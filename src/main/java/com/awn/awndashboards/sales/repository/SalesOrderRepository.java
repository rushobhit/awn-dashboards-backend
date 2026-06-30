package com.awn.awndashboards.sales.repository;

import com.awn.awndashboards.sales.entity.SalesOrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrderHeader, Integer> {
}
