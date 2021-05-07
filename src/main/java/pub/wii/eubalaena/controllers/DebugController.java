package pub.wii.eubalaena.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pub.wii.common.http.Response;

@RestController
@RequestMapping("debug")
public class DebugController {
    @RequestMapping(value = "health", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> health() {
        return Response.ok("ok");
    }
}
