package pub.wii.eubalaena.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pub.wii.common.http.Response;
import pub.wii.common.http.Status;
import pub.wii.common.spring.model.Token;
import pub.wii.common.spring.token.TokenManager;
import pub.wii.eubalaena.service.UserService;
import pub.wii.eubalaena.storage.mysql.entity.UserEntity;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController {

    TokenManager tokenManager;

    UserService userService;

    AuthController(TokenManager tokenManager, UserService userService) {
        this.tokenManager = tokenManager;
        this.userService = userService;
    }

    @RequestMapping(value = "token", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Token> getToken(@RequestBody UserEntity user) {
        UserService.encryptPassword(user);
        log.debug("user: {}", user);
        UserEntity ue = userService.check(user);

        if (ue != null) {
            Token token = tokenManager.create(user.getId());
            return Response.ok(token);
        } else {
            return Response.error(Status.SUCCESS, "user not found");
        }
    }
}
