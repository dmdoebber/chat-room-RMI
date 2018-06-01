/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Mensagem.IUserChat;

/**
 *
 * @author daniel
 */
public class UserChat implements IUserChat{
    
    private sala vSala;
    
    public UserChat(sala vSala){
        this.vSala = vSala;
    }

    @Override
    public void deliverMsg(String senderName, String msg) {
        vSala.deliverMsg(senderName, msg);
    }
}
