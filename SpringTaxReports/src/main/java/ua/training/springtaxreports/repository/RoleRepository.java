package ua.training.springtaxreports.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.training.springtaxreports.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
