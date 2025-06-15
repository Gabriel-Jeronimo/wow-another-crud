package com.example.wow_another_crud.service;

import com.example.wow_another_crud.exceptions.UserNotFoundException;
import com.example.wow_another_crud.model.User;
import com.example.wow_another_crud.model.UserDetailsImpl;
import com.example.wow_another_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
        return new UserDetailsImpl(user);
    }
}
