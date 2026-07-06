package com.awn.awndashboards.sales.controller;

import com.awn.awndashboards.sales.entity.SalesOrderHeader;
import com.awn.awndashboards.sales.repository.SalesOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sales-orders")
@RequiredArgsConstructor
public class SalesOrderController {

    private final SalesOrderRepository salesOrderRepository;

    @GetMapping
    public Page<SalesOrderHeader> getSalesOrders(
            @RequestParam(required = false) Integer customerId,
            @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime startDate,
            @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName,
            Pageable pageable) {
        return salesOrderRepository.findWithFilters(customerId, startDate, endDate, territoryId, categoryName, pageable);
    }
}
