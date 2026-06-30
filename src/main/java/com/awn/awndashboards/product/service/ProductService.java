package com.awn.awndashboards.product.service;

import com.awn.awndashboards.product.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDTO> getAllProducts(Pageable pageable);

}