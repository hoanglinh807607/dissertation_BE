package com.dissertation.userservice.repository;

import com.dissertation.common.entities.user_service.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    @Query("select r from Role r where LOWER(r.name) like LOWER(concat('%', :keyword,'%')) and r.isDeleted = false")
    Page<Role> findAll(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select (case when exists (select id from users where id in ( select user_id from user_role where role_id = :roleId) and is_deleted = false) then false else true end) as can_delete_or_disable", nativeQuery= true)
    Boolean canDeleteOrDisable(@Param("roleId") Integer roleId);

    @Query("select r from Role r where (LOWER(r.name) like LOWER(concat('%', :keyword,'%'))) and r.status = :status and r.isDeleted = false")
    Page<Role> findByStatus(@Param("keyword") String keyword, @Param("status") Boolean status, Pageable pageable);

    List<Role> findByCodeIn(Collection<String> codes);
}
