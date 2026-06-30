package com.awn.awndashboards.sales.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "salesorderheader", schema = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrderHeader {

    @Id
    @Column(name = "salesorderid")
    private Integer salesOrderId;

    @Column(name = "revisionnumber")
    private Short revisionNumber;

    @Column(name = "orderdate")
    private LocalDateTime orderDate;

    @Column(name = "duedate")
    private LocalDateTime dueDate;

    @Column(name = "shipdate")
    private LocalDateTime shipDate;

    @Column(name = "status")
    private Short status;

    @Column(name = "onlineorderflag")
    private Boolean onlineOrderFlag;

    @Column(name = "salesordernumber")
    private String salesOrderNumber;

    @Column(name = "purchaseordernumber")
    private String purchaseOrderNumber;

    @Column(name = "accountnumber")
    private String accountNumber;

    @Column(name = "customerid")
    private Integer customerId;

    @Column(name = "salespersonid")
    private Integer salesPersonId;

    @Column(name = "territoryid")
    private Integer territoryId;

    @Column(name = "billtoaddressid")
    private Integer billToAddressId;

    @Column(name = "shiptoaddressid")
    private Integer shipToAddressId;

    @Column(name = "shipmethodid")
    private Integer shipMethodId;

    @Column(name = "creditcardid")
    private Integer creditCardId;

    @Column(name = "creditcardapprovalcode")
    private String creditCardApprovalCode;

    @Column(name = "currencyrateid")
    private Integer currencyRateId;

    @Column(name = "subtotal")
    private BigDecimal subTotal;

    @Column(name = "taxamt")
    private BigDecimal taxAmt;

    @Column(name = "freight")
    private BigDecimal freight;

    @Column(name = "totaldue")
    private BigDecimal totalDue;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rowguid")
    private UUID rowGuid;

    @Column(name = "modifieddate")
    private LocalDateTime modifiedDate;
}
