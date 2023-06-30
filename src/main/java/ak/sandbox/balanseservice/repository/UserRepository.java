package ak.sandbox.balanseservice.repository;

import ak.sandbox.balanseservice.entity.History;
import ak.sandbox.balanseservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
