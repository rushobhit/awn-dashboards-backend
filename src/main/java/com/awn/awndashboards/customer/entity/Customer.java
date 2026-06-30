package com.awn.awndashboards.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer", schema = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @Column(name = "customerid")
    private Integer customerId;

    @Column(name = "personid")
    private Integer personId;

    @Column(name = "storeid")
    private Integer storeId;

    @Column(name = "territoryid")
    private Integer territoryId;

    @Column(name = "accountnumber")
    private String accountNumber;

    @Column(name = "rowguid")
    private UUID rowGuid;

    @Column(name = "modifieddate")
    private LocalDateTime modifiedDate;
}
