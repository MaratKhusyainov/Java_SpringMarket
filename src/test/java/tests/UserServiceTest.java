package tests;

import geekbrains.Entity.User;
import geekbrains.Repository.UserRepository;
import geekbrains.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private static final String USER_NAME = "Pop";
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetUser() {
        User expectedUser = new User();
        expectedUser.setId(3L);
        expectedUser.setUsername(USER_NAME);
        expectedUser.setPassword("10");
        expectedUser.setEmail("pop@mail.ru");

        when(userRepository.findByUsername(USER_NAME)).thenReturn(Optional.of(expectedUser));

        User testUser = userService.findByUsername(USER_NAME).get();
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq(USER_NAME));
        Assertions.assertEquals(expectedUser, testUser);
    }
}