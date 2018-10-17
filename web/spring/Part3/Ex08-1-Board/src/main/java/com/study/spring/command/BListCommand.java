package com.study.spring.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.study.spring.dao.BDao;
import com.study.spring.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) 
	{
		//BDao dao = BDao.getInstance();
		BDao dao =  new BDao();
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list",dtos);

	
			
		
	}

}
