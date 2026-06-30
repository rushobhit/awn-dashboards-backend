package com.awn.awndashboards.customer.mapper;

import com.awn.awndashboards.customer.dto.CustomerDTO;
import com.awn.awndashboards.customer.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerDTO.builder()
                .id(customer.getCustomerId())
                .accountNumber(customer.getAccountNumber())
                .personId(customer.getPersonId())
                .storeId(customer.getStoreId())
                .build();
    }
}
