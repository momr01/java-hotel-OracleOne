package ar.com.momr.back.controller;

import java.util.List;

import ar.com.momr.back.dao.UsuarioDAO;
import ar.com.momr.back.domain.Usuario;
import ar.com.momr.back.factory.ConnectionFactory;

public class UsuarioController {
private UsuarioDAO usuarioDao;
	
	public UsuarioController() {
		var factory = new ConnectionFactory();
		this.usuarioDao=new UsuarioDAO(factory.recuperarConexion());
	}
	
	public List<Usuario> listar(){
		return usuarioDao.listar();
	}

}
