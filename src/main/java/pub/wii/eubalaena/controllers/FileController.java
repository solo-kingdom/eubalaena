package pub.wii.eubalaena.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pub.wii.common.http.Response;
import pub.wii.eubalaena.service.FileService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1")
public class FileController {
    @Resource
    FileService fileService;

    @PostMapping(value = "file", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> put(@RequestParam("path") String path, @RequestParam("file") MultipartFile file) {
        fileService.save(path, file);
        return Response.ok("ok");
    }
}
