package com.awn.awndashboards.product.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Integer id;

    private String name;

    private String productNumber;

    private BigDecimal listPrice;

    private String color;
}