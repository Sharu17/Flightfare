package com.fbs.flightfareservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fbs.flightfareservice.models.User;

public interface UserRepository extends JpaRepository<User, String> {

	public Optional<User> findById(String id);

	@Query("select u from User u where u.username=?1")
	public User findByUsername(String username);
}
