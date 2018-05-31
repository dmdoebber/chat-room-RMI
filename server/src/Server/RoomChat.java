package Server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Mensagem.IRoomChat;
import Mensagem.IUserChat;
import java.util.HashMap;
import java.util.Iterator;
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
        
    }

    @Override
    public void joinRoom(String usrName, IUserChat user) {
        userList.put(usrName, user);
    }

    @Override
    public void leaveRoom(String usrName) {
        userList.remove(usrName);
        
        Iterator i = userList.entrySet().iterator();
        while(i.hasNext()){
            Map.Entry m = (Map.Entry) i.next();
            
            String user = (String) m.getKey();
            IUserChat iUser = (IUserChat) m.getValue();
            
            iUser.deliverMsg(user, usrName+" saiu da sala");
        }
    }

    @Override
    public void closeRoom() {
        Iterator i = userList.entrySet().iterator();
        while(i.hasNext()){
            Map.Entry m = (Map.Entry) i.next();
            
            String user = (String) m.getValue();
            IUserChat iUser = (IUserChat) m.getValue();
            
            iUser.deliverMsg(user, "Sala fechada pelo servidor!");
        }
    }

    @Override
    public String getRoomName() {
        return nomeSala;
    }    
}
