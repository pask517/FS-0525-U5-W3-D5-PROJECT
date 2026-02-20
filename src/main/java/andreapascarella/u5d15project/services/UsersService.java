package andreapascarella.u5d15project.services;

import andreapascarella.u5d15project.entities.User;
import andreapascarella.u5d15project.exceptions.NotFoundException;
import andreapascarella.u5d15project.payloads.UserDTO;
import andreapascarella.u5d15project.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder bcrypt;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder bcrypt) {
        this.usersRepository = usersRepository;
        this.bcrypt = bcrypt;
    }

    public User saveUser(UserDTO payload) {

        this.usersRepository.findByEmail(payload.email()).ifPresent(user -> {
        });

        User newUser = new User(payload.name(), payload.surname(), payload.email(), bcrypt.encode(payload.password()), payload.role());

        User savedUser = this.usersRepository.save(newUser);

        log.info("L'utente con id " + savedUser.getUserId() + " è stato salvato correttamente!");

        return savedUser;
    }

    public User findById(UUID userId) {
        return this.usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByEmail(String email) {
        return this.usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato!"));
    }

    public Page<User> findAllUsers(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;

        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.usersRepository.findAll(pageable);
    }
}
