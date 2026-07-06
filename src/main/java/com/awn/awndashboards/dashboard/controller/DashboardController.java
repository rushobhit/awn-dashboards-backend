package com.awn.awndashboards.dashboard.controller;

import com.awn.awndashboards.dashboard.dto.*;
import com.awn.awndashboards.dashboard.projection.DashboardKpiProjection;
import com.awn.awndashboards.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/kpis")
    public DashboardKpiProjection getDashboardKpis(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName) {
        return dashboardService.getDashboardKpis(startDate, endDate, territoryId, categoryName);
    }
    
    @GetMapping("/monthly-sales")
    public List<MonthlySalesDTO> monthlySales(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName) {
        return dashboardService.getMonthlySales(startDate, endDate, territoryId, categoryName);
    }

    @GetMapping("/sales-by-category")
    public List<SalesByCategoryDTO> getSalesByCategory(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName) {
        return dashboardService.getSalesByCategory(startDate, endDate, territoryId, categoryName);
    }

    @GetMapping("/sales-by-territory")
    public List<SalesByTerritoryDTO> getSalesByTerritory(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName) {
        return dashboardService.getSalesByTerritory(startDate, endDate, territoryId, categoryName);
    }

    @GetMapping("/top-products")
    public List<TopProductDTO> getTopProducts(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName) {
        return dashboardService.getTopProducts(startDate, endDate, territoryId, categoryName);
    }

    @GetMapping("/top-customers")
    public List<TopCustomerDTO> getTopCustomers(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName) {
        return dashboardService.getTopCustomers(startDate, endDate, territoryId, categoryName);
    }

    @GetMapping("/sales-trend")
    public List<SalesTrendDTO> getSalesTrend(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false, defaultValue = "month") String granularity) {
        return dashboardService.getSalesTrend(startDate, endDate, territoryId, categoryName, granularity);
    }

    @GetMapping("/product-performance")
    public List<ProductPerformanceDTO> getProductPerformance(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName) {
        return dashboardService.getProductPerformance(startDate, endDate, territoryId, categoryName);
    }

    @GetMapping("/sales-channels")
    public List<SalesChannelDTO> getSalesChannels(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName) {
        return dashboardService.getSalesChannels(startDate, endDate, territoryId, categoryName);
    }

    @GetMapping("/sales-people")
    public List<SalesPersonPerformanceDTO> getSalesPersonPerformance(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName) {
        return dashboardService.getSalesPersonPerformance(startDate, endDate, territoryId, categoryName);
    }

    @GetMapping("/subcategory-margins")
    public List<SubcategoryMarginDTO> getSubcategoryMargins(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName) {
        return dashboardService.getSubcategoryMargins(startDate, endDate, territoryId, categoryName);
    }

    @GetMapping("/olap-facts")
    public List<OlapFactDTO> getOlapFacts(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) Integer territoryId,
            @RequestParam(required = false) String categoryName) {
        return dashboardService.getOlapFacts(startDate, endDate, territoryId, categoryName);
    }

    @GetMapping("/filter-options")
    public FilterOptionsDTO getFilterOptions() {
        return dashboardService.getFilterOptions();
    }

}