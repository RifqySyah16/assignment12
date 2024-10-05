package com.devland.assignment.assignment12.applicationuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByUsername(String username);

}
