package com.ra.repository;

import com.ra.model.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
    boolean existsCatalogByCatName(String catName);
}
