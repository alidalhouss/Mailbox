/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailbox.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Ali DALHOUSS <ali.dalhouss@gmail.com>
 */

@ServerEndpoint("/websocket")
public class ChatServer {
    
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void onOpen(Session peer) {
        if(peers.contains(peer.getId())) {
        } else {
            peers.add(peer);
        }
    }
    
    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }
    
    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException {
        for (Session peer : peers) {
            peer.getBasicRemote().sendObject(message.replace("-", ":"));
        }
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }
    
}
