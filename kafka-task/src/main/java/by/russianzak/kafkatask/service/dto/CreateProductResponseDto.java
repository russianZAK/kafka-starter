package by.russianzak.kafkatask.service.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProductResponseDto {
    private UUID id;
    private String title;
    private BigDecimal price;
    private Long quantity;

    public CreateProductResponseDto(UUID id, String title, BigDecimal price, Long quantity) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
