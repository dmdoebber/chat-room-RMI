package Mensagem;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import Server.RoomChat;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public interface IServerChat extends Remote {
    public void criateRoom(String roomName) throws RemoteException;
    public ArrayList<RoomChat> getRooms() throws RemoteException;
}
