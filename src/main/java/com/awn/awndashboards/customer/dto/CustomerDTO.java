package com.awn.awndashboards.customer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Integer id;
    private String accountNumber;
    private Integer personId;
    private Integer storeId;
}
