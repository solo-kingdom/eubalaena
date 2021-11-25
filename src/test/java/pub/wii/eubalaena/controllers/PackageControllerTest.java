package pub.wii.eubalaena.controllers;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PackageControllerTest {

    @Test
    void file() {
        String path = Paths.get("a", "b", "c").toString();
        System.out.println(path);
    }
}