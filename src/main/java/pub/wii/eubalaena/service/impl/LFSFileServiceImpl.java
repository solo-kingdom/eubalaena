package pub.wii.eubalaena.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pub.wii.common.file.FileUtils;
import pub.wii.common.http.BusinessException;
import pub.wii.common.http.Status;
import pub.wii.eubalaena.service.FileService;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * implements of file service based on local file system.
 */
@Slf4j
@Service
public class LFSFileServiceImpl implements FileService {
    @Value("${eubalaena.file.location:~/.eubalaena/files}")
    String basePath;

    @SneakyThrows
    @Override
    public void save(String path, MultipartFile file) {
        String bp = basePath.replaceFirst("^~", System.getProperty("user.home"));
        Path pt = Paths.get(bp, path);
        log.info("save file into local storage. [path={}, fileName={}]", pt, file.getName());
        FileUtils.ensurePath(pt.toString());
        Path f = Paths.get(pt.toString(), file.getName());
        try {
            file.transferTo(f);
        } catch (Exception e) {
            throw new BusinessException(Status.SERVER_ERROR, "save file failed", e);
        }
    }
}
