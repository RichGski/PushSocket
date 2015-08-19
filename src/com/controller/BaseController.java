package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.system.MainControllerWebSocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
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
	
	@RequestMapping("/test")
	public ModelAndView test() {
		return new ModelAndView("main");
	}
	
		
	@RequestMapping("/testwebsocketpush")
	public ModelAndView helloWorld(HttpServletRequest request) {
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
	String message = "<br><div align='center'>" + "<h3>********** Done! **********<br><br>";
	return new ModelAndView("welcome", "message", message);
	}
}
