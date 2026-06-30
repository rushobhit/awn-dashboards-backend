package com.awn.awndashboards.dashboard.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesTrendDTO {
    private String period;
    private BigDecimal revenue;
    private Long orderCount;
}
