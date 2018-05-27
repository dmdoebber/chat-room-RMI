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
public interface IServerChat extends java.rmi.Remote {
    //public roomList getRooms();
    public void criateRoom(String roomName);
}
