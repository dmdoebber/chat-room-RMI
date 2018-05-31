/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Mensagem.IRoomChat;
import Mensagem.IServerChat;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author daniel
 */
public class Client{
    private Registry registry;
    private IServerChat server;
    private IRoomChat room;
    private viewList vList;
    private String IP = "localhost";
    
    public Client(){
       
        try{            
            registry = LocateRegistry.getRegistry(IP, 2020);
            
            server = (IServerChat) registry.lookup("Servidor");
            
            vList = new viewList(server);
            vList.setVisible(true);
            
        }catch(Exception e){
            System.out.println("Erro " + e);
        }
        
    }
    
    public static void main (String[] args){
        Client c = new Client();
    }
    
}
