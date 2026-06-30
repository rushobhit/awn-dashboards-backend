package com.awn.awndashboards.dashboard.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopProductDTO {
    private String productName;
    private String productNumber;
    private BigDecimal totalSales;
    private Long totalQuantity;
}
