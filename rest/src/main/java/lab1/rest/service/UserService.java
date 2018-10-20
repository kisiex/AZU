package lab1.rest.service;

import lab1.rest.model.User;
import lab1.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public void saveUser(Map<String, String> requestParams) {
        User user = new User();
        user.setLogin(requestParams.get("login"));
        user.setPassword(requestParams.get("password"));

        userRepository.save(user);
    }

    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    public void updateUser(User user, Map<String, String> requestParams) {
        user.setPassword(requestParams.get("password"));
        userRepository.save(user);
    }

    public boolean deleteUser(String login) {
        User user = findUserByLogin(login);
        if (user != null) {
            userRepository.delete(user);
        }
        return user != null;
    }
}
