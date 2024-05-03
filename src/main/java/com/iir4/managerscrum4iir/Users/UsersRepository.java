package com.iir4.managerscrum4iir.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByEmailAndPassword(String email, String password);
}