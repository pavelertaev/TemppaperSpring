package me.ertaev.temppaperspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.ertaev.temppaperspring.model.Sock;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;
@Service
public class SockServiceImpl implements SockService {


    private final FilesService fileService;
    private static Map<Long, Sock> socks = new TreeMap<>();
    private static long lastId = 0;

    public SockServiceImpl(FilesService fileService) {
        this.fileService = fileService;
    }


    @Override
    public void addSock(Sock sock) {
        socks.put(lastId++, sock);
    }

    @Override
    public Sock getSock(long id) {
        if (socks.containsKey(id)) {
            return socks.get(id);
        }
        return null;
    }

    @Override
    public Sock editSock(long id, Sock newSock) {
        if (socks.containsKey(id)) {
            socks.put(id, newSock);
            return newSock;
        }
        return null;
    }

    @Override
    public boolean deleteSock(long id) {
        if (socks.containsKey(id)) {
            socks.remove(id);
            return true;
        }
        return false;
    }

    private void readFromFile() {
        try {
            String json = fileService.readSocksFromFile();
            if (!json.isBlank()) {
                socks = new ObjectMapper().readValue(json, new TypeReference<>() {
                });
                lastId = socks.size();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(socks);
            fileService.saveSockToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public File createSockTxtFile() throws FileNotFoundException {
        Path path = fileService.createTempFile("Socks");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (Sock sock: socks.values()) {
                writer.append(socks.toString());
                writer.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path.toFile();
    }
}
