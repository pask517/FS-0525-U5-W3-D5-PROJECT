package andreapascarella.u5d15project.controllers;

import andreapascarella.u5d15project.entities.User;
import andreapascarella.u5d15project.exceptions.ValidationException;
import andreapascarella.u5d15project.payloads.LoginDTO;
import andreapascarella.u5d15project.payloads.LoginResponseDTO;
import andreapascarella.u5d15project.payloads.UserDTO;
import andreapascarella.u5d15project.services.AuthService;
import andreapascarella.u5d15project.services.UsersService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UsersService usersService;
    
    public AuthController(AuthService authService, UsersService usersService) {
        this.authService = authService;
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {

        return new LoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Validated UserDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {

            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();

            throw new ValidationException(errorsList);
        } else {
            return this.usersService.saveUser(payload);
        }
    }
}
