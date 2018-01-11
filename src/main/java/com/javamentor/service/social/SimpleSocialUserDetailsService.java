package com.javamentor.service.social;

import com.javamentor.config.SocialConfig;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.Arrays;

public class SimpleSocialUserDetailsService implements SocialUserDetailsService {

    private UserDetailsService userDetailsService;

    public SimpleSocialUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {

        UserDetails userDetails = userDetailsService.loadUserByUsername(s);
        return new SocialUser(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());

    }
}
