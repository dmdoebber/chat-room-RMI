/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Mensagem.IServerChat;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author daniel
 */
public class atualizaSalas extends Thread{
    private DefaultListModel<String> list;
    private IServerChat server;
    
    
    public atualizaSalas(DefaultListModel<String> list, IServerChat server){
        this.list = list;
        this.server = server;
    }
    
    
    @Override
    public void run(){
        ArrayList<String> lista;
        int oldSize = 0;
        
        while(true){
            try {
                lista = server.getRooms();
                
                if(oldSize != lista.size()){
                    oldSize = lista.size();
                    
                    list.removeAllElements();
                    
                    for(int i = 0; i < oldSize; i++)
                        list.addElement(lista.get(i));
                    
                }
                
            } catch (RemoteException ex) {
                System.out.println("Erro " + ex);
            }
            
        }    
    }
    

}
