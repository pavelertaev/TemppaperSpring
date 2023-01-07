//package me.ertaev.temppaperspring.service;
////
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
//@Service
//public class FilesServiceImpl implements FilesService {
//
//    @Value("${path.to.data.file}")
//    private String dataFilePath;
//    @Value("sock.json")
//    private String socksFileName;
//
//    @PostConstruct
//    private void init() {
//        try {
//            if (!Files.exists(Path.of(dataFilePath, socksFileName))) {
//                Files.createFile(Path.of(dataFilePath, socksFileName));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public Path createTempFile(String suffix) {
//        try {
//            return Files.createTempFile(Path.of(dataFilePath), "tempfile", suffix);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public File getFileSOck() {
//        return new File(dataFilePath + "/" + socksFileName);
//    }
//
//    @Override
//    public void cleanSockFile() {
//        cleanFile(socksFileName);
//    }
//
//
//    private boolean saveToFile(String fileName, String json) {
//        try {
//            cleanFile(fileName);
//            Files.writeString(Path.of(dataFilePath, fileName), json);
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    private String readFromFile(String fileName) {
//        try {
//            return Files.readString(Path.of(dataFilePath, fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public boolean saveSockToFile(String json) {
//        return saveToFile(socksFileName, json);
//    }
//
//    @Override
//    public String readSocksFromFile() {
//        return readFromFile(socksFileName);
//    }
//
//    @Override
//    public void cleanFile(String fileName) {
//        try {
//            Files.deleteIfExists(Path.of(dataFilePath, fileName));
//            Files.createFile(Path.of(dataFilePath, fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
}
