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
        ArrayList<String> roomList;
        int oldSize = 0;
        boolean continua = true;
        
        while(continua){     
            try {
                
                roomList = server.getRooms();
                
                if(oldSize != roomList.size()){
                    if(oldSize > roomList.size()){
                        for(int i = 0; i < oldSize; i++)
                            if(!roomList.contains(list.get(i))){
                                list.removeElement(list.get(i));
                                break;
                            }  
                    }else{
                        for (String room : roomList) {
                            if(!list.contains(room))
                                list.addElement(room);
                        }                      
                    }                                            
                    oldSize = roomList.size();
                }
                
            } catch (RemoteException ex) {
                continua = false;
                System.out.println("Erro " + ex);
            }
            
        }    
    }
    

}
