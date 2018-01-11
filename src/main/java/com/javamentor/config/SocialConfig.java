package com.javamentor.config;

import com.javamentor.service.RoleService;
import com.javamentor.service.UserService;
import com.javamentor.service.social.SimpleConnectionSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    DataSource dataSource;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConf, Environment env) {

        cfConf.addConnectionFactory(
                new GoogleConnectionFactory(
                        env.getProperty("google.client-id"),
                        env.getProperty("google.client-secret"))
        );

        cfConf.addConnectionFactory(
                new VKontakteConnectionFactory(
                        env.getProperty("vk.client-id"),
                        env.getProperty("vk.client-secret")
                )
        );

    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(
                dataSource, connectionFactoryLocator, Encryptors.noOpText());
        repository.setConnectionSignUp(new SimpleConnectionSignUpService(userService, roleService));
        return repository;

    }

}
