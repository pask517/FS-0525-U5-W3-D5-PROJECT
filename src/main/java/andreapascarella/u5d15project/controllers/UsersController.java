package andreapascarella.u5d15project.controllers;

import andreapascarella.u5d15project.entities.User;
import andreapascarella.u5d15project.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public Page<User> findAllUsers(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "surname") String orderBy,
                                   @RequestParam(defaultValue = "asc") String sortCriteria) {

        return this.usersService.findAllUsers(page, size, orderBy, sortCriteria);
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId) {
        return this.usersService.findById(userId);
    }
}
