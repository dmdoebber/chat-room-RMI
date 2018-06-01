/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Mensagem.IRoomChat;
import Mensagem.IServerChat;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class Server extends UnicastRemoteObject implements IServerChat{
    private HashMap<String, IRoomChat> roomList;  
    
    public Registry registry;
    private view v;
    
    public Server() throws RemoteException{
        try{
            registry = LocateRegistry.createRegistry(2020);
            registry.bind("Servidor", this);
            roomList = new HashMap<>();
        
            v = new view();
            v.setServer(this);
            v.setVisible(true);
            
        }catch(Exception e){
            System.out.println("Erro "+ e);
        } 
    }
    
    @Override
    public void createRoom(String nomeSala) throws RemoteException {
        
        try {
            
            RoomChat room = new RoomChat(nomeSala, this);
            registry.bind(nomeSala, room);
            
            System.out.println(Arrays.toString(registry.list()));
            
            roomList.put(nomeSala, room);  
            
        } catch (AlreadyBoundException | AccessException ex) {
            System.out.println("Erro " + ex);
        }     
    }

    @Override
    public HashMap<String, IRoomChat> getRooms() {
        return roomList;
    }
    
    public static void main(String[] args) throws RemoteException{
        Server server = new Server();
    }
    
}
