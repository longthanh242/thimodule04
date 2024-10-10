package com.ra.validate.catalog;

import com.ra.repository.CatalogRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateCatalogName implements ConstraintValidator<CatalogUnique, String> {
    private final CatalogRepository catalogRepository;
    @Autowired
    public ValidateCatalogName(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public boolean isValid(String catName, ConstraintValidatorContext context) {
        return !catalogRepository.existsCatalogByCatName(catName);
    }
}
