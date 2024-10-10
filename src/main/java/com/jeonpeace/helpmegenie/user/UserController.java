package com.jeonpeace.helpmegenie.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/join-view")
	public String joinView() {
		
		return "user/join";
	}
	
}
