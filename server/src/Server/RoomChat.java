/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Mensagem.IRoomChat;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author daniel
 */
public class RoomChat implements IRoomChat{
    private String nomeSala;
    private List<String> userList;
    private Server server;
    
    public RoomChat(String nomeSala, Server server){
        this.nomeSala = nomeSala;
        this.userList = new ArrayList<>();
        this.server = server;
    }
    @Override
    public void sendMsg(String usrName, String msg) {
        
    }

    @Override
    public void joinRoom(String usrName) {
        userList.add(usrName);        
    }

    @Override
    public void leaveRoom(String usrName) {
        userList.remove(usrName);
        //sendMsg(usrName, " saiu da sala!"); envia pra tudos
        if(userList.isEmpty())
            this.closeRoom();
    }

    @Override
    public void closeRoom() {
        if(!userList.isEmpty())
            for(String s :userList)
                this.sendMsg(s, "Sala fechada pelo servidor!");
        server.getRooms().remove(this.nomeSala);
    }

    @Override
    public String getRoomName() {
        return nomeSala;
    }
    
}
