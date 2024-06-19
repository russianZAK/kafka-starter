package by.russianzak.kafkatask.service;

import by.russianzak.kafkatask.event.ProductCreateEvent;
import by.russianzak.kafkatask.exception.TimeOutException;
import by.russianzak.kafkatask.service.dto.CreateProductRequestDto;
import by.russianzak.kafkatask.service.dto.CreateProductResponseDto;
import dev.nikita.kafka.reply.starter.template.KafkaSyncTemplate;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final Map<String, CompletableFuture<ProductCreateEvent>> responseMap = new ConcurrentHashMap<>();

    private final KafkaSyncTemplate<String, ProductCreateEvent> kafkaSyncTemplate;

    public ProductServiceImpl(KafkaSyncTemplate<String, ProductCreateEvent> kafkaSyncTemplate) {
        this.kafkaSyncTemplate = kafkaSyncTemplate;
    }

    @Override
    public CreateProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto)
        throws InterruptedException, TimeOutException, TimeoutException {

        // TODO: SAVE TO DATABASE

        UUID productId = UUID.randomUUID();
        CompletableFuture<ProductCreateEvent> responseFuture = new CompletableFuture<>();
        responseMap.put(productId.toString(), responseFuture);

        ProductCreateEvent productCreateEvent = new ProductCreateEvent(productId, createProductRequestDto.getTitle(), createProductRequestDto.getPrice(), createProductRequestDto.getQuantity());

        ProductCreateEvent productCreateEventResponse = kafkaSyncTemplate.exchange(productCreateEvent.getProductId().toString(), productCreateEvent);

        return new CreateProductResponseDto(productCreateEventResponse.getProductId(), productCreateEventResponse.getTitle(), productCreateEventResponse.getPrice(), productCreateEventResponse.getQuantity());
    }
}
