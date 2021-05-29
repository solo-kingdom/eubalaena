package pub.wii.eubalaena.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pub.wii.common.http.Response;

@RestController
@RequestMapping("key")
public class KeyCenterController {

    @RequestMapping(value = "put", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> put() {
        return Response.ok("ok");
    }

    @RequestMapping(value = "get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> get() {
        return Response.ok("ok");
    }
}
