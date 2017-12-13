package com.javamentor.service;

import com.javamentor.domain.User;

public interface UserService extends CrudService<User> {

    User findByUsername(String username);

}
