package com.awn.awndashboards.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product", schema = "production")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @Column(name = "productid")
    private Integer productId;

    @Column(name = "name")
    private String name;

    @Column(name = "productnumber")
    private String productNumber;

    @Column(name = "makeflag")
    private Boolean makeFlag;

    @Column(name = "finishedgoodsflag")
    private Boolean finishedGoodsFlag;

    @Column(name = "color")
    private String color;

    @Column(name = "standardcost")
    private BigDecimal standardCost;

    @Column(name = "listprice")
    private BigDecimal listPrice;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "sellstartdate")
    private LocalDateTime sellStartDate;

    @Column(name = "modifieddate")
    private LocalDateTime modifiedDate;

    @Column(name = "rowguid")
    private UUID rowGuid;
}