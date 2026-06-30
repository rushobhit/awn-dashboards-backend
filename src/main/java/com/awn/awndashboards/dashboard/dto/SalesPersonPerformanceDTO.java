package com.awn.awndashboards.dashboard.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesPersonPerformanceDTO {
    private Integer salesPersonId;
    private String salesPersonName;
    private BigDecimal totalSales;
    private BigDecimal quota;
    private BigDecimal bonus;
}
