package br.com.apisEjpa.controllers;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.apisEjpa.models.Moto;
import br.com.apisEjpa.repository.MotoRepository;



@Controller
@RequestMapping("/motos")
public class ControllerMoto {
	
	@Autowired
	MotoRepository mr;
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
	public String form() {
		return "motos/novo";
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public String form(Moto moto) {
		mr.save(moto);
		return "redirect:/motos";
	}
	
	@RequestMapping(value="")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("motos/motos.html");
		Iterable<Moto> motos = mr.findAll();
		mv.addObject("motos", motos);
		return mv;
	}
	
	@RequestMapping(value = "/excluir/{codigo}", method=RequestMethod.GET)
	public String excluir(@PathVariable("codigo") long id) throws SQLException {
		Moto moto = mr.findByCodigo(id);
		mr.delete(moto);
		return "redirect:/motos";
		
	}
	
	@RequestMapping(value = "/editar/{codigo}", method=RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") long id) throws SQLException {
		
		Moto moto = mr.findByCodigo(id);
		ModelAndView mv = new ModelAndView("motos/edit.html");
		mv.addObject("moto", moto);
			
		return mv;
		
	} 
	
	//atualizar
	@RequestMapping(value = "/atualizar", method=RequestMethod.POST)
	public String salvar(@ModelAttribute Moto moto) throws SQLException {
		mr.save(moto);
		return "redirect:/motos";
	}
}
