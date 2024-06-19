package by.russianzak.kafkatask.service;

import by.russianzak.kafkatask.event.ProductCreateEvent;
import by.russianzak.kafkatask.service.dto.CreateProductRequestDto;
import by.russianzak.kafkatask.service.dto.CreateProductResponseDto;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface ProductService {
    CreateProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto)
        throws InterruptedException, TimeoutException, ExecutionException;
}
