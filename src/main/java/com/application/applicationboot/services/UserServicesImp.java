package com.application.applicationboot.services;


import com.application.applicationboot.models.User;
import com.application.applicationboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserServicesImp implements UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserServicesImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    @Transactional
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public User findOne(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(long id, User updateUser) {
        updateUser.setId(id);
        userRepository.save(updateUser);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);

    }
}
