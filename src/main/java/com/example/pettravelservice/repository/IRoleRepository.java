package com.example.pettravelservice.repository;

import com.example.pettravelservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
