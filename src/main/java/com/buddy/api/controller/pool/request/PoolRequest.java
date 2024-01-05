package com.buddy.api.controller.pool.request;

import lombok.Getter;

@Getter
public class PoolRequest {
    private String name;
    private String region;
    private int depth;
    private Boolean equipmentRent;
    private Double latitude;
    private Double longitude;

    public PoolRequest(String name, String region, int depth, Boolean equipmentRent, Double latitude, Double longitude) {
        this.name = name;
        this.region = region;
        this.depth = depth;
        this.equipmentRent = equipmentRent;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
