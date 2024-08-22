package init.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Recibo {

    private Path path = Path.of("C:\\cdi_calculations\\");
    private String fileContent;
    private final DateTimeFormatter dateAndHour = DateTimeFormatter.ofPattern("HH-mm-ss_ddMMyyyy");

    public Recibo() {
        this("");
    }

    public Recibo(String fileContent) {
        this.fileContent = fileContent;
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

    public void createLocal() {
        File directory = new File(getPath().toString());
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                JOptionPane.showMessageDialog(null, "diretório criado.");
            }
        }
    }

    public void createFile() {
        LocalDateTime date = LocalDateTime.now();
        String dateHour = dateAndHour.format(date);

        path = Path.of("C:\\cdi_calculations\\" + dateHour + ".txt");
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

    public void openLocal() {
        File directory = new File("C:\\cdi_calculations");

        if (Desktop.isDesktopSupported()) {
            Desktop computer = Desktop.getDesktop();
            try {
                computer.open(directory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Not supported.");
        }
    }

}
