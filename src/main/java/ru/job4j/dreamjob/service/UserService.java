package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.UserDbStore;

import java.util.Optional;

@Service
public class UserService {

    private final UserDbStore userStore;

    public UserService(UserDbStore userStore) {
        this.userStore = userStore;
    }

    public Optional<User> add(User user) {
        return userStore.add(user);
    }

    public Optional<User> findUserByEmailAndPass(String email, String password) {
        Optional<User> userDb = userStore.findUserBy(email);
        if (userDb.isPresent() && !password.equals(userDb.get().getPassword())) {
            return Optional.empty();
        }
        return userDb;
    }
}
