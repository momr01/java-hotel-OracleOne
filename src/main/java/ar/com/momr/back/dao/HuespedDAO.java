package ar.com.momr.back.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.momr.back.domain.Huesped;

public class HuespedDAO {
	private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public List<Huesped> listar() {
		String query = "SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva "
				+ "FROM hotel_huesped";
		List<Huesped> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(query);
			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						resultado.add(new Huesped(resultSet.getInt("id"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getString("fechaNacimiento"),
								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
								resultSet.getInt("idReserva")));
					}

				}

			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return resultado;
	}

	public void guardar(Huesped huesped) {
		String query = "INSERT INTO hotel_huesped "
				+ "(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement;
			statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setString(3, huesped.getFechaNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getIdReserva());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						huesped.setId(resultSet.getInt(1));

						System.out.println(String.format("Fue insertado el huesped= %s", huesped));
					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public Huesped buscarPorId(int id) {
		String query = "SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva "
				+ "FROM hotel_huesped WHERE id=?";
		Huesped huesped = null;

		try {
			final PreparedStatement statement = con.prepareStatement(query);
			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
//						resultado.add(new Huesped(resultSet.getInt("id"), resultSet.getString("nombre"),
//								resultSet.getString("apellido"), resultSet.getString("fechaNacimiento"),
//								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
//								resultSet.getInt("idReserva")));
						huesped = new Huesped(resultSet.getInt("id"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getString("fechaNacimiento"),
								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
								resultSet.getInt("idReserva"));
					}

				}

			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return huesped;

	}

	public List<Huesped> buscarPorIdReserva(int idReserva) {
		String query = "SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva "
				+ "FROM hotel_huesped WHERE idReserva=?";
		List<Huesped> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setInt(1, idReserva);

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						resultado.add(new Huesped(resultSet.getInt("id"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getString("fechaNacimiento"),
								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
								resultSet.getInt("idReserva")));
					}

				}

			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return resultado;
	}

	public List<Huesped> buscarPorApellido(String apellido) {
		String query = "SELECT a.id, a.nombre, a.apellido, a.fechaNacimiento, a.nacionalidad, a.telefono, a.idReserva "
				+ "FROM hotel_huesped a, hotel_reserva b where a.idReserva = b.id and a.apellido=?";
		List<Huesped> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setString(1, apellido);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();
				try (resultSet) {
					while (resultSet.next()) {
						resultado.add(new Huesped(resultSet.getInt("id"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getString("fechaNacimiento"),
								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
								resultSet.getInt("idReserva")));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return resultado;

	}

	public void editar(Huesped huesped) {
		String query = "UPDATE hotel_huesped "
				+ "SET nombre=?, apellido=?, fechaNacimiento=?, nacionalidad=?, telefono=?" + " WHERE id=?";
		try {
			PreparedStatement statement;
			statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setString(3, huesped.getFechaNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getId());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						huesped.setId(resultSet.getInt(1));

						System.out.println(String.format("Fue modificado el huesped= %s", huesped));
					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public int eliminar(Integer id) {
		String query = "DELETE FROM hotel_huesped WHERE idReserva=?";

		try {
			final PreparedStatement statement = con.prepareStatement(query);
			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException();
		}

	}

}
