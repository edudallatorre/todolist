package com.edudallatorre.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModal, UUID>{
    UserModal findByUsername(String username);
}
