package com.awn.awndashboards.dashboard.service.impl;

import com.awn.awndashboards.dashboard.dto.*;
import com.awn.awndashboards.dashboard.projection.DashboardKpiProjection;
import com.awn.awndashboards.dashboard.repository.DashboardRepository;
import com.awn.awndashboards.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository repository;

    @Override
    @Cacheable(value = "dashboardKpis", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName")
    public DashboardKpiProjection getDashboardKpis(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName) {
        return repository.getDashboardKpis(startDate, endDate, territoryId, categoryName);
    }

    @Override
    @Cacheable(value = "monthlySales", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName")
    public List<MonthlySalesDTO> getMonthlySales(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName) {
        return repository.getMonthlySales(startDate, endDate, territoryId, categoryName)
                .stream()
                .map(row -> MonthlySalesDTO.builder()
                        .month((String) row[0])
                        .revenue(row[1] != null ? new BigDecimal(row[1].toString()) : BigDecimal.ZERO)
                        .build())
                .toList();
    }

    @Override
    @Cacheable(value = "salesByCategory", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName")
    public List<SalesByCategoryDTO> getSalesByCategory(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName) {
        return repository.getSalesByCategory(startDate, endDate, territoryId, categoryName)
                .stream()
                .map(row -> SalesByCategoryDTO.builder()
                        .categoryName((String) row[0])
                        .sales(row[1] != null ? new BigDecimal(row[1].toString()) : BigDecimal.ZERO)
                        .build())
                .toList();
    }

    @Override
    @Cacheable(value = "salesByTerritory", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName")
    public List<SalesByTerritoryDTO> getSalesByTerritory(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName) {
        return repository.getSalesByTerritory(startDate, endDate, territoryId, categoryName)
                .stream()
                .map(row -> SalesByTerritoryDTO.builder()
                        .territoryName((String) row[0])
                        .sales(row[1] != null ? new BigDecimal(row[1].toString()) : BigDecimal.ZERO)
                        .build())
                .toList();
    }

    @Override
    @Cacheable(value = "topProducts", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName")
    public List<TopProductDTO> getTopProducts(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName) {
        return repository.getTopProducts(startDate, endDate, territoryId, categoryName)
                .stream()
                .map(row -> TopProductDTO.builder()
                        .productName((String) row[0])
                        .productNumber((String) row[1])
                        .totalSales(row[2] != null ? new BigDecimal(row[2].toString()) : BigDecimal.ZERO)
                        .totalQuantity(row[3] != null ? Long.valueOf(row[3].toString()) : 0L)
                        .build())
                .toList();
    }

    @Override
    @Cacheable(value = "topCustomers", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName")
    public List<TopCustomerDTO> getTopCustomers(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName) {
        return repository.getTopCustomers(startDate, endDate, territoryId, categoryName)
                .stream()
                .map(row -> TopCustomerDTO.builder()
                        .customerId(row[0] != null ? Integer.valueOf(row[0].toString()) : null)
                        .customerName((String) row[1])
                        .totalSpend(row[2] != null ? new BigDecimal(row[2].toString()) : BigDecimal.ZERO)
                        .totalOrders(row[3] != null ? Long.valueOf(row[3].toString()) : 0L)
                        .build())
                .toList();
    }

    @Override
    @Cacheable(value = "salesTrend", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName + '-' + #granularity")
    public List<SalesTrendDTO> getSalesTrend(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName, String granularity) {
        List<Object[]> results;
        if ("year".equalsIgnoreCase(granularity)) {
            results = repository.getSalesTrendByYear(startDate, endDate, territoryId, categoryName);
        } else if ("quarter".equalsIgnoreCase(granularity)) {
            results = repository.getSalesTrendByQuarter(startDate, endDate, territoryId, categoryName);
        } else if ("week".equalsIgnoreCase(granularity)) {
            results = repository.getSalesTrendByWeek(startDate, endDate, territoryId, categoryName);
        } else if ("day".equalsIgnoreCase(granularity)) {
            results = repository.getSalesTrendByDay(startDate, endDate, territoryId, categoryName);
        } else {
            results = repository.getSalesTrend(startDate, endDate, territoryId, categoryName);
        }
        
        return results.stream()
                .map(row -> SalesTrendDTO.builder()
                        .period((String) row[0])
                        .revenue(row[1] != null ? new BigDecimal(row[1].toString()) : BigDecimal.ZERO)
                        .orderCount(row[2] != null ? Long.valueOf(row[2].toString()) : 0L)
                        .build())
                .toList();
    }

    @Override
    @Cacheable(value = "productPerformance", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName")
    public List<ProductPerformanceDTO> getProductPerformance(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName) {
        return repository.getProductPerformance(startDate, endDate, territoryId, categoryName)
                .stream()
                .map(row -> ProductPerformanceDTO.builder()
                        .productId(row[0] != null ? Integer.valueOf(row[0].toString()) : null)
                        .productName((String) row[1])
                        .productNumber((String) row[2])
                        .categoryName((String) row[3])
                        .totalSales(row[4] != null ? new BigDecimal(row[4].toString()) : BigDecimal.ZERO)
                        .totalQuantity(row[5] != null ? Long.valueOf(row[5].toString()) : 0L)
                        .totalOrders(row[6] != null ? Long.valueOf(row[6].toString()) : 0L)
                        .margin(row[7] != null ? new BigDecimal(row[7].toString()) : BigDecimal.ZERO)
                        .build())
                .toList();
    }

    @Override
    @Cacheable(value = "salesChannels", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName")
    public List<SalesChannelDTO> getSalesChannels(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName) {
        return repository.getSalesChannels(startDate, endDate, territoryId, categoryName)
                .stream()
                .map(row -> SalesChannelDTO.builder()
                        .channel((String) row[0])
                        .revenue(row[1] != null ? new BigDecimal(row[1].toString()) : BigDecimal.ZERO)
                        .orderCount(row[2] != null ? Long.valueOf(row[2].toString()) : 0L)
                        .build())
                .toList();
    }

    @Override
    @Cacheable(value = "salesPersonPerformance", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName")
    public List<SalesPersonPerformanceDTO> getSalesPersonPerformance(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName) {
        return repository.getSalesPersonPerformance(startDate, endDate, territoryId, categoryName)
                .stream()
                .map(row -> SalesPersonPerformanceDTO.builder()
                        .salesPersonId(row[0] != null ? Integer.valueOf(row[0].toString()) : null)
                        .salesPersonName((String) row[1])
                        .totalSales(row[2] != null ? new BigDecimal(row[2].toString()) : BigDecimal.ZERO)
                        .quota(row[3] != null ? new BigDecimal(row[3].toString()) : BigDecimal.ZERO)
                        .bonus(row[4] != null ? new BigDecimal(row[4].toString()) : BigDecimal.ZERO)
                        .build())
                .toList();
    }

    @Override
    @Cacheable(value = "subcategoryMargins", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName")
    public List<SubcategoryMarginDTO> getSubcategoryMargins(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName) {
        return repository.getSubcategoryMargins(startDate, endDate, territoryId, categoryName)
                .stream()
                .map(row -> SubcategoryMarginDTO.builder()
                        .subcategoryName((String) row[0])
                        .revenue(row[1] != null ? new BigDecimal(row[1].toString()) : BigDecimal.ZERO)
                        .cost(row[2] != null ? new BigDecimal(row[2].toString()) : BigDecimal.ZERO)
                        .profit(row[3] != null ? new BigDecimal(row[3].toString()) : BigDecimal.ZERO)
                        .build())
                .toList();
    }

    @Override
    @Cacheable(value = "olapFacts", key = "#startDate + '-' + #endDate + '-' + #territoryId + '-' + #categoryName")
    public List<OlapFactDTO> getOlapFacts(LocalDateTime startDate, LocalDateTime endDate, Integer territoryId, String categoryName) {
        return repository.getOlapFacts(startDate, endDate, territoryId, categoryName)
                .stream()
                .map(row -> OlapFactDTO.builder()
                        .month((String) row[0])
                        .categoryName((String) row[1])
                        .subcategoryName((String) row[2])
                        .territoryName((String) row[3])
                        .channel((String) row[4])
                        .revenue(row[5] != null ? new BigDecimal(row[5].toString()) : BigDecimal.ZERO)
                        .orders(row[6] != null ? Long.valueOf(row[6].toString()) : 0L)
                        .build())
                .toList();
    }

    @Override
    @Cacheable(value = "filterOptions")
    public FilterOptionsDTO getFilterOptions() {
        List<TerritoryOptionDTO> territories = repository.getAllTerritoryOptions().stream()
                .map(row -> TerritoryOptionDTO.builder()
                        .id(row[0] != null ? Integer.valueOf(row[0].toString()) : null)
                        .name((String) row[1])
                        .build())
                .collect(Collectors.toList());

        List<String> categories = repository.getAllCategoryOptions();

        return FilterOptionsDTO.builder()
                .territories(territories)
                .categories(categories)
                .build();
    }

}