package com.buddy.api.controller.pool.response;

import lombok.Getter;

import java.util.List;

@Getter
public class PoolsResponse {

    private List<PoolResponse> pools;
    private Integer totalElement;

    public PoolsResponse(List<PoolResponse> pools, Integer totalElement) {
        this.pools = pools;
        this.totalElement = totalElement;
    }

    public static PoolsResponse of(List<PoolResponse> pools, Integer totalElement) {
        return new PoolsResponse(pools, totalElement);
    }
}
