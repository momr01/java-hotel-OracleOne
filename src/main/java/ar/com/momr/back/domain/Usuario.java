package ar.com.momr.back.domain;

public class Usuario {

	private Integer id;
	private String usuario;
	private String password;
	private String emaoil;

	public Usuario(Integer id, String usuario, String password, String emaoil) {
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.emaoil = emaoil;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmaoil() {
		return emaoil;
	}

	public void setEmaoil(String emaoil) {
		this.emaoil = emaoil;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", password=" + password + ", emaoil=" + emaoil + "]";
	}

}
