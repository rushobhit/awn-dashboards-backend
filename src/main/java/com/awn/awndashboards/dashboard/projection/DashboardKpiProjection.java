package com.awn.awndashboards.dashboard.projection;

import java.math.BigDecimal;

public interface DashboardKpiProjection {

    BigDecimal getTotalRevenue();

    Long getTotalOrders();

    Long getTotalCustomers();

    Long getTotalProducts();

}