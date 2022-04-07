package com.dissertation.userservice.repository;

import com.dissertation.common.entities.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Integer> {
}
