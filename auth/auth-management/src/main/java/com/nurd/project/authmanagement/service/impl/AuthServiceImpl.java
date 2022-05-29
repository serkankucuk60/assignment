package com.nurd.project.authmanagement.service.impl;

import com.nurd.project.authmanagement.dto.request.LoginRequestDTO;
import com.nurd.project.authmanagement.dto.request.SignupRequestDTO;
import com.nurd.project.authmanagement.dto.response.JwtResponseDTO;
import com.nurd.project.authmanagement.dto.response.MessageResponseDTO;
import com.nurd.project.authmanagement.entity.Role;
import com.nurd.project.authmanagement.entity.RoleEnum;
import com.nurd.project.authmanagement.entity.User;
import com.nurd.project.authmanagement.jwt.JwtUtils;
import com.nurd.project.authmanagement.repo.RoleRepository;
import com.nurd.project.authmanagement.repo.UserRepository;
import com.nurd.project.authmanagement.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    JwtUtils jwtUtils;
    AuthenticationManager authenticationManager;
    UserRepository userRepository;
     RoleRepository roleRepository;
    PasswordEncoder encoder;

    @Override
    public JwtResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequestDTO.getUsername(),
                                loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponseDTO(jwt,
                                userDetails.getId(),
                                userDetails.getUsername(),
                                userDetails.getEmail(),
                                roles);
    }

    @Override
    public ResponseEntity registerUser(SignupRequestDTO signUpRequestDTO) {
        if (userRepository.existsByUsername(signUpRequestDTO.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequestDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequestDTO.getUsername(),
                            signUpRequestDTO.getEmail(),
                            encoder.encode(signUpRequestDTO.getPassword()));

        Set<String> strRoles = signUpRequestDTO.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponseDTO("User registered successfully!"));
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


}
