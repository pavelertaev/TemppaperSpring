package me.ertaev.temppaperspring.service;

import java.io.File;
import java.nio.file.Path;

public interface FilesService {
    boolean saveSockToFile(String json);

    String readSocksFromFile();

    void cleanFile(String fileName);

    Path createTempFile(String suffix);

    File getFileSOck();

    void cleanSockFile();
//
}
