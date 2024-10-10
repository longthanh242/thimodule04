package com.ra.model.dto.request;

import com.ra.model.entity.Catalog;
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
    private String productName;
    @NotBlank
    private double price;
    @NotBlank
    private String description;
    private MultipartFile image;
    private int catalogId;
}
