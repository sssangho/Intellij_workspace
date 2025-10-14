package com.du.storestock.repository;

import com.du.storestock.entity.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockHistoryRepository extends JpaRepository<StockHistory,Long> {
    List<StockHistory> findByProductId(Long productId);
}
