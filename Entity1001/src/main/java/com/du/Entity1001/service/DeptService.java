package com.du.Entity1001.service;

import com.du.Entity1001.entity.Dept;
import com.du.Entity1001.repository.DeptRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class DeptService {
    private final DeptRepository deptRepository;

    public void save(Dept dept){
        deptRepository.save(dept);
    }

    public Dept getById(Long id){
        return deptRepository.findById(id);
    }

    public List<Dept> getAll(){
        return deptRepository.findAll();
    }
}
