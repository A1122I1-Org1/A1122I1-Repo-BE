package com.example.be.security;

import com.example.be.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
public class UserPrinciple implements UserDetails {

    private Integer userId;
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public static UserPrinciple mapUserToUserPrinciple(Account account) {
        /*GrantedAuthority là một interface trong Spring Security, đại diện cho một quyền hạn được cấp cho Authentication*/
        List<GrantedAuthority> authorities = account.getRoles().stream().
                map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
        /* SimpleGrantedAuthority là một lớp cơ bản trong Spring Security, nó cung cấp một cách đơn giản để tạo ra một GrantedAuthority*/
        return new UserPrinciple(account.getAccountId(), account.getUsername(), account.getPassword(), authorities);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
