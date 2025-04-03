package com.showroommanagement.service;

<<<<<<< HEAD
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
=======
import com.showroommanagement.dto.SignInDTO;
import com.showroommanagement.dto.SignUpDTO;
import com.showroommanagement.entity.User;
import com.showroommanagement.exception.BadRequestServiceAlertException;
import com.showroommanagement.repository.UserRepository;
import com.showroommanagement.util.Constant;
import com.showroommanagement.util.UserCredentialValidation;
import com.showroommanagement.util.UserType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {

    private final JWTService jwtService;

    private final UserRepository userRepository;

    private final UserCredentialValidation userCredentialValidation;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Transactional
    public User adminCreate(final SignUpDTO signUpDTO) {
        if (!userCredentialValidation.isValidEmail(signUpDTO.getEmail())) {
            throw new BadRequestServiceAlertException("Invalid Email format");
        }
        if (userRepository.existsByEmail(signUpDTO.getEmail())) {
            throw new BadRequestServiceAlertException(Constant.EXIST_ACCOUNT);
        }
        final User user = new User();
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(encoder.encode(signUpDTO.getPassword()));
        user.setUserType(UserType.ADMIN);
>>>>>>> 1bb0e5d (first commit)
        return this.userRepository.save(user);
    }

    @Transactional
<<<<<<< HEAD
    public User createSignIn(final User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).orElseThrow(() -> new UserNotExistException("Invalid email or password. Please try again."));
    }

    public User retrieveUserById(final Integer id) {
        return this.userRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
=======
    public User employeeCreate(final SignUpDTO signUpDTO) {
        if (!userCredentialValidation.isValidEmail(signUpDTO.getEmail())) {
            throw new BadRequestServiceAlertException("Invalid Email format");
        }
        if (userRepository.existsByEmail(signUpDTO.getEmail())) {
            throw new BadRequestServiceAlertException(Constant.EXIST_ACCOUNT);
        }
        final User user = new User();
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(encoder.encode(signUpDTO.getPassword()));
        user.setUserType(UserType.EMPLOYEE);
        return this.userRepository.save(user);
    }

    @Transactional
    public User customerCreate(final SignUpDTO signUpDTO) {
        if (!userCredentialValidation.isValidEmail(signUpDTO.getEmail())) {
            throw new BadRequestServiceAlertException("Invalid Email format");
        }
        if (userRepository.existsByEmail(signUpDTO.getEmail())) {
            throw new BadRequestServiceAlertException(Constant.EXIST_ACCOUNT);
        }
        final User user = new User();
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(encoder.encode(signUpDTO.getPassword()));
        user.setUserType(UserType.CUSTOMER);
        return this.userRepository.save(user);
    }

    @Transactional
    public Map<String, String> signIn(final SignInDTO signInDTO) {
        if (!userCredentialValidation.isValidEmail(signInDTO.getEmail())) {
            throw new BadRequestServiceAlertException("Invalid Email format");
        }
        final User user = this.userRepository.findByEmail(signInDTO.getEmail()).orElseThrow(() -> new RuntimeException(Constant.INCORRECT_EMAIL));
        if (!encoder.matches(signInDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException(Constant.INCORRECT_PASSWORD);
        }
        final String jwt = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        final Map<String, String> jwtAuthResp = new HashMap<>();
        jwtAuthResp.put("token", jwt);
        jwtAuthResp.put("refreshToken", refreshToken);
        return jwtAuthResp;
    }

    @Transactional
    public Map<String, String> refreshToken(final String refreshToken) {
        final String userEmail = jwtService.extractUserName(refreshToken);
        final User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException(Constant.INCORRECT_EMAIL));
        if (jwtService.isTokenValid(refreshToken, user)) {
            final var jwt = jwtService.generateToken(user);
            final Map<String, String> response = new HashMap<>();
            response.put("token", jwt);
            response.put("refreshToken", refreshToken);
            return response;
        }
        throw new BadRequestServiceAlertException(Constant.INVALID_TOKEN);
    }

    public User retrieveUserById(final int id) {
        return this.userRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.USER_NOT_FOUND));
>>>>>>> 1bb0e5d (first commit)
    }

    public List<User> retrieveUser() {
        return this.userRepository.findAll();
    }

<<<<<<< HEAD
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
=======
    public User deleteById(final int id) {
        final User user = this.userRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        this.userRepository.deleteById(id);
        return user;
    }
}
>>>>>>> 1bb0e5d (first commit)
