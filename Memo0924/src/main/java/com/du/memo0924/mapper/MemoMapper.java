package com.du.memo0924.mapper;

import com.du.memo0924.domain.Memo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoMapper {
    List<Memo> findAll();
    Memo findById(Long id);
    void insert(Memo memo);
    void update(Memo memo);
    void delete(Long id);
}
