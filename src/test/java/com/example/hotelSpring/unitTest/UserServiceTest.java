package com.example.hotelSpring.unitTest;

import com.example.hotelSpring.entity.User;
import com.example.hotelSpring.repository.UserDAO;
import com.example.hotelSpring.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDAO userDAO;
    @InjectMocks
    private UserService userService;


    @Test
    public void saveTest() {
        User user = new User();
        user.setUserName("user");
        user.setPassword("user");
        ResponseEntity<String> resp = userService.save(user);
        assertEquals("New user saved", resp.getBody());
    }
}
