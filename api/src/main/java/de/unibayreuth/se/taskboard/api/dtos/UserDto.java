package de.unibayreuth.se.taskboard.api.dtos;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

//TODO: Add DTO for users.
public record UserDto(
        UUID id,

        @NotBlank(message = "name must not be blank")
        String name,

        LocalDateTime createdAt
) { }
