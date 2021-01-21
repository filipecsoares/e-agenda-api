package com.filipe.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filipe.agenda.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
