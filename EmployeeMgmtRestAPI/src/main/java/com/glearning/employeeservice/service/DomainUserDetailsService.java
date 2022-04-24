package com.glearning.employeeservice.service;

import com.glearning.employeeservice.model.DomainUserDetails;
import com.glearning.employeeservice.model.User;
import com.glearning.employeeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User validDomainUser = this.userRepository.findByName(username)
                                                  .orElseThrow(() -> new UsernameNotFoundException("invalid username password"));
        return new DomainUserDetails(validDomainUser);
    }
}
