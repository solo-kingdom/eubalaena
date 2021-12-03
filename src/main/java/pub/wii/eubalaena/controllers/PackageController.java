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
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/package")
public class PackageController {
    private static final String DOWNLOAD = "download";
    @javax.annotation.Resource
    FileService fileService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> file(@RequestParam String space,
                                 @RequestParam String product,
                                 @RequestParam String module,
                                 @RequestParam String version,
                                 @RequestParam MultipartFile file) {
        String path = Paths.get(space, product, module, version).toString();
        fileService.save(path, file);
        return Response.ok("ok");
    }

    @GetMapping(value = "")
    @ResponseBody
    public ResponseEntity<Object> get(@RequestParam String space,
                                      @RequestParam String product,
                                      @RequestParam String module,
                                      @RequestParam String version,
                                      @RequestParam String file) {
        String path = Paths.get(space, product, module, version).toString();
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
    public Response<Object> list(@RequestParam String space,
                                 @RequestParam String product,
                                 @RequestParam String module,
                                 @RequestParam String version) {
        String path = Paths.get(space, product, module, version).toString();
        return Response.ok(fileService.list(path));
    }
}
