package ar.com.momr.view.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ar.com.momr.back.controller.HuespedController;
import ar.com.momr.back.controller.ReservaController;
import ar.com.momr.back.domain.Huesped;
import ar.com.momr.back.domain.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private ReservaController reservaController;
	private HuespedController huespedController;
	private JComboBox<String> opcBusqueda;

	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Busqueda.class.getResource("/ar/com/momr/view/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(440, 125, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		txtBuscar.setText("disabled");
		txtBuscar.setEnabled(false);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Colors.general);
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(170, 62, 587, 42);
		contentPane.add(lblNewLabel_4);

		UIManager.put("TabbedPane.selected", Colors.lighter);
		UIManager.put("TabbedPane.contentAreaColor", Colors.lighter);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(Colors.general);
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		String dataR[][] = {};
		String colR[] = { "Id", "Fecha entrada", "Fecha salida", "Importe", "Forma de Pago" };
		modelo = new DefaultTableModel(dataR, colR);
		tbReservas = new JTable(modelo);
		tbReservas.setDefaultEditor(Object.class, null);
		tbReservas.setSelectionBackground(Colors.row);
		JTableHeader headerReservas = tbReservas.getTableHeader();
		headerReservas.setReorderingAllowed(false);
		headerReservas.setBackground(Colors.general);
		headerReservas.setForeground(Color.white);
		JScrollPane scrollPaneR = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/ar/com/momr/view/imagenes/reservado.png")),
				scrollPaneR, null);

		/**
		 * Forma 1 de hacerlo
		 * 
		 * 
		 **/
		/*
		 * tbHuespedes = new JTable();
		 * tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 * tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		 * tbHuespedes.setDefaultEditor(Object.class, null); panel.addTab("Huéspedes",
		 * new
		 * ImageIcon(Busqueda.class.getResource("/ar/com/momr/view/imagenes/pessoas.png"
		 * )), tbHuespedes, null); modeloH = (DefaultTableModel) tbHuespedes.getModel();
		 * modeloH.addColumn("Nombre"); modeloH.addColumn("Apellido");
		 * modeloH.addColumn("Fecha de Nacimiento"); modeloH.addColumn("Nacionalidad");
		 * modeloH.addColumn("Telefono"); modeloH.addColumn("Numero de Reserva");
		 */
		String dataH[][] = {};
		String colH[] = { "Id", "Nombre", "Apellido", "Fecha de nacimiento", "Nacionalidad", "Teléfono", "Id Reserva" };
		modeloH = new DefaultTableModel(dataH, colH);
		tbHuespedes = new JTable(modeloH);
		tbHuespedes.setDefaultEditor(Object.class, null);
		tbHuespedes.setSelectionBackground(Colors.row);
		JTableHeader headerHuespedes = tbHuespedes.getTableHeader();
		headerHuespedes.setReorderingAllowed(false);
		headerHuespedes.setBackground(Colors.general);
		headerHuespedes.setForeground(Color.white);
		JScrollPane scrollPaneH = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/ar/com/momr/view/imagenes/pessoas.png")),
				scrollPaneH, null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		ImageIcon imageIcon = new ImageIcon(Busqueda.class.getResource("/ar/com/momr/view/imagenes/hotel-romm.png")); // load
																														// the
																														// image
																														// to
																														// a
																														// imageIcon
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(104, 104, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back
		lblNewLabel_2.setIcon(imageIcon);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		final JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Colors.general);
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		final JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(Colors.general);
		separator_1_2.setBackground(Colors.general);
		separator_1_2.setBounds(440, 157, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				resetearTablas();

				String inputValue = txtBuscar.getText();
				buscar(inputValue);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnbuscar.setBackground(Colors.darker);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnbuscar.setBackground(Colors.general);
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(Colors.general);
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(Colors.general);
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (scrollPaneR.isVisible() && tbReservas.getSelectedRow() != -1)
					editarReserva();
				else if (scrollPaneH.isVisible() && tbHuespedes.getSelectedRow() != -1)
					editarHuesped();
				else
					JOptionPane.showMessageDialog(null, "Nada para editar!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(Colors.darker);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(Colors.general);
			}
		});
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(Colors.general);
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tbReservas.isVisible() && tbReservas.getSelectedRow() != -1)
					eliminarReserva();
				else if (tbHuespedes.isVisible() && tbHuespedes.getSelectedRow() != -1)
					eliminarHuesped();
				else
					JOptionPane.showMessageDialog(null, "Nada para eliminar!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnEliminar.setBackground(Colors.darker);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEliminar.setBackground(Colors.general);
			}
		});
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);

		opcBusqueda = new JComboBox<String>();
		opcBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBuscar.setText("");
				if (opcBusqueda.getSelectedItem().toString().equalsIgnoreCase("todo")) {
					txtBuscar.setText("disabled");
					txtBuscar.setEnabled(false);
				} else
					txtBuscar.setEnabled(true);
			}
		});
		opcBusqueda.setModel(new DefaultComboBoxModel<String>(new String[] { "todo", "nro reserva", "apellido" }));
		opcBusqueda.setBounds(635, 125, 103, 34);
		opcBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(opcBusqueda);

		setResizable(false);
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private void cargarTodasLasReservas() {
		var reservas = this.reservaController.listar();
		recorrerTablaReservas(reservas);
	}

	private void cargarTodosLosHuespedes() {
		var huespedes = this.huespedController.listar();
		recorrerTablaHuespedes(huespedes);
	}

	private void buscar(String value) {
		String input = opcBusqueda.getSelectedItem().toString();
		if (input.equalsIgnoreCase("todo")) {
			cargarTodasLasReservas();
			cargarTodosLosHuespedes();
		} else {
			validarInput(value);
		}

	}

	private void validarInput(String value) {
		if (value.isEmpty())
			JOptionPane.showMessageDialog(this, "Por favor ingrese un parámetro de búsqueda válido", "ADVERTENCIA",
					JOptionPane.WARNING_MESSAGE);
		else if (opcBusqueda.getSelectedItem().toString().equalsIgnoreCase("apellido"))
			cargarDatosPorApellido(value);
		else if (opcBusqueda.getSelectedItem().toString().equalsIgnoreCase("nro reserva")) {
			try {
				cargarDatosPorNroReserva(Integer.parseInt(value));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Para buscar por número de reserva, debe ingresar sólo números.",
						"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
				throw new RuntimeException(e);
			}
		}

	}

	private void cargarDatosPorNroReserva(int value) {
		Reserva reserva = this.reservaController.buscarPorNroReserva(value);
		if (reserva != null) {
			modelo.addRow(new Object[] { reserva.getId(), reserva.getEntrada(), reserva.getSalida(), reserva.getValor(),
					reserva.getFormaPago() });

			var huespedes = this.huespedController.buscarPorIdReserva(reserva.getId());
			recorrerTablaHuespedes(huespedes);
		} else {
			JOptionPane.showMessageDialog(this, "No se encontraron resultados.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void cargarDatosPorApellido(String value) {
		var reservas = this.reservaController.buscarPorApellido(value);
		var huespedes = this.huespedController.buscarPorApellido(value);

		if (reservas.isEmpty() || huespedes.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No se encontraron resultados.", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else {
			recorrerTablaReservas(reservas);
			recorrerTablaHuespedes(huespedes);
		}

	}

	private void recorrerTablaReservas(List<Reserva> reservas) {
		reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(), reserva.getEntrada(),
				reserva.getSalida(), reserva.getValor(), reserva.getFormaPago() }));

	}

	private void recorrerTablaHuespedes(List<Huesped> huespedes) {
		huespedes.forEach(huesped -> modeloH.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
				huesped.getIdReserva() }));

	}

	private void editarReserva() {
		int idR = (int) modelo.getValueAt(tbReservas.getSelectedRow(), 0);

		Reserva reserva = this.reservaController.buscarPorId(idR);
		EditarReserva editar = new EditarReserva(reserva);
		editar.setVisible(true);
	}

	private void editarHuesped() {
		int idH = (int) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0);

		Huesped huesped = this.huespedController.buscarPorId(idH);
		EditarHuesped editar = new EditarHuesped(huesped);
		editar.setVisible(true);

	}

	private void eliminarReserva() {
		int idR = (int) modelo.getValueAt(tbReservas.getSelectedRow(), 0);
		eliminar(idR);

	}

	private void eliminarHuesped() {
		int idH = (int) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0);
		Huesped huesped = this.huespedController.buscarPorId(idH);
		eliminar(huesped.getIdReserva());
	}

	private void eliminar(int id) {
		String[] options = { "Eliminar", "Cancelar" };
		int x = JOptionPane.showOptionDialog(null, "Se borrará la reserva y el huésped relacionado.", "ADVERTENCIA",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if (x == 0) {
			try {
				this.huespedController.eliminar(id);
				this.reservaController.eliminar(id);
				JOptionPane.showMessageDialog(this, "Se eliminó el registro.", "EXITO",
						JOptionPane.INFORMATION_MESSAGE);
				resetearTablas();
				cargarTodasLasReservas();
				cargarTodosLosHuespedes();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Ocurrió un error. No se eliminaron los registros.", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	private void resetearTablas() {
		modelo.setRowCount(0);
		modeloH.setRowCount(0);
	}
}
