package lab1.rest.controller;

import lab1.rest.model.User;
import lab1.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "my_app")
@ComponentScan
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
//todo tylko json jest oblusigwany
    @GetMapping(value = "users",
            produces = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE})
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "users/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkIfUserExists(@PathVariable String login) {
        User user = userService.findUserByLogin(login);
        if (user != null) {
            return new ResponseEntity<>("Found user with given login", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "users")
    public void createNewUser(@RequestBody Map<String, String> requestParams) {
        this.userService.saveUser(requestParams);
    }

    @PutMapping(value = "users/{login}")
    public ResponseEntity<String> updateUsersPassword(@PathVariable String login, @RequestBody Map<String, String> requestParams) {
        User user = userService.findUserByLogin(login);
        if (user == null) {
            return new ResponseEntity<>("Cannot update this user, user not found", HttpStatus.NOT_FOUND);
        } else {
            userService.updateUser(user, requestParams);
            return new ResponseEntity<>("User updated", HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "users/{login}")
    public ResponseEntity<String> deleteUser(@PathVariable String login) {
        boolean deleted = userService.deleteUser(login);
        if (deleted) {
            return new ResponseEntity<>("User was deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete user", HttpStatus.BAD_REQUEST);
        }
    }

}
