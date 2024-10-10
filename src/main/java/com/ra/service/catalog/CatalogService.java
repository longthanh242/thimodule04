package com.ra.service.catalog;

import com.ra.model.dto.request.CatalogDTO;
import com.ra.model.entity.Catalog;

import java.util.List;

public interface CatalogService {
    List<Catalog> getAll();

    Catalog create(CatalogDTO catalogDTO);

    Catalog findById(int catId);

    Catalog update(Catalog catalog);

    void delete(int catId);
}
