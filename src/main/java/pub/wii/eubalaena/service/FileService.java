package pub.wii.eubalaena.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pub.wii.eubalaena.model.file.FileListItem;

import java.util.List;

@Service
public interface FileService {
    void save(String path, MultipartFile file);

    List<FileListItem> list(String path);

    Resource load(String path, String file);
}
