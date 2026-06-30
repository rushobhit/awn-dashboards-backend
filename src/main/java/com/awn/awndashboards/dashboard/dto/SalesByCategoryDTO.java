package com.awn.awndashboards.dashboard.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesByCategoryDTO {
    private String categoryName;
    private BigDecimal sales;
}
