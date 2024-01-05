package com.buddy.api.service;

import com.buddy.api.controller.pool.request.PoolRequest;
import com.buddy.api.domain.Pool;
import com.buddy.api.repository.pool.PoolRepository;
import com.buddy.api.utils.PointBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PoolService {

    private PoolRepository poolRepository;
    private PointBuilder builder;

    public PoolService(PoolRepository poolRepository) {
        this.poolRepository = poolRepository;
        this.builder = new PointBuilder();
    }

    public List<Pool> findAll() {
        return poolRepository.findAll();
    }

    public Pool save(PoolRequest request) {
        return poolRepository.save(Pool.of(request.getName(), request.getRegion(), request.getDepth(),
                request.getEquipmentRent(),builder.getPoint(request.getLatitude(), request.getLongitude())));
    }
    public Pool update(String name, PoolRequest request) {
        Pool pool = poolRepository.findByName(name);
        pool.updateTo(request.getName(),request.getRegion(),request.getDepth(),request.getEquipmentRent(),
                builder.getPoint(request.getLatitude(), request.getLongitude()));
        return pool;
    }
}
