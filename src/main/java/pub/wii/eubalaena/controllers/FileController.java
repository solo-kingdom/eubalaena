package pub.wii.eubalaena.controllers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pub.wii.common.http.Response;
import pub.wii.eubalaena.service.FileService;

import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    @javax.annotation.Resource
    FileService fileService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> file(@RequestParam(value = "path", defaultValue = "") String path,
                                 @RequestParam("file") MultipartFile file) {
        fileService.save(path, file);
        return Response.ok("ok");
    }

    @GetMapping(value = "")
    @ResponseBody
    public ResponseEntity<Object> get(@RequestParam(value = "path", defaultValue = "") String path,
                                      @RequestParam(value = "file") String file) {
        Resource resource = fileService.load(path, file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(file);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Object> list(@RequestParam(value = "path", defaultValue = "") String path) {
        return Response.ok(fileService.list(path));
    }
}
