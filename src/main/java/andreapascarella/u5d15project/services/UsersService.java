package andreapascarella.u5d15project.services;

import andreapascarella.u5d15project.entities.User;
import andreapascarella.u5d15project.exceptions.BadRequestException;
import andreapascarella.u5d15project.payloads.UserDTO;
import andreapascarella.u5d15project.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User saveUser(UserDTO payload) {

        this.usersRepository.findByEmail(payload.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        User newUser = new User(payload.name(), payload.surname(), payload.username(), payload.email(), bcrypt.encode(payload.password()));

        User savedUser = this.usersRepository.save(newUser);

        log.info("L'utente con id " + savedUser.getUserId() + " è stato salvato correttamente!");

        return savedUser;
    }
}
