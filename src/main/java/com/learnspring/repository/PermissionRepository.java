package com.learnspring.repository;

import com.learnspring.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PermissionRepository extends JpaRepository<Permission, String> {

}
