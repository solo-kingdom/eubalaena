package pub.wii.eubalaena.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
    void save(String path, MultipartFile file);
}
