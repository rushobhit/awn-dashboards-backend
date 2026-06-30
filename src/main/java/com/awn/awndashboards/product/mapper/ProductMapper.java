package com.awn.awndashboards.product.mapper;

import com.awn.awndashboards.product.dto.ProductDTO;
import com.awn.awndashboards.product.entity.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product){

        return ProductDTO.builder()
                .id(product.getProductId())
                .name(product.getName())
                .productNumber(product.getProductNumber())
                .listPrice(product.getListPrice())
                .color(product.getColor())
                .build();
    }

}