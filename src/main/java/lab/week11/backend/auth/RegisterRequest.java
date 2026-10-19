package lab.week11.backend.auth;

public record RegisterRequest(
        String firstname,
        String lastname,
        String email,
        String phone,
        Integer age,
        String description,
        String password
) {}
