package lab1.rest.service;

import lab1.rest.model.User;
import lab1.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@ComponentScan
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public String saveUser(Map<String, String> requestParams) {
        User user = new User();
        user.setLogin(requestParams.get("login"));
        user.setPassword(requestParams.get("password"));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    public boolean checkIfUserExists(String login){
        return userRepository.findUserByLogin(login).isPresent();
    }


    public Optional<User> findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    public boolean updateUser(String login, Map<String, String> requestParams) {
        Optional<User> user = findUserByLogin(login);
        user.ifPresent(u -> {
            u.setPassword(requestParams.get("password"));
            userRepository.save(u);
        });
        return user.isPresent();
    }

    public boolean deleteUser(String login) {
        Optional<User> user = findUserByLogin(login);
        user.ifPresent(it -> userRepository.delete(it));

        return user.isPresent();
    }
}
