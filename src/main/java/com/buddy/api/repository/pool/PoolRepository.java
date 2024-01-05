package com.buddy.api.repository.pool;

import com.buddy.api.domain.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolRepository extends JpaRepository<Pool, Integer> {
    Pool findByName(String name);
}
