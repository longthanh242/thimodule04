package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

@Entity
@Table(name = "catalogs")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_id")
    private int catId;
    @Column(name = "catalog_name", length = 100, nullable = false)
    private String catName;
    @Column(columnDefinition = "text")
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "catalog")
    private List<Product> product;
}
