package geekbrains.Controller;

import geekbrains.Entity.User;
import geekbrains.Exception.MarketError;
import geekbrains.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        if (userService.findByUsernameAndEmail(user.getUsername(),user.getEmail()).isPresent()){
            return new ResponseEntity<>(new MarketError(HttpStatus.CONFLICT.value(), "This user already exist"), HttpStatus.CONFLICT);
        } else {
            userService.saveUser(user);
            return ResponseEntity.ok(HttpStatus.CREATED);
        }
    }
}
