package com.alvaro.springsecurity.service.auth;

import com.alvaro.springsecurity.dto.auth.AuthenticationRequest;
import com.alvaro.springsecurity.dto.auth.AuthenticationResponse;
import com.alvaro.springsecurity.dto.RegisteredUser;
import com.alvaro.springsecurity.dto.SaveUser;
import com.alvaro.springsecurity.exception.ObjectNotFoundException;
import com.alvaro.springsecurity.persistence.entitiy.User;
import com.alvaro.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public RegisteredUser registerOneCustomer(SaveUser newUser) {
        User user = userService.registerOneCustomer(newUser);

        RegisteredUser userDto = new RegisteredUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userDto.setJwt(jwt);

        return userDto;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("name", user.getName());
            extraClaims.put("role", user.getRole().name());
            extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest authRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        );

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findOneByUsername(authRequest.getUsername()).get();
        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));

        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setJwt(jwt);

        return authResponse;
    }

    public boolean validateToken(String jwt) {

        try {
            jwtService.extractUsername(jwt);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User findLoggedInUser() {
        Authentication auth = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getPrincipal().toString();

        return  userService.findOneByUsername(username)
                    .orElseThrow(() -> new ObjectNotFoundException("User not found. Username " + username));

    }
}