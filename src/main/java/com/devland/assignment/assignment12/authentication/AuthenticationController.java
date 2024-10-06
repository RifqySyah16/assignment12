package com.devland.assignment.assignment12.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devland.assignment.assignment12.applicationuser.ApplicationUserService;
import com.devland.assignment.assignment12.applicationuser.dto.RegisterationRequestDTO;
import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.authentication.dto.JwtResponseDTO;
import com.devland.assignment.assignment12.authentication.dto.LoginRequestDTO;
import com.devland.assignment.assignment12.authentication.jwt.JwtProvider;
import com.devland.assignment.assignment12.file.FileService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final ApplicationUserService applicationUserService;
    private final FileService fileService;

    @PostMapping("/tokens")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        logger.info("Attemp Login To System");
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponseDTO(jwt));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<JwtResponseDTO> registrasion(
            @RequestPart("registrasionRequestDTO") @Valid RegisterationRequestDTO registrasionRequestDTO,
            @RequestPart("file") MultipartFile file) {
        ApplicationUser newUser = registrasionRequestDTO.convertToEntity();
        if (file != null && !file.isEmpty()) {
            this.fileService.savePhoto(newUser, file);
        }

        this.applicationUserService.save(newUser);

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registrasionRequestDTO.getUsername(),
                        registrasionRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.status(HttpStatus.CREATED).body(new JwtResponseDTO(jwt));

    }

}
