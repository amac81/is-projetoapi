package pt.arnaldocanelas.projetoapi.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.arnaldocanelas.projetoapi.dto.AuthRequestDTO;
import pt.arnaldocanelas.projetoapi.dto.AuthResponseDTO;
import pt.arnaldocanelas.projetoapi.repositories.UserRepository;
import pt.arnaldocanelas.projetoapi.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
                )
            );
        } catch (AuthenticationException e) {
            throw new SecurityException("Invalid credentials.");
        }

        UserDetails userDetails = userRepository.findByUsername(authRequest.getUsername())
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles("USER") // ou outras roles se usares
                        .build())
                .orElseThrow(() -> new SecurityException("User not found."));

        String token = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthResponseDTO(token);
    }
}