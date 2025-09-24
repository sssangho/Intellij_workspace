package com.du.memo0924.service;

import com.du.memo0924.domain.Memo;
import com.du.memo0924.mapper.MemoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoMapper memoMapper;

    public List<Memo> findAll(){
        return memoMapper.findAll();
    }

    public Memo findById(Long id){
        return memoMapper.findById(id);
    }

    public void create(Memo memo){
        memoMapper.insert(memo);
    }

    public void update(Memo memo){
        memoMapper.update(memo);
    }

    public void delete(Long id){
        memoMapper.delete(id);
    }
}
