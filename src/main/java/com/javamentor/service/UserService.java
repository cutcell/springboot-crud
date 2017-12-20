package com.javamentor.service;

import com.javamentor.model.User;

public interface UserService extends CrudService<User> {

    User findByUsername(String username);

}
