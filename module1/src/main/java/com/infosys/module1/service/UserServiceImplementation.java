package com.infosys.module1.service;

import com.infosys.module1.exception.Module1Exception;
import com.infosys.module1.security.JwtProvider;
import com.infosys.module1.info.LoginDto;
import com.infosys.module1.model.User;
import com.infosys.module1.repository.UserRepository;
import com.infosys.module1.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signUp(User user) throws Module1Exception {
        // Check if the email is already in use
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new Module1Exception("An account with this email address already exists.");
        }

        // Create a new user and set up their details
        User newUser = new User();
        newUser.setEmail(user.getEmail());

        // Ensure password is at least 7 characters long
        if (user.getPassword().length() < 7) {
            throw new Module1Exception("Password must be at least 7 characters long.");
        }

        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());

        // Save the user and generate an authentication token
        userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        String token = JwtProvider.generateToken(authentication);

        return new AuthResponse(token, "Registration completed successfully.");
    }

    @Override
    public AuthResponse login(LoginDto loginDetails) throws Module1Exception {
        // Authenticate the user based on provided login details
        Authentication authentication = authenticate(loginDetails.getEmail(), loginDetails.getPassword());

        // Generate JWT token on successful authentication
        String token = JwtProvider.generateToken(authentication);

        return new AuthResponse(token, "Login successful!");
    }

    @Override
    public User getUser(String jwt) {
        // Retrieve user by decoding the JWT to get email
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        return userRepository.findByEmail(email);
    }

    public Authentication authenticate(String email, String password) throws Module1Exception {
        // Fetch user details based on email
        UserDetails userDetails = getUserDetails(email);

        // If user does not exist, throw an exception
        if (userDetails == null) {
            throw new Module1Exception("No user found with the provided email.");
        }

        // If the passwords do not match, throw an exception
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new Module1Exception("Incorrect password.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public UserDetails getUserDetails(String email) throws Module1Exception {
        // Fetch the user from the repository
        User user = userRepository.findByEmail(email);

        // If no user is found with the given email, throw an exception
        if (user == null) {
            throw new Module1Exception("No user found associated with this email address: " + email);
        }

        // Create list of authorities based on user role
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(user.getRole())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if ("resident".equals(user.getRole())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_RESIDENT"));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
