package com.ra.model.dto.request;

import com.ra.model.entity.Catalog;
import com.ra.validate.product.ProductUnique;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ProductDTO {
    @NotBlank
    @ProductUnique
    private String productName;
    @NotBlank
    private double price;
    @NotBlank
    private String description;
    private MultipartFile image;
    private boolean status;
    private int catalogId;
}
