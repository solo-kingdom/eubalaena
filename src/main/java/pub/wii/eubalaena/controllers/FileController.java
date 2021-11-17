package pub.wii.eubalaena.controllers;


import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pub.wii.common.http.Response;
import pub.wii.eubalaena.service.FileService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    private static final String DOWNLOAD = "download";
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
                .body(resource);
    }

    @GetMapping(value = "/" + DOWNLOAD + "/**")
    @ResponseBody
    public ResponseEntity<Object> fetch(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String file = uri.substring(uri.indexOf(DOWNLOAD) + DOWNLOAD.length() + 1);
        Resource resource = fileService.load("", file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Object> list(@RequestParam(value = "path", defaultValue = "") String path) {
        return Response.ok(fileService.list(path));
    }
}
