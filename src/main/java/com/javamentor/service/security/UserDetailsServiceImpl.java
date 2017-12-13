package com.javamentor.service.security;

import com.javamentor.domain.User;
import com.javamentor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private Converter<User, UserDetails> userUserDetailsConverter;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userUserDetailsConverter.convert(userService.findByUsername(s));
    }
}
