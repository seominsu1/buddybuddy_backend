package com.buddy.api.controller.pool;

import com.buddy.api.controller.pool.request.PoolRequest;
import org.springframework.http.ResponseEntity;

public interface PoolApiSpec {

    ResponseEntity findAll();

    ResponseEntity save(PoolRequest request);
    ResponseEntity update(String memberId, PoolRequest request);
}
