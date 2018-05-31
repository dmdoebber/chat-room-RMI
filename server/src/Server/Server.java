/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Mensagem.IRoomChat;
import Mensagem.IServerChat;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author daniel
 */
public class Server extends UnicastRemoteObject implements IServerChat{
    private HashMap<String, IRoomChat> roomList;  
    
    private Registry registry;
    private view v;
    
    public Server() throws RemoteException{
        try{
            registry = LocateRegistry.createRegistry(2020);
            registry.rebind("Servidor", this);
            roomList = new HashMap<>();
        
            v = new view();
            v.setServer(this);
            v.setVisible(true);
            
        }catch(Exception e){
            System.out.println("Erro "+ e);
        } 
    }
    
    @Override
    public void createRoom(String nomeSala) {
        RoomChat room;
        try {  
            
            room = new RoomChat(nomeSala, this);
            this.registry.bind(nomeSala, room);
            
            roomList.put(nomeSala, room);
            v.list.addElement(nomeSala);
        } catch (RemoteException | AlreadyBoundException ex) {
            System.out.println("Erro "+ ex);
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
