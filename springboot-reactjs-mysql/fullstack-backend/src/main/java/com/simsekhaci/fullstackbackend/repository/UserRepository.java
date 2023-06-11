package com.simsekhaci.fullstackbackend.repository;

import com.simsekhaci.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
