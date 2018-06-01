/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Mensagem.IServerChat;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author daniel
 */
public class Client{
    private Registry registry;
    private IServerChat server;
    
    private viewList vList;
    private String IP;
    
    public Client(){
        this.IP = "127.0.0.1";       
        
        try{                        
            registry = LocateRegistry.getRegistry(IP, 2020);
            server = (IServerChat) registry.lookup("Servidor");
            
            vList = new viewList(server);
            vList.setVisible(true);   
        }catch(RemoteException | NotBoundException e){
            System.out.println("Erro " + e);
        }
    }
    
    public static void main (String[] args){
        Client c = new Client();
    }
}
