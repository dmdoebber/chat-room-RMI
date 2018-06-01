/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Mensagem.IUserChat;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author daniel
 */
public class UserChat extends UnicastRemoteObject implements IUserChat {
    private String nome;
    private sala vSala;
    
    public UserChat(String nome, sala vSala) throws RemoteException{
        this.nome = nome;
        this.vSala = vSala;       
    }

    @Override
    public void deliverMsg(String senderName, String msg) {
        vSala.deliverMsg(senderName, msg);
    }
}
