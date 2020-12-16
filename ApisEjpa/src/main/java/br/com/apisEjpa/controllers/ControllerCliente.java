package br.com.apisEjpa.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.apisEjpa.models.Cliente;
import br.com.apisEjpa.repository.ClienteRepository;


@Controller
@RequestMapping("/clientes")
public class ControllerCliente {

	@Autowired
	ClienteRepository cr;
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
	public String form() {
		return "clientes/novo";
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public String form(Cliente cliente) {
		cr.save(cliente);
		return "redirect:/clientes";
	}
	
	@RequestMapping(value="")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("clientes/clientes.html"); //clientes/clientes.html"
		Iterable<Cliente> clientes = cr.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}
	
	@RequestMapping(value = "/excluir/{codigo}", method=RequestMethod.GET)
	public String excluirColaborador(@PathVariable("codigo") long codigo) throws SQLException {
		Cliente cliente = cr.findByCodigo(codigo);
		cr.delete(cliente);
		return "redirect:/clientes";
		
	}
	
	@RequestMapping(value = "/editar/{codigo}", method=RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") long codigo) throws SQLException {
		
		Cliente cliente = cr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("clientes/edit.html");
		mv.addObject("cliente", cliente);
			
		return mv;
		
	} 
	
	//atualizar
	@RequestMapping(value = "/atualizar", method=RequestMethod.POST)
	public String salvar(@ModelAttribute Cliente cliente) throws SQLException {
		cr.save(cliente);
		return "redirect:/clientes";
	}
}
