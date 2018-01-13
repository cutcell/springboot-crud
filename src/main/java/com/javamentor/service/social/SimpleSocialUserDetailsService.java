package com.javamentor.service.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class SimpleSocialUserDetailsService implements SocialUserDetailsService {

    private UserDetailsService userDetailsService;

    @Autowired
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
