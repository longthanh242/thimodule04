package com.ra.controller;

import com.ra.model.dto.request.ProductDTO;
import com.ra.model.dto.response.ProductResponse;
import com.ra.model.entity.Catalog;
import com.ra.service.catalog.CatalogService;
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
    private final CatalogService catalogService;
    @Autowired
    public ProductController(ProductService productService, CatalogService catalogService) {
        this.productService = productService;
        this.catalogService = catalogService;
    }

    @GetMapping
    public ResponseEntity<?> renderAll(){
        List<ProductResponse> list = productService.getAll();
        if (list.isEmpty())
            return new ResponseEntity<>("Chưa có sản phẩm", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDTO productDTO){
        Catalog catalog = catalogService.findById(productDTO.getCatalogId());
        if (catalog == null)
            return new ResponseEntity<>("Mã thể loại này không tồn tại", HttpStatus.NOT_FOUND);
        ProductResponse productResponse = productService.save(productDTO);
        return new ResponseEntity<>(productResponse,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        ProductResponse productResponse = productService.findById(id);
        if (productResponse == null)
            return new ResponseEntity<>("Sản phẩm không tồn tại", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody ProductDTO productDTO){
        ProductResponse productUpdate = productService.update(id, productDTO);
        if (productUpdate == null)
            return new ResponseEntity<>("Sản phẩm không tồn tại", HttpStatus.NOT_FOUND);
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
