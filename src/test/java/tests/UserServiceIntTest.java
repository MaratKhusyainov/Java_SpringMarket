package tests;

import geekbrains.Entity.User;
import geekbrains.Repository.UserRepository;
import geekbrains.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceIntTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void getUserTest() {
        User user = userRepository.findByUsername("Peter").get();
        UserDetails expectedUser = userService.loadUserByUsername("Peter");
        Assertions.assertEquals(expectedUser.getUsername(), user.getUsername());
        Assertions.assertEquals(expectedUser.getPassword(), user.getPassword());
    }
}
