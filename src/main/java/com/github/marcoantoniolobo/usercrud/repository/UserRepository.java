package com.github.marcoantoniolobo.usercrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.marcoantoniolobo.usercrud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}
