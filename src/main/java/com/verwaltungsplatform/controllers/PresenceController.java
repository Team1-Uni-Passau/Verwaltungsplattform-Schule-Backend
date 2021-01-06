package com.verwaltungsplatform.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verwaltungsplatform.dto.PresenceDto;
import com.verwaltungsplatform.service.PresenceService;

@Controller
public class PresenceController {
	
	@Autowired
	PresenceService presenceService;
	
	// Gibt die Präsenz des eigenen Kindes in der letzten Woche aus
	@GetMapping("/eltern/praesenz/{parentId}")
	@ResponseBody
	public List<PresenceDto> getPresenceChildLastWeek(@PathVariable("parentId") int parentId) {
		
		return presenceService.getPresenceEntriesLastWeek(parentId);
	}
	
	

}
