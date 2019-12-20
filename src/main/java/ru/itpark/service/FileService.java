package ru.itpark.service;

import lombok.Data;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Data
public class FileService {
    private Path uploadPath;
    private Path resultDirectory;

    public FileService() {
        uploadPath = Paths.get(System.getenv("UPLOAD_PATH"));
        if (Files.notExists(uploadPath)) {
            try {
                Files.createDirectory(uploadPath);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        resultDirectory = Paths.get(System.getenv("RESULTS"));
        if (Files.notExists(resultDirectory)) {
            try {
                Files.createDirectory(resultDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void writeFile (Part file) {
        if (file != null && file.getSize() != 0) {
            try {
                file.write(uploadPath.resolve(file.getSubmittedFileName()).toString());
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void readFile (String envPath, String id, ServletOutputStream servletOutputStream) throws IOException {
        Path path = Paths.get(envPath).resolve(id);
        Files.copy(path, servletOutputStream);
    }
}