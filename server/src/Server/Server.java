/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Mensagem.IServerChat;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/**
 *
 * @author daniel
 */
public class Server extends UnicastRemoteObject implements IServerChat{
    private Registry roomList;
    
    public Server() throws Exception{
        super();
        roomList = LocateRegistry.createRegistry(6969);
    }
    
    @Override
    public void criateRoom(String roomName) {
        RoomChat room;
        
        room = new RoomChat();
        
    }
    
}
