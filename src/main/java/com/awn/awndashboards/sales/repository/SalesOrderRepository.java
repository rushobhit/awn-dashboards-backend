package com.awn.awndashboards.sales.repository;

import com.awn.awndashboards.sales.entity.SalesOrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrderHeader, Integer> {
    Page<SalesOrderHeader> findByCustomerId(Integer customerId, Pageable pageable);

    @org.springframework.data.jpa.repository.Query(value = """
        SELECT DISTINCT soh.* 
        FROM sales.salesorderheader soh
        LEFT JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        LEFT JOIN production.product p ON sod.productid = p.productid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:customerId as integer) IS NULL OR soh.customerid = cast(:customerId as integer))
          AND (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
    """, 
    countQuery = """
        SELECT count(DISTINCT soh.salesorderid)
        FROM sales.salesorderheader soh
        LEFT JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        LEFT JOIN production.product p ON sod.productid = p.productid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:customerId as integer) IS NULL OR soh.customerid = cast(:customerId as integer))
          AND (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
    """, nativeQuery = true)
    Page<SalesOrderHeader> findWithFilters(
            @org.springframework.data.repository.query.Param("customerId") Integer customerId,
            @org.springframework.data.repository.query.Param("startDate") java.time.LocalDateTime startDate,
            @org.springframework.data.repository.query.Param("endDate") java.time.LocalDateTime endDate,
            @org.springframework.data.repository.query.Param("territoryId") Integer territoryId,
            @org.springframework.data.repository.query.Param("categoryName") String categoryName,
            Pageable pageable);
}
