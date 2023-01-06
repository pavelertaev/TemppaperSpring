package me.ertaev.temppaperspring.service;
//
import me.ertaev.temppaperspring.model.Sock;

import java.io.File;
import java.io.FileNotFoundException;

public interface SockService {
    public void addSock(Sock sock);
    public Sock getSock(long id);
    Sock editSock(long id , Sock newSock);
    boolean deleteSock(long id);

    File createSockTxtFile() throws FileNotFoundException;
}
