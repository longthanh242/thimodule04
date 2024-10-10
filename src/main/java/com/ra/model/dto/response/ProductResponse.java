package com.ra.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ProductResponse {
    private long productId;
    private String productName;
    private double price;
    private String description;
    private String imgURL;
    private boolean status;
    private String catalogName;
}
