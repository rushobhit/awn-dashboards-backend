package com.awn.awndashboards.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterOptionsDTO {
    private List<TerritoryOptionDTO> territories;
    private List<String> categories;
}
