/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mensagem;

/**
 *
 * @author daniel
 */
public interface IRoomChat extends java.rmi.Remote {
    public void sendMsg(String usrName, String msg);
    public void joinRoom(String usrName);
    public void leaveRoom(String usrName);
    public void closeRoom();
    public String getRoomName();
}
