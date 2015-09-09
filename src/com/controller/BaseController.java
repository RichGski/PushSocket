package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.config.AssyncConfig;
import com.system.MainControllerWebSocket;
import com.system.PushUtil;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
	
    ApplicationContext context = new AnnotationConfigApplicationContext(AssyncConfig.class);
    private PushUtil pushUtil = context.getBean(PushUtil.class);
    
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
		pushUtil.pushWorld(request);
		String message = "<br><div align='center'>" + "<h3>********** Done! **********<br><br>";
		return new ModelAndView("welcome", "message", message);
	}
	
	@RequestMapping("/testwebsocketpushasync")
	public ModelAndView helloWorldAsync(HttpServletRequest request) {
		pushUtil.pushWorldAsync(request);
		wait(2500);
		String message = "<br><div align='center'>" + "<h3>********** Done! **********<br><br>";
		return new ModelAndView("welcome", "message", message);
	}	
	
}
