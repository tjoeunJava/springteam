package com.tjoeun.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tjoeun.dao.TopMenuDAO;
import com.tjoeun.dto.BoardInfoDTO;
import com.tjoeun.dto.ContentDTO;
import com.tjoeun.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	TopMenuDAO topMenuDAO;	
	
	@GetMapping("/main")
	public String main(Model model) {
		
		List<List<ContentDTO>> boardList = new ArrayList<List<ContentDTO>>();
		
		for(int i = 1; i <= 4; i++) {
		  List<ContentDTO> contentDTOList = mainService.getMainList(i);
		  boardList.add(contentDTOList);
		}
		
		List<BoardInfoDTO> boardInfoList = topMenuDAO.getTopMenuList(); 
		 
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardInfoList", boardInfoList);
		
		return "main";
	}
	

}
