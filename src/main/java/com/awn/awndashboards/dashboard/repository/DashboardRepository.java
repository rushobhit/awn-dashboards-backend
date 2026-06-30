package com.awn.awndashboards.dashboard.repository;

import com.awn.awndashboards.dashboard.projection.DashboardKpiProjection;
import com.awn.awndashboards.product.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<Product,Integer>{

    @Query(value="""
        SELECT
            COALESCE(SUM(sod.linetotal), 0) totalRevenue,
            COUNT(DISTINCT soh.salesorderid) totalOrders,
            COUNT(DISTINCT soh.customerid) totalCustomers,
            COUNT(DISTINCT sod.productid) totalProducts
        FROM sales.salesorderheader soh
        JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        JOIN production.product p ON sod.productid = p.productid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        """, nativeQuery=true)
    DashboardKpiProjection getDashboardKpis(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);


    @Query(value="""
        SELECT
            TO_CHAR(soh.orderdate,'Mon YYYY') as month,
            COALESCE(SUM(sod.linetotal), 0) revenue
        FROM sales.salesorderheader soh
        JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        JOIN production.product p ON sod.productid = p.productid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        GROUP BY
            DATE_TRUNC('month',soh.orderdate),
            TO_CHAR(soh.orderdate,'Mon YYYY')
        ORDER BY DATE_TRUNC('month',soh.orderdate)
        """, nativeQuery=true)
    List<Object[]> getMonthlySales(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);

    @Query(value="""
        SELECT 
            pc.name AS categoryName,
            COALESCE(SUM(sod.linetotal), 0) AS sales
        FROM production.productcategory pc
        JOIN production.productsubcategory psc ON pc.productcategoryid = psc.productcategoryid
        JOIN production.product p ON psc.productsubcategoryid = p.productsubcategoryid
        JOIN sales.salesorderdetail sod ON p.productid = sod.productid
        JOIN sales.salesorderheader soh ON sod.salesorderid = soh.salesorderid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        GROUP BY pc.name
        ORDER BY sales DESC
        """, nativeQuery=true)
    List<Object[]> getSalesByCategory(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);

    @Query(value="""
        SELECT 
            st.name AS territoryName,
            COALESCE(SUM(sod.linetotal), 0) AS sales
        FROM sales.salesterritory st
        JOIN sales.salesorderheader soh ON st.territoryid = soh.territoryid
        JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        JOIN production.product p ON sod.productid = p.productid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        GROUP BY st.name
        ORDER BY sales DESC
        """, nativeQuery=true)
    List<Object[]> getSalesByTerritory(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);

    @Query(value="""
        SELECT 
            p.name AS productName,
            p.productnumber AS productNumber,
            COALESCE(SUM(sod.linetotal), 0) AS totalSales,
            SUM(sod.orderqty) AS totalQuantity
        FROM production.product p
        JOIN sales.salesorderdetail sod ON p.productid = sod.productid
        JOIN sales.salesorderheader soh ON sod.salesorderid = soh.salesorderid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        GROUP BY p.name, p.productnumber
        ORDER BY totalSales DESC
        LIMIT 10
        """, nativeQuery=true)
    List<Object[]> getTopProducts(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);

    @Query(value="""
        SELECT 
            c.customerid AS customerId,
            COALESCE(s.name, CONCAT(pers.firstname, ' ', pers.lastname)) AS customerName,
            COALESCE(SUM(sod.linetotal), 0) AS totalSpend,
            COUNT(DISTINCT soh.salesorderid) AS totalOrders
        FROM sales.customer c
        JOIN sales.salesorderheader soh ON c.customerid = soh.customerid
        JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        JOIN production.product p ON sod.productid = p.productid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        LEFT JOIN person.person pers ON c.personid = pers.businessentityid
        LEFT JOIN sales.store s ON c.storeid = s.businessentityid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        GROUP BY c.customerid, s.name, pers.firstname, pers.lastname
        ORDER BY totalSpend DESC
        LIMIT 10
        """, nativeQuery=true)
    List<Object[]> getTopCustomers(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);

    @Query(value="""
        SELECT 
            TO_CHAR(soh.orderdate, 'YYYY-MM') AS period,
            COALESCE(SUM(sod.linetotal), 0) AS revenue,
            COUNT(DISTINCT soh.salesorderid) AS orderCount
        FROM sales.salesorderheader soh
        JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        JOIN production.product p ON sod.productid = p.productid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        GROUP BY DATE_TRUNC('month', soh.orderdate), TO_CHAR(soh.orderdate, 'YYYY-MM')
        ORDER BY DATE_TRUNC('month', soh.orderdate)
        """, nativeQuery=true)
    List<Object[]> getSalesTrend(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);

    @Query(value="""
        SELECT 
            p.productid AS productId,
            p.name AS productName,
            p.productnumber AS productNumber,
            pc.name AS categoryName,
            COALESCE(SUM(sod.linetotal), 0) AS totalSales,
            COALESCE(SUM(sod.orderqty), 0) AS totalQuantity,
            COUNT(DISTINCT sod.salesorderid) AS totalOrders,
            COALESCE(SUM(sod.linetotal) - SUM(p.standardcost * sod.orderqty), 0) AS margin
        FROM production.product p
        JOIN sales.salesorderdetail sod ON p.productid = sod.productid
        JOIN sales.salesorderheader soh ON sod.salesorderid = soh.salesorderid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        GROUP BY p.productid, p.name, p.productnumber, pc.name
        ORDER BY totalSales DESC
        LIMIT 50
        """, nativeQuery=true)
    List<Object[]> getProductPerformance(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);

    @Query(value="""
        SELECT 
            CASE WHEN soh.onlineorderflag = true THEN 'Online' ELSE 'Reseller' END AS channel,
            COALESCE(SUM(sod.linetotal), 0) AS revenue,
            COUNT(DISTINCT soh.salesorderid) AS orderCount
        FROM sales.salesorderheader soh
        JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        JOIN production.product p ON sod.productid = p.productid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        GROUP BY soh.onlineorderflag
        """, nativeQuery=true)
    List<Object[]> getSalesChannels(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);

    @Query(value="""
        SELECT 
            sp.businessentityid AS salesPersonId,
            COALESCE(CONCAT(pers.firstname, ' ', pers.lastname), 'Reseller Sales') AS salesPersonName,
            COALESCE(SUM(sod.linetotal), 0) AS totalSales,
            COALESCE(sp.salesquota, 0) AS quota,
            COALESCE(sp.bonus, 0) AS bonus
        FROM sales.salesperson sp
        JOIN person.person pers ON sp.businessentityid = pers.businessentityid
        JOIN sales.salesorderheader soh ON sp.businessentityid = soh.salespersonid
        JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        JOIN production.product p ON sod.productid = p.productid
        LEFT JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        GROUP BY sp.businessentityid, pers.firstname, pers.lastname, sp.salesquota, sp.bonus
        ORDER BY totalSales DESC
        LIMIT 10
        """, nativeQuery=true)
    List<Object[]> getSalesPersonPerformance(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);

    @Query(value="""
        SELECT 
            psc.name AS subcategoryName,
            COALESCE(SUM(sod.linetotal), 0) AS revenue,
            COALESCE(SUM(p.standardcost * sod.orderqty), 0) AS cost,
            COALESCE(SUM(sod.linetotal) - SUM(p.standardcost * sod.orderqty), 0) AS profit
        FROM production.productsubcategory psc
        JOIN production.product p ON psc.productsubcategoryid = p.productsubcategoryid
        JOIN sales.salesorderdetail sod ON p.productid = sod.productid
        JOIN sales.salesorderheader soh ON sod.salesorderid = soh.salesorderid
        LEFT JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        GROUP BY psc.name
        ORDER BY profit DESC
        """, nativeQuery=true)
    List<Object[]> getSubcategoryMargins(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);

    @Query(value="""
        SELECT
            to_char(soh.orderdate, 'YYYY-MM') AS month,
            pc.name AS categoryName,
            psc.name AS subcategoryName,
            st.name AS territoryName,
            CASE WHEN soh.onlineorderflag = true THEN 'Online' ELSE 'Reseller' END AS channel,
            COALESCE(SUM(sod.linetotal), 0) AS revenue,
            COUNT(DISTINCT soh.salesorderid) AS orders
        FROM sales.salesorderheader soh
        JOIN sales.salesorderdetail sod ON soh.salesorderid = sod.salesorderid
        JOIN production.product p ON sod.productid = p.productid
        JOIN production.productsubcategory psc ON p.productsubcategoryid = psc.productsubcategoryid
        JOIN production.productcategory pc ON psc.productcategoryid = pc.productcategoryid
        JOIN sales.salesterritory st ON soh.territoryid = st.territoryid
        WHERE (cast(:startDate as timestamp) IS NULL OR soh.orderdate >= cast(:startDate as timestamp))
          AND (cast(:endDate as timestamp) IS NULL OR soh.orderdate <= cast(:endDate as timestamp))
          AND (cast(:territoryId as integer) IS NULL OR soh.territoryid = cast(:territoryId as integer))
          AND (cast(:categoryName as varchar) IS NULL OR pc.name = cast(:categoryName as varchar))
        GROUP BY to_char(soh.orderdate, 'YYYY-MM'), pc.name, psc.name, st.name, soh.onlineorderflag
        ORDER BY month ASC
        """, nativeQuery=true)
    List<Object[]> getOlapFacts(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("territoryId") Integer territoryId,
            @Param("categoryName") String categoryName);

}