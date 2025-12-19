package de.unibayreuth.se.taskboard.business.impl;

import de.unibayreuth.se.taskboard.business.domain.User;
import de.unibayreuth.se.taskboard.business.exceptions.DuplicateNameException;
import de.unibayreuth.se.taskboard.business.exceptions.UserNotFoundException;
import de.unibayreuth.se.taskboard.business.ports.UserPersistenceService;
import de.unibayreuth.se.taskboard.business.ports.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPersistenceService userPersistenceService;

    @Override
    public void clear() {
        userPersistenceService.clear();
    }

    @Override
    @NonNull
    public List<User> getAllUsers() {
        return userPersistenceService.getAll();
    }

    @Override
    @NonNull
    public User getById(UUID id) throws UserNotFoundException {
        return userPersistenceService.getById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " does not exist."));
    }

    @Override
    @NonNull
    public User create(User user) throws DuplicateNameException {
        user.setId(null);
        try {
            return userPersistenceService.upsert(user);
        } catch (UserNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}