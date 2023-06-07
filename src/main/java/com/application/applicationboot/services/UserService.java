package com.application.applicationboot.services;

import com.application.applicationboot.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findOne(long id);

    public void saveUser(User user);

    public void update(long id, User updateUser);

    public void deleteUser(long id);
}
