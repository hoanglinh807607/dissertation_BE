package com.dissertation.userservice.repository;

import com.dissertation.common.entities.user_service.User;
import com.mongodb.lang.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    @Override
    @EntityGraph(attributePaths = "roles")
    Optional<User> findById(Integer id);

    @Override
    @EntityGraph(attributePaths = "roles")
    Page<User> findAll(@Nullable Specification<User> spec, Pageable pageable);

    @EntityGraph(attributePaths = "roles")
    User findByEmailAddressIgnoreCase(String email);

    @Query("Select u from User u where u.emailAddress = ?1")
    Optional<User> findByEmail(String email);
}
