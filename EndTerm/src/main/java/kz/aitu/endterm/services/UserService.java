package kz.aitu.endterm.services;

import kz.aitu.endterm.models.User;
import kz.aitu.endterm.repositories.UserRepositoryInterface;
import kz.aitu.endterm.services.interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements UserServiceInterface {
    private final UserRepositoryInterface repo;

    public UserService(UserRepositoryInterface repo) {
        this.repo = repo;
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User create(User user) {
        return repo.save(user);
    }

    @Override
    public List<User> getByUsername(String username) {
        return repo.findByUsername(username);
    }
}
