package com.example.hotelSpring.service;

import com.example.hotelSpring.entity.Locale;
import com.example.hotelSpring.entity.Role;
import com.example.hotelSpring.entity.User;
import com.example.hotelSpring.repository.UserDAO;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER =  LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserDAO userDAO;

    public ResponseEntity<String> save(User user){
        try {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setActive(true);
            user.setRole(Role.USER);
            userDAO.save(user);
        }catch (DataIntegrityViolationException e){
            LOGGER.trace(e.getMessage());
            return new ResponseEntity<>("Such user name already exist!", HttpStatus.NOT_ACCEPTABLE);
        }
        catch (Exception e){
            LOGGER.trace(e.getMessage());
            return new ResponseEntity<>("Internal error, user not saved", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("New user saved", HttpStatus.ACCEPTED);
    }



    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDAO.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipalImpl(user);

    }
}
