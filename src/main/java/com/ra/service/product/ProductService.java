package com.ra.service.product;


import com.ra.model.dto.request.ProductDTO;
import com.ra.model.dto.response.ProductResponse;
import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll();
    ProductResponse save(ProductDTO productDTO);

    ProductResponse findById(long productId);

    Product update(Product product);
    void delete(long productId);

}
