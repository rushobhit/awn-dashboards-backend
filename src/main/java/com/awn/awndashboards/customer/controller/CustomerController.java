package com.awn.awndashboards.customer.controller;

import com.awn.awndashboards.customer.entity.Customer;
import com.awn.awndashboards.customer.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping
    public Page<Customer> getCustomers(
            @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime startDate,
            @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName,
            Pageable pageable) {
        return customerRepository.findWithFilters(startDate, endDate, territoryId, categoryName, pageable);
    }
}
