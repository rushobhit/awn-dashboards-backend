package com.awn.awndashboards.dashboard.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopCustomerDTO {
    private Integer customerId;
    private String customerName;
    private BigDecimal totalSpend;
    private Long totalOrders;
}
