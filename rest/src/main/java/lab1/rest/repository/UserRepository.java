package lab1.rest.repository;

import lab1.rest.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Qualifier("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    List <User> findAll();
    User findUserByLogin(String login);
}
