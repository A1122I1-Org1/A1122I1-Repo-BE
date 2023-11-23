package com.example.be.controller;

import com.example.be.dto.JwtDTO;
import com.example.be.dto.PasswordChangeDTO;
import com.example.be.dto.SignInDTO;
import com.example.be.entity.Account;
import com.example.be.jwt.JwtTokenProvider;
import com.example.be.security.UserPrinciple;
import com.example.be.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInDTO signInDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDTO.getUserName(), signInDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String token = jwtTokenProvider.genarateToken(userPrinciple);
        return ResponseEntity.
                ok(new JwtDTO(token, userPrinciple.getUsername(), userPrinciple.getAuthorities()));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account users = iAccountService.findByUserName(userPrinciple.getUsername());
        if (passwordEncoder.matches(passwordChangeDTO.getOldPassword(), users.getPassword())) {
            if (passwordChangeDTO.getConfimPassword().equalsIgnoreCase(passwordChangeDTO.getNewPassword())) {
                String encodedPassword = passwordEncoder.encode(passwordChangeDTO.getNewPassword());
                users.setPassword(encodedPassword);
                iAccountService.changePassword(users);
                return new ResponseEntity<>("Đổi mật khẩu thành công", HttpStatus.OK);
            }
            return new ResponseEntity<>("Mật khẩu mới và mật khẩu xác nhận không trùng khớp", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Mật khẩu cũ không đúng", HttpStatus.BAD_REQUEST);
    }


}