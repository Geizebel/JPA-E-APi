package br.com.apisEjpa.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.apisEjpa.models.Vendedor;
import br.com.apisEjpa.repository.VendedorRepository;

@Controller
@RequestMapping("/vendedores")
public class ControllerVendedor {
	@Autowired
	VendedorRepository vr;
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
	public String form() {
		return "vendedores/novo";
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public String form(Vendedor vendedor) {
		vr.save(vendedor);
		return "redirect:/vendedores";
	}
	
	@RequestMapping(value="")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("vendedores/vendedores.html");
		Iterable<Vendedor> vendedores = vr.findAll();
		mv.addObject("vendedores", vendedores);
		return mv;
	}
	
	@RequestMapping(value = "/excluir/{codigo}", method=RequestMethod.GET)
	public String excluirColaborador(@PathVariable("codigo") long codigo) throws SQLException {
		Vendedor vendedor = vr.findByCodigo(codigo);
		vr.delete(vendedor);
		return "redirect:/vendedores";
		
	}
	
	@RequestMapping(value = "/editar/{codigo}", method=RequestMethod.GET)
	public ModelAndView editar(@PathVariable("codigo") long codigo) throws SQLException {
		
		Vendedor vendedor = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("vendedores/edit.html");
		mv.addObject("vendedor", vendedor);
			
		return mv;
		
	} 
	
	//atualizar
	@RequestMapping(value = "/atualizar", method=RequestMethod.POST)
	public String salvar(@ModelAttribute Vendedor vendedor) throws SQLException {
		vr.save(vendedor);
		return "redirect:/vendedores";
	}

}
