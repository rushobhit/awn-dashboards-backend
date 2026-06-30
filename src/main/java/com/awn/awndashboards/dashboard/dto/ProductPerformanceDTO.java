package com.awn.awndashboards.dashboard.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPerformanceDTO {
    private Integer productId;
    private String productName;
    private String productNumber;
    private String categoryName;
    private BigDecimal totalSales;
    private Long totalQuantity;
    private Long totalOrders;
    private BigDecimal margin;
}
