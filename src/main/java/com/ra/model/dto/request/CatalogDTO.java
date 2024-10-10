package com.ra.model.dto.request;

import com.ra.validate.catalog.CatalogUnique;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CatalogDTO {
    @NotBlank
    @CatalogUnique
    private String catName;
    private String description;
}
