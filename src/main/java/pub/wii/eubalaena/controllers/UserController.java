package pub.wii.eubalaena.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pub.wii.common.spring.annotation.Auth;
import pub.wii.eubalaena.storage.mysql.entity.UserEntity;
import pub.wii.eubalaena.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
@Auth
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> insert(@RequestBody UserEntity user) {
        UserService.encryptPassword(user);
        userService.insert(user);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<UserEntity> get(@RequestParam("id") int id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<UserEntity>> list() {
        return ResponseEntity.ok(userService.list());
    }
}
