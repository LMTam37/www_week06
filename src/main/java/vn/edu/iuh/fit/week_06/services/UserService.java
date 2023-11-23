package vn.edu.iuh.fit.week_06.services;

import vn.edu.iuh.fit.week_06.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long userId);
    Optional<User> getUserByEmail(String email);
    User saveUser(User user);
    void deleteUser(Long userId);
}
