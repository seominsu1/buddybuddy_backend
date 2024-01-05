package com.buddy.api.controller.pool;

import com.buddy.api.controller.pool.request.PoolRequest;
import com.buddy.api.controller.pool.response.PoolResponse;
import com.buddy.api.controller.pool.response.PoolsResponse;
import com.buddy.api.domain.Pool;
import com.buddy.api.service.PoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pool")
public class PoolController implements PoolApiSpec {

    private PoolService poolService;

    public PoolController(PoolService poolService) {
        this.poolService = poolService;
    }

    @Override
    @GetMapping
    public ResponseEntity<PoolsResponse> findAll() {
        return ResponseEntity.ok(poolResponses(poolService.findAll()));
    }

    @Override
    @PostMapping()
    public ResponseEntity<PoolResponse> save(@RequestBody PoolRequest request) {
        return ResponseEntity.ok(PoolResponse.of(poolService.save(request)));
    }

    @Override
    @PutMapping("/{name}")
    public ResponseEntity<PoolResponse> update(@PathVariable String name, @RequestBody PoolRequest request) {
        return ResponseEntity.ok(PoolResponse.of(poolService.update(name, request)));
    }

    private PoolsResponse poolResponses(List<Pool> pools) {
        return PoolsResponse.of(pools.stream()
                .map(PoolResponse::of)
                .toList(), pools.size());
    }
}
