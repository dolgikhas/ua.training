package ua.training.springtaxreports.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.training.springtaxreports.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername( String username );
}
