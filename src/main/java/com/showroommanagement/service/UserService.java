package com.showroommanagement.service;

import com.showroommanagement.entity.User;
import com.showroommanagement.exception.BadRequestServiceAlertException;
import com.showroommanagement.exception.UserExistsException;
import com.showroommanagement.exception.UserNotExistException;
import com.showroommanagement.repository.UserRepository;
import com.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional

    public User createSignUp(final User user) {
        User existingUser = this.userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new UserExistsException(Constant.EXIST_MAIL);
        }
        return this.userRepository.save(user);
    }

    @Transactional
    public User createSignIn(final User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).orElseThrow(() -> new UserNotExistException("Invalid email or password. Please try again."));
    }

    public User retrieveUserById(final Integer id) {
        return this.userRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
    }

    public List<User> retrieveUser() {
        return this.userRepository.findAll();
    }

    @Transactional
    public User updateUserById(final User user, final Integer id) {
        final User existingUser = this.userRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getId() != null) {
            existingUser.setId(user.getId());
        }
        if (user.getUserType() != null) {
            existingUser.setUserType(user.getUserType());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        if (user.getCreatedAt() != null) {
            existingUser.setCreatedAt(user.getCreatedAt());
        }

        return this.userRepository.save(existingUser);
    }

    public User removeUserById(final Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        this.userRepository.deleteById(id);
        return user;
    }
}
