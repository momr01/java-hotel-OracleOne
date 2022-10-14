package ar.com.momr.back.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.momr.back.domain.Reserva;

public class ReservaDAO {

	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public List<Reserva> listar() {
		String query = "SELECT id, entrada, salida, valor, formaPago, nroReserva FROM hotel_reserva";
		List<Reserva> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(query);
			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						resultado.add(new Reserva(resultSet.getInt("id"), resultSet.getString("entrada"),
								resultSet.getString("salida"), resultSet.getDouble("valor"),
								resultSet.getString("formaPago"), resultSet.getInt("nroReserva")));
					}

				}

			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return resultado;
	}

	public void guardar(Reserva reserva) {
		String query = "INSERT INTO hotel_reserva "
				+ "(entrada, salida, valor, formaPago, nroReserva) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement;
			statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setString(1, reserva.getEntrada());
				statement.setString(2, reserva.getSalida());
				statement.setDouble(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				statement.setInt(5, reserva.getNroReserva());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));

						System.out.println(String.format("Fue insertada la reserva= %s", reserva));
					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public Reserva buscarPorId(int id) {
		String query = "SELECT id, entrada, salida, valor, formaPago, nroReserva " + "FROM hotel_reserva WHERE id=?";
		Reserva reserva = null;

		try {
			final PreparedStatement statement = con.prepareStatement(query);
			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						reserva = new Reserva(resultSet.getInt("id"), resultSet.getString("entrada"),
								resultSet.getString("salida"), resultSet.getDouble("valor"),
								resultSet.getString("formaPago"), resultSet.getInt("nroReserva"));
					}

				}

			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return reserva;

	}

	public Reserva buscarPorIdReserva(int nroReserva) {
		String query = "SELECT id, entrada, salida, valor, formaPago, nroReserva "
				+ "FROM hotel_reserva WHERE nroReserva=?";
		Reserva reserva = null;

		try {
			final PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setInt(1, nroReserva);

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						reserva = new Reserva(resultSet.getInt("id"), resultSet.getString("entrada"),
								resultSet.getString("salida"), resultSet.getDouble("valor"),
								resultSet.getString("formaPago"), resultSet.getInt("nroReserva"));
					}

				}

			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return reserva;
	}

	public List<Reserva> buscarPorApellido(String apellido) {
		String query = "SELECT a.id, a.entrada, a.salida, a.valor, a.formaPago, a.nroReserva "
				+ "FROM hotel_reserva a, hotel_huesped b where a.id = b.idReserva and b.apellido=?";
		List<Reserva> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setString(1, apellido);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();
				try (resultSet) {
					while (resultSet.next()) {
						resultado.add(new Reserva(resultSet.getInt("id"), resultSet.getString("entrada"),
								resultSet.getString("salida"), resultSet.getDouble("valor"),
								resultSet.getString("formaPago"), resultSet.getInt("nroReserva")));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return resultado;

	}

	public void editar(Reserva reserva) {
		String query = "UPDATE hotel_reserva " + "SET entrada=?, salida=?, valor=?, formaPago=?" + " WHERE id=?";
		try {
			PreparedStatement statement;
			statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setString(1, reserva.getEntrada());
				statement.setString(2, reserva.getSalida());
				statement.setDouble(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				statement.setInt(5, reserva.getId());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));

						System.out.println(String.format("Fue modificada la reserva= %s", reserva));
					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public int eliminar(Integer id) {
		String query = "DELETE FROM hotel_reserva WHERE id=?";

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
