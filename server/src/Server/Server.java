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
import java.util.HashMap;

/**
 *
 * @author daniel
 */
public class Server extends UnicastRemoteObject implements IServerChat{
    
    private HashMap<String, IRoomChat> roomList;  
    public  Registry registry;
    private view v;
    
    public Server() throws RemoteException{
        super();
        try{
            registry = LocateRegistry.getRegistry(2020);
            roomList = new HashMap();
        
            v = new view(this);
            v.setVisible(true);
            
        }catch(Exception e){
            System.out.println("Erro "+ e);
        } 
    }
    
    @Override
    public void createRoom(String nomeSala) throws RemoteException {        
        try {
            
            RoomChat room = new RoomChat(nomeSala, this);
            this.registry.bind(nomeSala, room);                        
            roomList.put(nomeSala, room);
            v.list.addElement(nomeSala);
            
        } catch (AlreadyBoundException | AccessException ex) {
            System.out.println("Erro " + ex);
        }     
    }

    @Override
    public HashMap<String, IRoomChat> getRooms() {
        return roomList;
    }
    
    public static void main(String[] args) throws RemoteException, AlreadyBoundException{
        Server server = new Server();
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        Registry registry = LocateRegistry.createRegistry(2020);
        registry.rebind("Servidor", server);
    }
}
