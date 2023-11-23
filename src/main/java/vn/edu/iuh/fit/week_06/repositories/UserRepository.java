package vn.edu.iuh.fit.week_06.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.week_06.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
