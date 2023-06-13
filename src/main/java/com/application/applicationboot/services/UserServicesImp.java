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
public class UserServicesImp implements UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserServicesImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public User findOne(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(long id, User updateUser) {
        updateUser.setId(updateUser.getId());
        userRepository.save(updateUser);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);

    }
}
