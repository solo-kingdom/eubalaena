package pub.wii.eubalaena.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pub.wii.common.http.Response;
import pub.wii.common.spring.annotation.Auth;
import pub.wii.eubalaena.service.UserService;
import pub.wii.eubalaena.storage.mysql.entity.UserEntity;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@Auth
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    private Response<String> insert(@RequestBody UserEntity user) {
        UserService.encryptPassword(user);
        userService.insert(user);
        return Response.ok("ok");
    }

    @GetMapping(value = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    private Response<UserEntity> get(@RequestParam("id") int id) {
        return Response.ok(userService.get(id));
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<UserEntity>> list() {
        return ResponseEntity.ok(userService.list());
    }
}
