package com.du.Entity1001.repository;

import com.du.Entity1001.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser,Long> {
}
