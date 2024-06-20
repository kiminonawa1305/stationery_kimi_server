package com.lamnguyen.stationery_kimi.enums;

public enum BillStatusEnum {
    ORDERED("Đã đặt hàng"),
    ON_THE_WAY("Đang giao hàng"),
    ARRIVED_AT_THE_WAREHOUSE("Đã về kho"),
    DELIVERED("Đã giao hàng");

    private String status;

    BillStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
