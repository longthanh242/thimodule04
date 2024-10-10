package com.ra.controller;

import com.ra.model.dto.request.CatalogDTO;
import com.ra.model.entity.Catalog;
import com.ra.service.catalog.CatalogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {
    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Catalog> catalogList = catalogService.getAll();
        if (catalogList.isEmpty())
            return new ResponseEntity<>("Danh sách chưa có thể loại", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(catalogList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewCatalog(@Valid @RequestBody CatalogDTO catalogDTO) {
        Catalog catalog = catalogService.create(catalogDTO);
        return new ResponseEntity<>(catalog, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Catalog catalog = catalogService.findById(id);
        if (catalog == null)
            return new ResponseEntity<>("Thể loại này không có", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(catalog, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCatalog(@RequestBody Catalog catalog, @PathVariable int id) {
        catalog.setCatId(id);
        Catalog catalogUpdate = catalogService.update(catalog);
        return new ResponseEntity<>(catalogUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        catalogService.delete(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }
}
