package com.awn.awndashboards.dashboard.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OlapFactDTO {
    private String month;
    private String categoryName;
    private String subcategoryName;
    private String territoryName;
    private String channel;
    private BigDecimal revenue;
    private Long orders;
}
