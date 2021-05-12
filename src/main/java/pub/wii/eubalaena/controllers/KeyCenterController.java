package pub.wii.eubalaena.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pub.wii.common.spring.annotation.Auth;

@RestController
@RequestMapping("key")
@Auth
public class KeyCenterController {

    @RequestMapping(value = "put", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put() {
        return ResponseEntity.ok("ok");
    }
}
