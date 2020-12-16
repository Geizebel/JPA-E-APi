package br.com.apisEjpa.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.apisEjpa.models.Peca;
import br.com.apisEjpa.repository.PecaRepository;



@Controller
@RequestMapping("/pecas")
public class ControllerPeca {
	
	@Autowired
	PecaRepository pr;
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
	public String form() {
		return "pecas/novo";
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public String form(Peca peca) {
		pr.save(peca);
		return "redirect:/pecas";
	}
	
	@RequestMapping(value="")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("pecas/pecas.html");
		Iterable<Peca> pecas = pr.findAll();
		mv.addObject("pecas", pecas);
		return mv;
	}
	
	@RequestMapping(value = "/excluir/{codigo}", method=RequestMethod.GET)
	public String excluir(@PathVariable("codigo") long id) throws SQLException {
		Peca peca = pr.findByCodigo(id);
		pr.delete(peca);
		return "redirect:/pecas";
		
	}
	
	@RequestMapping(value = "/editar/{codigo}", method=RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") long id) throws SQLException {
		
		Peca peca = pr.findByCodigo(id);
		ModelAndView mv = new ModelAndView("pecas/edit.html");
		mv.addObject("peca", peca);
			
		return mv;
		
	} 
	
	//atualizar
	@RequestMapping(value = "/atualizar", method=RequestMethod.POST)
	public String salvar(@ModelAttribute Peca peca) throws SQLException {
		pr.save(peca);
		return "redirect:/pecas";
	}
}