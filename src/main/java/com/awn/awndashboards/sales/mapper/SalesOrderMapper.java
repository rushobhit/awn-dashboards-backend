package com.awn.awndashboards.sales.mapper;

import com.awn.awndashboards.sales.dto.SalesOrderDTO;
import com.awn.awndashboards.sales.entity.SalesOrderHeader;

public class SalesOrderMapper {

    public static SalesOrderDTO toDTO(SalesOrderHeader header) {
        if (header == null) {
            return null;
        }
        return SalesOrderDTO.builder()
                .id(header.getSalesOrderId())
                .orderDate(header.getOrderDate())
                .salesOrderNumber(header.getSalesOrderNumber())
                .customerId(header.getCustomerId())
                .totalDue(header.getTotalDue())
                .build();
    }
}
