package com.du.storestock.repository;

import com.du.storestock.entity.StockHistory;
import com.du.storestock.entity.StockType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockHistoryRepository extends JpaRepository<StockHistory,Long> {
    List<StockHistory> findByProductId(Long productId);

    List<StockHistory> findByProductIdAndType(Long productId, StockType type);
}
