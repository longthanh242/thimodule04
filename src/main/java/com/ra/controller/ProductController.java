package com.ra.controller;

import com.ra.model.dto.request.ProductDTO;
import com.ra.model.dto.response.ProductResponse;
import com.ra.model.entity.Product;
import com.ra.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> renderAll(){
        List<ProductResponse> list = productService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDTO productDTO){
        ProductResponse productResponse = productService.save(productDTO);
        return new ResponseEntity<>(productResponse,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        ProductResponse productResponse = productService.findById(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody ProductDTO productDTO){
        ProductResponse productUpdate = productService.update(id, productDTO);
        return new ResponseEntity<>(productUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        productService.delete(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> searchByName(@RequestParam("name") String productName){
        List<ProductResponse> productList= productService.searchByName(productName);
        if (productList.isEmpty())
            return new ResponseEntity<>("Không có sản phẩm", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
