package ar.com.momr.back.controller;

import java.util.List;

import ar.com.momr.back.dao.HuespedDAO;
import ar.com.momr.back.domain.Huesped;
import ar.com.momr.back.factory.ConnectionFactory;

public class HuespedController {
	
	private HuespedDAO huespedDao;
	
	public HuespedController() {
		var factory = new ConnectionFactory();
		this.huespedDao=new HuespedDAO(factory.recuperarConexion());
	}
	
	public List<Huesped> listar(){
		return huespedDao.listar();
	}
	
	public void guardar(Huesped huesped) {
		huespedDao.guardar(huesped);
	}
	
	public Huesped buscarPorId(int id) {
		return huespedDao.buscarPorId(id);
	}
	
	public List<Huesped> buscarPorIdReserva(int idReserva){
		return huespedDao.buscarPorIdReserva(idReserva);
	}
	
	public List<Huesped> buscarPorApellido(String apellido){
		return huespedDao.buscarPorApellido(apellido);
	}
	
	public void editar(Huesped huesped) {
		huespedDao.editar(huesped);
	}
	
	public int eliminar(int id) {
		return huespedDao.eliminar(id);
	}


}
