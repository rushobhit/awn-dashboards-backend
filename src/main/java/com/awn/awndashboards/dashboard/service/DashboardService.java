package com.awn.awndashboards.dashboard.service;

import com.awn.awndashboards.dashboard.projection.DashboardKpiProjection;

import java.time.LocalDateTime;
import java.util.List;

import com.awn.awndashboards.dashboard.dto.*;

public interface DashboardService {

    DashboardKpiProjection getDashboardKpis(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName);
    
    List<MonthlySalesDTO> getMonthlySales(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName);

    List<SalesByCategoryDTO> getSalesByCategory(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName);

    List<SalesByTerritoryDTO> getSalesByTerritory(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName);

    List<TopProductDTO> getTopProducts(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName);

    List<TopCustomerDTO> getTopCustomers(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName);

    List<SalesTrendDTO> getSalesTrend(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName, String granularity);

    List<ProductPerformanceDTO> getProductPerformance(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName);

    List<SalesChannelDTO> getSalesChannels(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName);

    List<SalesPersonPerformanceDTO> getSalesPersonPerformance(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName);

    List<SubcategoryMarginDTO> getSubcategoryMargins(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName);

    List<OlapFactDTO> getOlapFacts(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName);

    FilterOptionsDTO getFilterOptions();
}