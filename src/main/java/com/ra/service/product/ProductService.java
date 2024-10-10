package com.ra.service.product;


import com.ra.model.dto.request.ProductDTO;
import com.ra.model.dto.response.ProductResponse;
import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll();

    ProductResponse save(ProductDTO productDTO);

    ProductResponse findById(long productId);

    ProductResponse update(long productId, ProductDTO productDTO);

    void delete(long productId);

    List<ProductResponse> searchByName(String producName);
}
