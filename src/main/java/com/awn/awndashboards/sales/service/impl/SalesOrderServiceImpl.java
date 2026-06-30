package com.awn.awndashboards.sales.service.impl;

import com.awn.awndashboards.sales.dto.SalesOrderDTO;
import com.awn.awndashboards.sales.mapper.SalesOrderMapper;
import com.awn.awndashboards.sales.repository.SalesOrderRepository;
import com.awn.awndashboards.sales.service.SalesOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesOrderServiceImpl implements SalesOrderService {

    private final SalesOrderRepository repository;

    @Override
    public Page<SalesOrderDTO> getAllSalesOrders(Pageable pageable) {
        return repository.findAll(pageable)
                .map(SalesOrderMapper::toDTO);
    }
}
