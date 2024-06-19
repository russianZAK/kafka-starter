package by.russianzak.kafkatask.service.dto;

import java.math.BigDecimal;

public class CreateProductRequestDto {
    private String title;
    private BigDecimal price;
    private Long quantity;

    public CreateProductRequestDto(String title, BigDecimal price, Long quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
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
