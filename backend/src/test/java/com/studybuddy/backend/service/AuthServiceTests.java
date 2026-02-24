package com.studybuddy.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.studybuddy.backend.dto.auth.SignupRequest;
import com.studybuddy.backend.entity.auth.User;
import com.studybuddy.backend.repository.UserRepository;
import com.studybuddy.backend.service.auth.AuthService;
import com.studybuddy.backend.service.auth.EmailService;
import com.studybuddy.backend.utility.auth.JwtUtil;
import com.studybuddy.backend.utility.auth.VerificationCodeGenerator;

@ExtendWith(MockitoExtension.class)
class AuthServiceTests {
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    EmailService emailService;
    @Mock
    VerificationCodeGenerator codeGenerator;
    @InjectMocks
    AuthService authService;

    @Test
    void signup_withValidInput_returnsSuccessMessage() {
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(codeGenerator.generateCode()).thenReturn("123456");
        when(userRepository.save(any())).thenReturn(new User());

        SignupRequest req = new SignupRequest();
        req.setEmail("test@example.com");
        req.setUsername("username");
        req.setPassword("Password1!");
        req.setConfirmPassword("Password1!");
        Map<String, String> response = authService.signup(req);

        assertEquals("test@example.com", response.get("email"));

        verify(emailService).sendCodeEmail(eq("test@example.com"), eq("123456"), eq("Verification"));
    }
}
