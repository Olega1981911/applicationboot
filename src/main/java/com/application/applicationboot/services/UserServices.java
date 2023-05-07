package com.application.applicationboot.services;


import com.application.applicationboot.models.User;
import com.application.applicationboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServices {
    private final UserRepository userRepository;


    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User findOne(long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }


    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }


    @Transactional
    public void update(long id, User updateUser) {
        updateUser.setId(id);
        userRepository.save(updateUser);
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
