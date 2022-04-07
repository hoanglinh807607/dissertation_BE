package com.dissertation.userservice.security.services;
import com.dissertation.common.entities.user_service.User;
import com.dissertation.common.security.UserDetailsImpl;
import com.dissertation.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with emailAddress: " + emailAddress));

        return UserDetailsImpl.build(user);
    }

}