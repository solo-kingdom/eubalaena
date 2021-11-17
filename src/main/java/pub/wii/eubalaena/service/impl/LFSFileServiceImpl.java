package pub.wii.eubalaena.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pub.wii.common.file.FileUtils;
import pub.wii.common.http.BusinessException;
import pub.wii.common.http.Status;
import pub.wii.eubalaena.model.file.FileListItem;
import pub.wii.eubalaena.service.FileService;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        Path pt = Paths.get(getBasePath(), path);
        log.info("save file into local storage. [path={}, fileName={}]", pt, file.getName());
        FileUtils.ensurePath(pt.toString());
        Path f = Paths.get(pt.toString(), file.getName());
        try {
            file.transferTo(f);
        } catch (Exception e) {
            throw new BusinessException(Status.SERVER_ERROR, "save file failed", e);
        }
    }

    @Override
    public List<FileListItem> list(String path) {
        File f = new File(Paths.get(getBasePath(), path).toUri());
        File[] fs = f.listFiles();
        if (fs == null || fs.length == 0) {
            return new ArrayList<>();
        } else {
            return Arrays.stream(fs)
                    .map(FileListItem::new)
                    .collect(Collectors.toList());
        }
    }

    @Override
    @SneakyThrows
    public Resource load(String path, String file) {
        Path pt = Paths.get(getBasePath(), path, file);
        log.info("load file. [path={}]", path);
        if (!new File(pt.toUri()).exists()) {
            throw new BusinessException(Status.NOT_FOUND, "file not exists");
        }
        return new FileSystemResource(pt);
    }

    public String getBasePath() {
        return basePath.replaceFirst("^~", System.getProperty("user.home"));
    }
}
