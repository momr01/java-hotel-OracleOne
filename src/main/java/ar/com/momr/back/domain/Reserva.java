package ar.com.momr.back.domain;

public class Reserva {
	
	private Integer id;
	private String entrada;
	private String salida;
	private double valor;
	private String formaPago;
	private Integer nroReserva;

	public Reserva(Integer id, String entrada, String salida, double valor, String formaPago, int nroReserva) {
		this.id = id;
		this.entrada = entrada;
		this.salida = salida;
		this.valor = valor;
		this.formaPago = formaPago;
		this.nroReserva = nroReserva;
	}
	
	public Reserva(Integer id, String entrada, String salida, double valor, String formaPago) {
		this.id = id;
		this.entrada = entrada;
		this.salida = salida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Reserva(String entrada, String salida, double valor, String formaPago, int nroReserva) {
		this.entrada = entrada;
		this.salida = salida;
		this.valor = valor;
		this.formaPago = formaPago;
		this.nroReserva = nroReserva;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public int getNroReserva() {
		return nroReserva;
	}

	public void setNroReserva(int nroReserva) {
		this.nroReserva = nroReserva;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", entrada=" + entrada + ", salida=" + salida + ", valor=" + valor + ", formaPago="
				+ formaPago + ", nroReserva=" + nroReserva + "]";
	}

	
	
	

}
