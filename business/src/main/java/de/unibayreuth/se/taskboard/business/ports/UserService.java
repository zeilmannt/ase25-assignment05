package de.unibayreuth.se.taskboard.business.ports;

import de.unibayreuth.se.taskboard.business.domain.User;
import de.unibayreuth.se.taskboard.business.exceptions.DuplicateNameException;
import de.unibayreuth.se.taskboard.business.exceptions.UserNotFoundException;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface UserService {
    //TODO: Add user service interface that the controller uses to interact with the business layer.
    //TODO: Implement the user service interface in the business layer, using the existing user persistence service.
    void clear();

    @NonNull
    List<User> getAllUsers();

    @NonNull
    User getById(UUID id) throws UserNotFoundException;

    @NonNull
    User create(User user) throws DuplicateNameException;
}
