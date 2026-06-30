package com.awn.awndashboards.sales.service;

import com.awn.awndashboards.sales.dto.SalesOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SalesOrderService {
    Page<SalesOrderDTO> getAllSalesOrders(Pageable pageable);
}
