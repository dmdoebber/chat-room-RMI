/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Mensagem.IServerChat;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
public class Server extends UnicastRemoteObject implements IServerChat{
    private Registry registry;
    private List<RoomChat> roomList;  
    private view v;
    
    public Server() throws RemoteException{
        try{
            registry = LocateRegistry.createRegistry(2020);
            roomList = new ArrayList<>();
        
            v = new view();
            v.setServer(this);
            v.setVisible(true);
            
        }catch(Exception e){
            System.out.println("Erro "+ e);
        } 
    }
    
    @Override
    public void criateRoom(String nomeSala) {
        RoomChat room;
        try {  
            
            room = new RoomChat(nomeSala, this);
            this.registry.bind(nomeSala, room);
            
            roomList.add(room);
            v.list.addElement(nomeSala);
        } catch (RemoteException | AlreadyBoundException ex) {
            System.out.println("Erro "+ ex);
        }    
    }

    @Override
    public List<RoomChat> getRooms() {
        return roomList;
    }
    
    public static void main(String[] args) throws RemoteException{
        Server server = new Server();
    }
    
}
