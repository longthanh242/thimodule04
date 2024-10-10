package com.ra.validate.product;

import com.ra.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateProductNameUnique implements ConstraintValidator<ProductUnique, String> {
    private final ProductRepository productRepository;
    @Autowired
    public ValidateProductNameUnique(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isValid(String productName, ConstraintValidatorContext context) {
        return !productRepository.existsProductByProductName(productName);
    }
}
