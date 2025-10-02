package com.du.project1.service;

import com.du.project1.entity.MyUser;
import com.du.project1.repository.MyUserRepository;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {
    private final MyUserRepository repo;

    public MyUserService(MyUserRepository repo) {
        this.repo = repo;
    }

    public void register(MyUser user) {
        repo.save(user);
    }

    public MyUser login(String email, String password) {
        return repo.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }
}
