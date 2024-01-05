package com.buddy.api.controller.pool.response;

import com.buddy.api.domain.Pool;
import com.buddy.api.domain.Profile;
import lombok.Getter;

@Getter
public class PoolResponse {
    private String name;
    private String region;
    private int depth;
    private Boolean equipmentRent;
    private Double latitude;
    private Double longitude;

    public PoolResponse(String name, String region, int depth, Boolean equipmentRent, Double latitude, Double longitude) {
        this.name = name;
        this.region = region;
        this.depth = depth;
        this.equipmentRent = equipmentRent;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static PoolResponse of(Pool pool) {
        return new PoolResponse(pool.getName(), pool.getRegion(),pool.getDepth(),pool.getEquipmentRent(),pool.getLocation().getX(),pool.getLocation().getY());
    }
}
