package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage {

    private final Map<Long, User> users = new HashMap<>();

    private long nextId = 1;

    @Override
    public Collection<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User add(User user) {
        user.setId(getNextId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User newUser) {
        users.replace(newUser.getId(), newUser);
        return newUser;
    }

    @Override
    public boolean contains(Long id) {
        return users.containsKey(id);
    }

    @Override
    public Optional<User> getUser(long id) {
        return Optional.ofNullable(users.get(id));
    }

    private long getNextId() {
        return nextId++;
    }
}
