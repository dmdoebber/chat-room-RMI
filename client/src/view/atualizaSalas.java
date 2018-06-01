/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Mensagem.IRoomChat;
import Mensagem.IServerChat;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
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
        HashMap<String, IRoomChat> lista;
        int oldSize = 0;
        
        while(true){     
            try {
                
                lista = server.getRooms();
                
                if(oldSize != lista.size()){
                    if(oldSize > lista.size()){
                        for(int i = 0; i < oldSize; i++)
                            if(!lista.containsKey(list.get(i))){
                                list.removeElement(list.get(i));
                                break;
                            }  
                    }else{
                        for(Map.Entry m : lista.entrySet()){
                            if(!list.contains(m.getKey()))
                                list.addElement((String) m.getKey());
                        }                        
                    }                                            
                    oldSize = lista.size();
                }
                
            } catch (RemoteException ex) {
                System.out.println("Erro " + ex);
            }
            
        }    
    }
    

}
