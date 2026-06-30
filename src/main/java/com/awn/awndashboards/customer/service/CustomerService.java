package com.awn.awndashboards.customer.service;

import com.awn.awndashboards.customer.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<CustomerDTO> getAllCustomers(Pageable pageable);
}
