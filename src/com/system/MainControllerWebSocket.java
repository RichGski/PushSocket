package com.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/mainwebsock", 
        configurator = GetHttpSessionConfigurator.class)
public class MainControllerWebSocket {
	private static Map<String, javax.websocket.Session> mSessions = new HashMap<String, javax.websocket.Session>();
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
			InterruptedException {
		System.out.println("User input: " + message);
		session.getBasicRemote().sendText("Hello world Mr. " + message);
		// Sending message to client each 1 second
		for (int i = 0; i <= 6; i++) {
			session.getBasicRemote().sendText(i + " Message from server");
			Thread.sleep(1000);

		}
	}
	
	public static void sendMessage(String message, String strSessionID){
		try {
			@SuppressWarnings("resource")
			Session mySes = mSessions.get(strSessionID);
			if (mySes != null && mySes.isOpen()) mySes.getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @OnOpen
    public void open(Session session, EndpointConfig config) {
    	HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        mSessions.put(httpSession.getId(), session);
        System.out.println("Client connected on session: " + httpSession.getId());
    }

	@SuppressWarnings("rawtypes")
	@OnClose
	public void onClose(Session session) {
		for (Iterator iter = mSessions.entrySet().iterator(); iter.hasNext();) {
			  @SuppressWarnings("unchecked")
			Map.Entry <String, javax.websocket.Session> e = (Entry<String, Session>) iter.next();
			  if (e.getValue().getId() == session.getId() ) {
				  iter.remove();
			  }}
		System.out.println("Connection closed");
	}
}
