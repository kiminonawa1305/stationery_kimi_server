package com.lamnguyen.stationery_kimi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "bill_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer quantity;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "product_option_id")
    private ProductOption productOption;

    @ManyToOne
    @JoinColumn(name = "combo_product_detail_id")
    private ComboProductDetail comboProductDetail;
}
