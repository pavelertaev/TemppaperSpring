package me.ertaev.temppaperspring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.ertaev.temppaperspring.model.Sock;
import me.ertaev.temppaperspring.service.SockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/sock")
@RestController
@Tag(name = "Носки " , description = "CRUD - операции и другие эндпоинты для отпуска и со склада , удаление брачных , и добавления носков")
public class SockController {
    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }
    @PostMapping
    @Operation(description = "Добавление носков на склад ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Носки были добавлены" ,
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Sock.class)
                            )

                    }


            )
    })
    public ResponseEntity<Sock> createSock(@RequestBody Sock sock) {
        sockService.addSock(sock);
        return ResponseEntity.ok(sock);
    }

    @GetMapping
    @Operation(description = "Запрос на кол-во носков на складе")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Носки были найдены" ,
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Sock.class)
                            )

                    }


            )
    })
    public ResponseEntity<Sock> getSock(@RequestParam int sockId) {
        Sock sock = sockService.getSock(sockId);
        if (sock == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sock);
    }

    @PutMapping("/{id}")
    @Operation(description = "Забрать товар со склада ")
    public ResponseEntity<Sock> editSock(@PathVariable long id, @RequestBody Sock newSock) {
        if (sockService.getSock(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Sock sock =sockService.editSock(id,newSock);
        return ResponseEntity.ok(sock);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Списание брака по товару")
    public ResponseEntity<Void> deleteSock(@PathVariable long id) {
        if (sockService.deleteSock(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
