package kz.aitu.endterm.services.interfaces;



import kz.aitu.endterm.models.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAll();
    User create(User user);
    List<User> getByUsername(String surname);
}
