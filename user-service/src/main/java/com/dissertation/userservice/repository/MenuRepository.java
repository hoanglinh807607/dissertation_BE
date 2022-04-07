package com.dissertation.userservice.repository;

import com.dissertation.common.entities.user_service.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("Select m from Menu m where m.roleId = ?1")
    List<Menu> findByRoleId(Integer roleId);
}
