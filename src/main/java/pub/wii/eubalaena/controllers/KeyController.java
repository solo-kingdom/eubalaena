package pub.wii.eubalaena.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pub.wii.common.http.Response;
import pub.wii.common.spring.annotation.Auth;
import pub.wii.eubalaena.model.Key;
import pub.wii.eubalaena.service.KeyService;

@RestController
@RequestMapping("key")
public class KeyController {

    KeyService keyService;

    KeyController(KeyService keyService) {
        this.keyService = keyService;
    }

    @RequestMapping(value = "put", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> put(@RequestBody Key key) {
        keyService.insert(key);
        return Response.ok("ok");
    }

    @RequestMapping(value = "get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Key> get(@RequestBody Key key) {
        return Response.ok(keyService.get(key));
    }
}
