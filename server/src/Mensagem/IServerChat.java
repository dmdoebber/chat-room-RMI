package Mensagem;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author daniel
 */
public interface IServerChat extends Remote {
    public void createRoom(String roomName) throws RemoteException;
    public HashMap<String, IRoomChat> getRooms() throws RemoteException;
}
