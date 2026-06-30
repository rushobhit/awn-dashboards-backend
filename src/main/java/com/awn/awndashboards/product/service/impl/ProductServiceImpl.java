package com.awn.awndashboards.product.service.impl;

import com.awn.awndashboards.product.dto.ProductDTO;
import com.awn.awndashboards.product.mapper.ProductMapper;
import com.awn.awndashboards.product.repository.ProductRepository;
import com.awn.awndashboards.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return repository.findAll(pageable)
                .map(ProductMapper::toDTO);
    }

}