package com.awn.awndashboards.product.repository;

import com.awn.awndashboards.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @org.springframework.data.jpa.repository.Query(value = """
        SELECT DISTINCT p.* 
        FROM production.product p
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        LEFT JOIN sales.salesorderdetail sod ON p.productid = sod.productid
        LEFT JOIN sales.salesorderheader soh ON sod.salesorderid = soh.salesorderid
        WHERE (:name IS NULL OR p.name ILIKE CONCAT('%', :name, '%'))
          AND (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
    """, 
    countQuery = """
        SELECT count(DISTINCT p.productid)
        FROM production.product p
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        LEFT JOIN sales.salesorderdetail sod ON p.productid = sod.productid
        LEFT JOIN sales.salesorderheader soh ON sod.salesorderid = soh.salesorderid
        WHERE (:name IS NULL OR p.name ILIKE CONCAT('%', :name, '%'))
          AND (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
    """, nativeQuery = true)
    Page<Product> findWithFilters(
            @org.springframework.data.repository.query.Param("name") String name,
            @org.springframework.data.repository.query.Param("startDate") java.time.LocalDateTime startDate,
            @org.springframework.data.repository.query.Param("endDate") java.time.LocalDateTime endDate,
            @org.springframework.data.repository.query.Param("territoryId") Integer territoryId,
            @org.springframework.data.repository.query.Param("categoryName") String categoryName,
            Pageable pageable);
}