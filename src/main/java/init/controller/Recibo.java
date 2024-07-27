package init.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Recibo {

    private Path path = Path.of("src\\main\\files");
    private String fileContent;
    private final DateTimeFormatter dateAndHour = DateTimeFormatter.ofPattern("HH-mm-ss_ddMMyyyy");

    public Recibo() {

    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public Path getPath() {
        return path;
    }

    public void createFile() {
        LocalDateTime date = LocalDateTime.now();
        String dateHour = dateAndHour.format(date);

        path = Path.of("src\\main\\files\\" + dateHour + ".txt");
        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.writeString(path, getFileContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertContent() {

    }

    

}
