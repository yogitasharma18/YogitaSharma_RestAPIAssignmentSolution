package com.glearning.employeeservice.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class DomainUserDetails implements UserDetails {

    //domain user
    private final User user;


    public DomainUserDetails(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //take the roles of the user
        //convert each role to granted authority
        //put the granted authority to a collection and send back
        return this.user.getRoles()
                            .stream()
                                .map(Role::getRoleName)
                                .map(roleName -> "ROLE_" + roleName)
                                .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
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
