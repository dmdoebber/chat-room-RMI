package Server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Mensagem.IRoomChat;
import Mensagem.IUserChat;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author daniel
 */
public class RoomChat extends UnicastRemoteObject implements IRoomChat{
    private String nomeSala;
    private Map<String, IUserChat> userList;
    private Server server;
    
    public RoomChat(String nomeSala, Server server) throws RemoteException{
        this.nomeSala = nomeSala;
        this.userList = new HashMap<>() ;
        this.server = server;
    }
    @Override
    public void sendMsg(String usrName, String msg) throws RemoteException {

        for (Map.Entry m : userList.entrySet()) {            
            IUserChat iUser = (IUserChat) m.getValue();
            iUser.deliverMsg(usrName, msg);
        }
    }

    @Override
    public void joinRoom(String usrName, IUserChat user) throws RemoteException {
        userList.put(usrName, user);
        
        for (Map.Entry m : userList.entrySet()) {
            IUserChat iUser = (IUserChat) m.getValue();
            
            iUser.deliverMsg(usrName, " entrou na sala!");
        }
        
    }

    @Override
    public void leaveRoom(String usrName) throws RemoteException {
        userList.remove(usrName);
        
        for (Map.Entry m : userList.entrySet()) {
            IUserChat iUser = (IUserChat) m.getValue();
            
            iUser.deliverMsg(usrName," saiu da sala");
        }
    }

    @Override
    public void closeRoom() throws RemoteException{
        for (Map.Entry m : userList.entrySet()) {
            IUserChat iUser = (IUserChat) m.getValue();
            iUser.deliverMsg("", "Sala fechada pelo servidor!");
        }
        try {
            server.registry.unbind(nomeSala);
            server.getRooms().remove(nomeSala);
        } catch (NotBoundException ex) {
            System.out.println("Erro " + ex);
        }
    }

    @Override
    public String getRoomName() {
        return nomeSala;
    }    
}
