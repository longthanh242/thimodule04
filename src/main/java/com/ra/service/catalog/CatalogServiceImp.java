package com.ra.service.catalog;

import com.ra.model.dto.request.CatalogDTO;
import com.ra.model.entity.Catalog;
import com.ra.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImp implements CatalogService{
    private final CatalogRepository catalogRepository;
    @Autowired
    public CatalogServiceImp(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<Catalog> getAll() {
        return catalogRepository.findAll();
    }

    @Override
    public Catalog create(CatalogDTO catalogDTO) {
        Catalog catalog = Catalog.builder()
                .catName(catalogDTO.getCatName())
                .description(catalogDTO.getDescription())
                .build();
        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog findById(int catId) {
        return catalogRepository.findById(catId).orElse(null);
    }

    @Override
    public Catalog update(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Override
    public void delete(int catId) {
        catalogRepository.deleteById(catId);
    }
}
