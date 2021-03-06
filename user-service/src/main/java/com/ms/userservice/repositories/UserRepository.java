package com.ms.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.userservice.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
