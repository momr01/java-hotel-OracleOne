package ar.com.momr.back.controller;

import java.util.List;

import ar.com.momr.back.dao.ReservaDAO;
import ar.com.momr.back.domain.Reserva;
import ar.com.momr.back.factory.ConnectionFactory;

public class ReservaController {
	
	private ReservaDAO reservaDao;
	
	public ReservaController() {
		var factory = new ConnectionFactory();
		this.reservaDao=new ReservaDAO(factory.recuperarConexion());
	}
	
	public List<Reserva> listar(){
		return reservaDao.listar();
	}
	
	public void guardar(Reserva reserva) {
		reservaDao.guardar(reserva);
	}
	
	public Reserva buscarPorId(int id) {
		return reservaDao.buscarPorId(id);
	}
	
	public Reserva buscarPorNroReserva(int idReserva){
		return reservaDao.buscarPorIdReserva(idReserva);
	}
	
	public List<Reserva> buscarPorApellido(String apellido){
		return reservaDao.buscarPorApellido(apellido);
	}
	
	public void editar(Reserva reserva) {
		reservaDao.editar(reserva);
	}
	
	public int eliminar(int id) {
		return reservaDao.eliminar(id);
	}
	

}
