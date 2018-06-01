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

public interface IUserChat extends java.rmi.Remote {
    public void deliverMsg(String senderName, String msg);
}
