package kz.aitu.endterm.repositories;

import kz.aitu.endterm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositoryInterface extends JpaRepository<User, Integer> {
    List<User> findByUsername(String username);
}
