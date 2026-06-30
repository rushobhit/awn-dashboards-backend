package com.awn.awndashboards.sales.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrderDTO {
    private Integer id;
    private LocalDateTime orderDate;
    private String salesOrderNumber;
    private Integer customerId;
    private BigDecimal totalDue;
}
