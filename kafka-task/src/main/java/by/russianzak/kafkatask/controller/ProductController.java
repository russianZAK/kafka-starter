package by.russianzak.kafkatask.controller;

import by.russianzak.kafkatask.event.ProductCreateEvent;
import by.russianzak.kafkatask.service.ProductService;
import by.russianzak.kafkatask.service.dto.CreateProductRequestDto;
import by.russianzak.kafkatask.service.dto.CreateProductResponseDto;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<CreateProductResponseDto> createProduct(@RequestBody @Validated CreateProductRequestDto createProductRequestDto)
        throws InterruptedException, TimeoutException, ExecutionException {
        CreateProductResponseDto product = productService.createProduct(createProductRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
