package com.application.applicationboot.services;

import com.application.applicationboot.models.User;


import java.util.List;

public interface UserService {

   List<User> findAll();

    User findOne(long id);

    void saveUser(User user);

   void update(long id, User updateUser);

    void deleteUser(long id);
}
