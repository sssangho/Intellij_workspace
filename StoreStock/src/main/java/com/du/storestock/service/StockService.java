package com.du.storestock.service;

import com.du.storestock.entity.Product;
import com.du.storestock.entity.StockHistory;
import com.du.storestock.entity.StockType;
import com.du.storestock.repository.ProductRepository;
import com.du.storestock.repository.StockHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {
    private final ProductRepository productRepository;
    private final StockHistoryRepository stockHistoryRepository;

    public StockService(ProductRepository productRepository, StockHistoryRepository stockHistoryRepository) {
        this.productRepository = productRepository;
        this.stockHistoryRepository = stockHistoryRepository;
    }

    /**
     * 입출고 기록 추가 + Product 재고 자동 갱신
     */
    @Transactional
    public StockHistory addStockHistory(Long productId, int quantity, StockType type, String note) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        // 재고 계산
        int changeQuantity = (type == StockType.IN) ? quantity : -quantity;
        product.setStock_quantity(product.getStock_quantity() + changeQuantity);

        // Product 저장 (재고 갱신)
        productRepository.save(product);

        // StockHistory 생성
        StockHistory history = StockHistory.builder()
                .product(product)
                .changeQuantity(changeQuantity)
                .type(type)
                .note(note)
                .createdAt(LocalDateTime.now())
                .build();

        return stockHistoryRepository.save(history);
    }

    public List<StockHistory> getStockHistory(Long productId) {
        return stockHistoryRepository.findByProductId(productId);
    }
}
