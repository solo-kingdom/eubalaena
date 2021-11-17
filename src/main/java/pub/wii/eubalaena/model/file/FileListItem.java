package pub.wii.eubalaena.model.file;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class FileListItem {
    private String name;
    private FileType type;

    public FileListItem(File file) {
        this.name = file.getName();
        this.type = file.isFile() ? FileType.File : FileType.FOLDER;
    }

    public enum FileType {
        FOLDER, File
    }
}
