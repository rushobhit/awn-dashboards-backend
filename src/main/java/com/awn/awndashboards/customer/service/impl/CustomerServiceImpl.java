package com.awn.awndashboards.customer.service.impl;

import com.awn.awndashboards.customer.dto.CustomerDTO;
import com.awn.awndashboards.customer.mapper.CustomerMapper;
import com.awn.awndashboards.customer.repository.CustomerRepository;
import com.awn.awndashboards.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
        return repository.findAll(pageable)
                .map(CustomerMapper::toDTO);
    }
}
