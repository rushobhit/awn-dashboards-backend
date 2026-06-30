package com.awn.awndashboards.dashboard.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardKpiDTO {

    private BigDecimal totalRevenue;

    private Long totalOrders;

    private Long totalCustomers;

    private Long totalProducts;

}