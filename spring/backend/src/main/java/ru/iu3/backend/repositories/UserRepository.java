package ru.iu3.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.iu3.backend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
      Optional<User> findByLogin(String login);  
      Optional<User> findByToken(String token); 

}
