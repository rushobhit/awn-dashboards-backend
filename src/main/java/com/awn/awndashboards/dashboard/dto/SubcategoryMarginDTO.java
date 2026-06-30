package com.awn.awndashboards.dashboard.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubcategoryMarginDTO {
    private String subcategoryName;
    private BigDecimal revenue;
    private BigDecimal cost;
    private BigDecimal profit;
}
