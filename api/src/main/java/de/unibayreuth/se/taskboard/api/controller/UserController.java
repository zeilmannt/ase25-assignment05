package de.unibayreuth.se.taskboard.api.controller;

import de.unibayreuth.se.taskboard.api.dtos.UserDto;
import de.unibayreuth.se.taskboard.api.mapper.UserDtoMapper;
import de.unibayreuth.se.taskboard.business.domain.User;
import de.unibayreuth.se.taskboard.business.ports.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@OpenAPIDefinition(
        info = @Info(
                title = "TaskBoard",
                version = "0.0.1"
        )
)
@Tag(name = "Users")
@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
        private final UserService userService;
        private final UserDtoMapper userDtoMapper;

        // TODO: Add GET /api/users endpoint to retrieve all users.
        @GetMapping
        public List<UserDto> getAllUsers(){
                return userService.getAllUsers().stream()
                        .map(userDtoMapper::fromBusiness)
                        .toList();
        }

        // TODO: Add GET /api/users/{id} endpoint to retrieve a user by ID.
        @GetMapping("/{id}")
        public UserDto getUserById(@PathVariable UUID id){
                User user = userService.getById(id);
                return userDtoMapper.fromBusiness(user);
        }

        // TODO: Add POST /api/users endpoint to create a new user based on a provided user DTO.
        @PostMapping
        @ResponseStatus
        public UserDto createUser(@Valid @RequestBody UserDto dto){
                User created = userService.create(userDtoMapper.toBusiness(dto));
                return userDtoMapper.fromBusiness(created);
        }
}
