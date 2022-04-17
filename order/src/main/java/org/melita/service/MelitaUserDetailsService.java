package org.melita.service;

import org.melita.repository.UserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MelitaUserDetailsService implements UserDetailsService {

    private UserDetailsRepository userDetailsRepository;

    public MelitaUserDetailsService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<org.melita.entity.User> user = this.userDetailsRepository.findById(username);
        if(user.isPresent()) {
            return new User(user.get().getUserName(), user.get().getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException("Invalid User");
    }
}
