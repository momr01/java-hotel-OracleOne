package ar.com.momr.back.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.momr.back.domain.Usuario;

public class UsuarioDAO {

	private Connection con;

	public UsuarioDAO(Connection con) {
		this.con = con;
	}

	public List<Usuario> listar() {
		List<Usuario> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT id, usuario, password, email FROM hotel_usuario");
			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						resultado.add(new Usuario(
								resultSet.getInt("id"), 
								resultSet.getString("usuario"),
								resultSet.getString("password"), 
								resultSet.getString("email")));
					}

				}

			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return resultado;
	}

}
