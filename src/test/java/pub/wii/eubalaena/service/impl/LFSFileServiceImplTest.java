package pub.wii.eubalaena.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pub.wii.eubalaena.SpringBootTestConfig;
import pub.wii.eubalaena.service.FileService;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTestConfig
class LFSFileServiceImplTest {

    @Resource
    FileService fileService;

    @Test
    void save() {
        MultipartFile file = new MockMultipartFile("test.file", "bar".getBytes());
        fileService.save(UUID.randomUUID().toString(), file);
    }
}