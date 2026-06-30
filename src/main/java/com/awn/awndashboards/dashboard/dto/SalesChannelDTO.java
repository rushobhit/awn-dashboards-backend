package com.awn.awndashboards.dashboard.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesChannelDTO {
    private String channel;
    private BigDecimal revenue;
    private Long orderCount;
}
