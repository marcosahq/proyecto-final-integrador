package com.app.hotel.users.repository;

import com.app.hotel.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer id);
    List<User> findAll();
}
