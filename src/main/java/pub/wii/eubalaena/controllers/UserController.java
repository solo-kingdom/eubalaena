package pub.wii.eubalaena.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pub.wii.common.http.Response;
import pub.wii.eubalaena.storage.mysql.entity.UserEntity;
import pub.wii.eubalaena.storage.mysql.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    private Response<String> insert(@RequestBody UserEntity user) {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        userService.insert(user);
        return Response.ok("ok");
    }

    @GetMapping(value = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    private Response<UserEntity> get(@RequestParam("id") int id) {
        return Response.ok(userService.get(id));
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    private Response<List<UserEntity>> list() {
        return Response.ok(userService.list());
    }
}
