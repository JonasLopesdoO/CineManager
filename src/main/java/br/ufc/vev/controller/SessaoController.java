package br.ufc.vev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.vev.service.SessaoService;

@Controller
@RequestMapping(path= "/sessao/")
public class SessaoController {
		
	@Autowired
	SessaoService sessaoService;
	
	
}
