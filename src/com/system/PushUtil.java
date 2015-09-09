package com.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;


@Component
public class PushUtil {
	
	/**
	 * Sends the passed message to the session using web socket
	 * @param strMessage	The message to send to the connected user
	 */
	private void sendMessage(String strMessage, HttpServletRequest request){
		MainControllerWebSocket.sendMessage(strMessage, request.getSession().getId());
	}
	
	private void wait(int iDelay){
		try {
			Thread.sleep(iDelay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Async
	public void pushWorldAsync(HttpServletRequest request) {
		sendMessage("Starting, please wait.", request);
		wait(500);
		sendMessage("One.", request);
		wait(500);
		sendMessage("Two.", request);
		wait(1000);
		sendMessage("Three.", request);
		wait(500);
		sendMessage("Four.", request);
		wait(1000);
	}
	
	public void pushWorld(HttpServletRequest request) {
		sendMessage("Starting, please wait.", request);
		wait(500);
		sendMessage("One.", request);
		wait(500);
		sendMessage("Two.", request);
		wait(1000);
		sendMessage("Three.", request);
		wait(500);
		sendMessage("Four.", request);
		wait(1000);
	}
	
}
