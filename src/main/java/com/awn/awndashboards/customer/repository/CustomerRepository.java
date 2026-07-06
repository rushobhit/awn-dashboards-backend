package com.awn.awndashboards.customer.repository;

import com.awn.awndashboards.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @org.springframework.data.jpa.repository.Query(value = """
        SELECT DISTINCT c.* 
        FROM sales.customer c
        LEFT JOIN sales.salesorderheader soh ON c.customerid = soh.customerid
        LEFT JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        LEFT JOIN production.product p ON sod.productid = p.productid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
    """, 
    countQuery = """
        SELECT count(DISTINCT c.customerid)
        FROM sales.customer c
        LEFT JOIN sales.salesorderheader soh ON c.customerid = soh.customerid
        LEFT JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        LEFT JOIN production.product p ON sod.productid = p.productid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
    """, nativeQuery = true)
    org.springframework.data.domain.Page<Customer> findWithFilters(
            @org.springframework.data.repository.query.Param("startDate") java.time.LocalDateTime startDate,
            @org.springframework.data.repository.query.Param("endDate") java.time.LocalDateTime endDate,
            @org.springframework.data.repository.query.Param("territoryId") Integer territoryId,
            @org.springframework.data.repository.query.Param("categoryName") String categoryName,
            org.springframework.data.domain.Pageable pageable);
}
