package andreapascarella.u5d15project.services;

import andreapascarella.u5d15project.entities.User;
import andreapascarella.u5d15project.exceptions.UnauthorizedException;
import andreapascarella.u5d15project.payloads.LoginDTO;
import andreapascarella.u5d15project.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsersService usersService;
    private final JWTTools jwtTools;
    private final PasswordEncoder bcrypt;

    @Autowired
    public AuthService(UsersService usersService, JWTTools jwtTools, PasswordEncoder bcrypt) {

        this.usersService = usersService;
        this.jwtTools = jwtTools;
        this.bcrypt = bcrypt;
    }

    public String checkCredentialsAndGenerateToken(LoginDTO payload) {

        User found = this.usersService.findByEmail(payload.email());

        if (bcrypt.matches(payload.password(), found.getPassword())) {

            String accessToken = jwtTools.generateToken(found);

            return accessToken;

        } else {

            throw new UnauthorizedException("Credenziali errate!");
        }


    }
}