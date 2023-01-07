package me.ertaev.temppaperspring.controllers;

import me.ertaev.temppaperspring.service.FilesService;
import me.ertaev.temppaperspring.service.SockService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {


    private final FilesService filesService;

    public SockService sockService;


    public FilesController(FilesService filesService, SockService sockService) {
        this.filesService = filesService;
        this.sockService = sockService;
    }

    @GetMapping(value = "/export/sock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> downloadSockFile() throws FileNotFoundException {
        File file = filesService.getFileSOck();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment ; filename=\"Sock.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import/sock", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        filesService.cleanSockFile();
        File sockFile = filesService.getFileSOck();
        try (FileOutputStream fosSock = new FileOutputStream(sockFile)) {
            IOUtils.copy(file.getInputStream(), fosSock);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/export/socks/txt")
    public ResponseEntity<InputStreamResource> downloadSockTxtFile() throws IOException {
        File file = sockService.createSockTxtFile();
        InputStreamResource ior = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(file.length())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"socks.txt\"")
                .body(ior);
    }


}
