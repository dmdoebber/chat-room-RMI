/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mensagem;

import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author daniel
 */
public interface IServerChat extends java.rmi.Remote {
    public HashMap<String, IRoomChat> getRooms() throws RemoteException;
    public void createRoom(String roomName) throws RemoteException;
}
