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
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author daniel
 */
public class RoomChat implements IRoomChat{
    private String nomeSala;
    private Map<String, IUserChat> userList;
    private Server server;
    
    public RoomChat(String nomeSala, Server server){
        this.nomeSala = nomeSala;
        this.userList = new HashMap<>() ;
        this.server = server;
    }
    @Override
    public void sendMsg(String usrName, String msg) {

        for (Map.Entry m : userList.entrySet()) {            
            String user = (String) m.getKey();
            IUserChat iUser = (IUserChat) m.getValue();
            
            iUser.deliverMsg(user, msg);
        }
    }

    @Override
    public void joinRoom(String usrName, IUserChat user) {
        userList.put(usrName, user);
    }

    @Override
    public void leaveRoom(String usrName) {
        userList.remove(usrName);
        
        for (Map.Entry m : userList.entrySet()) {
            String user = (String) m.getKey();
            IUserChat iUser = (IUserChat) m.getValue();
            
            iUser.deliverMsg(user, usrName+" saiu da sala");
        }
    }

    @Override
    public void closeRoom() throws RemoteException{
        try {
            
             server.registry.unbind(nomeSala);
             server.getRooms().remove(nomeSala);
            
             for (Map.Entry m : userList.entrySet()) {
                String user = (String) m.getValue();
                IUserChat iUser = (IUserChat) m.getValue();
            
                iUser.deliverMsg(user, "Sala fechada pelo servidor!");
            }
        } catch (NotBoundException ex) {
            System.out.println("Erro " + ex);
        }
        
    }

    @Override
    public String getRoomName() {
        return nomeSala;
    }    
}
