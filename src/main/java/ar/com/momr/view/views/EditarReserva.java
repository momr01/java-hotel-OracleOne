package ar.com.momr.view.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import ar.com.momr.back.controller.ReservaController;
import ar.com.momr.back.domain.Reserva;
import ar.com.momr.back.properties.Properties;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.text.Format;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class EditarReserva extends JFrame {

	private JPanel contentPane;
	public static JDateChooser txtFechaE;
	public static JDateChooser txtFechaS;
	public static JComboBox<Format> txtFormaPago;
	int xMouse, yMouse;
	private static JLabel lblPrecio;
	private ReservaController reservaController;
	private Reserva reserva;

	private LocalDate fe;
	private LocalDate fs;
	private String fechaEntrada;
	private String fechaSalida;
	private double precio;
	private String formaPago;

	public EditarReserva() {

	}

	public EditarReserva(Reserva reserva) {
		super("Reserva");

		this.reservaController = new ReservaController();
		this.reserva = reserva;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(EditarReserva.class.getResource("/ar/com/momr/view/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setAlwaysOnTop(true);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(Colors.general);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(Colors.general);
		panel.add(separator_1_2);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(Colors.general);
		separator_1_3.setBackground(Colors.general);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Colors.general);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(Colors.general);
		panel.add(separator_1_1);

		txtFechaE = new JDateChooser();
		txtFechaE.getCalendarButton().setBackground(Colors.general);
		txtFechaE.getCalendarButton().setIcon(
				new ImageIcon(EditarReserva.class.getResource("/ar/com/momr/view/imagenes/icon-reservas.png")));
		txtFechaE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaE.setBounds(68, 161, 289, 35);
		txtFechaE.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaE.setBackground(Color.WHITE);
		txtFechaE.setBorder(new LineBorder(Colors.general));
		txtFechaE.setDateFormatString("yyyy-MM-dd");
		txtFechaE.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaE.setMinSelectableDate(
				new Date(LocalDate.now().atStartOfDay(ZoneId.of("America/New_York")).toEpochSecond() * 1000));
		LocalDate localDateE = LocalDate.of(Integer.parseInt(this.reserva.getEntrada().substring(0, 4)),
				Integer.parseInt(this.reserva.getEntrada().substring(5, 7)),
				Integer.parseInt(this.reserva.getEntrada().substring(8, 10)));
		Date dateE = new Date(localDateE.atStartOfDay(ZoneId.of("America/New_York")).toEpochSecond() * 1000);
		txtFechaE.setDate(dateE);
		panel.add(txtFechaE);

		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 289, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);

		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 289, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);

		txtFechaS = new JDateChooser();
		txtFechaS.getCalendarButton().setIcon(
				new ImageIcon(EditarReserva.class.getResource("/ar/com/momr/view/imagenes/icon-reservas.png")));
		txtFechaS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaS.setBounds(68, 246, 289, 35);
		txtFechaS.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaS.setBackground(Color.WHITE);
		txtFechaS.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaS.setDateFormatString("yyyy-MM-dd");
		txtFechaS.getCalendarButton().setBackground(Colors.general);
		txtFechaS.setMinSelectableDate(
				new Date(LocalDate.now().atStartOfDay(ZoneId.of("America/New_York")).toEpochSecond() * 1000));
		txtFechaS.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		LocalDate localDateS = LocalDate.of(Integer.parseInt(this.reserva.getSalida().substring(0, 4)),
				Integer.parseInt(this.reserva.getSalida().substring(5, 7)),
				Integer.parseInt(this.reserva.getSalida().substring(8, 10)));
		Date dateS = new Date(localDateS.atStartOfDay(ZoneId.of("America/New_York")).toEpochSecond() * 1000);
		txtFechaS.setDate(dateS);
		panel.add(txtFechaS);

		txtFechaE.getDateEditor().addPropertyChangeListener((PropertyChangeEvent e) -> {
			if (e.getPropertyName().equals("date")) {
				generarValor();
			}

		});
		txtFechaS.getDateEditor().addPropertyChangeListener((PropertyChangeEvent e) -> {
			if (e.getPropertyName().equals("date")) {
				generarValor();
			}

		});

		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 285, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);

		lblPrecio = new JLabel(String.valueOf(this.reserva.getValor()));
		lblPrecio.setForeground(SystemColor.textInactiveText);
		lblPrecio.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblPrecio.setBounds(68, 341, 285, 14);
		panel.add(lblPrecio);

		txtFormaPago = new JComboBox<>();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel(
				new String[] { "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo" }));
		txtFormaPago.setSelectedItem(this.reserva.getFormaPago());
		panel.add(txtFormaPago);

		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		JLabel lblTitulo = new JLabel("EDITAR RESERVA");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(68, 60, 289, 42);
		lblTitulo.setForeground(Colors.general);
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		ImageIcon imageIcon = new ImageIcon(
				ReservasView.class.getResource("/ar/com/momr/view/imagenes/hotel-romm.png")); // load the image to a
																								// imageIcon
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(104, 104, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back
		logo.setIcon(imageIcon);

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 0, 500, 600);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/ar/com/momr/view/imagenes/ruta.jpg")));

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		panel.add(header);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Colors.general);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(Colors.general);
		panel.add(separator_1);

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				fe = LocalDate.ofInstant(txtFechaE.getDate().toInstant(), ZoneId.systemDefault());
				fs = LocalDate.ofInstant(txtFechaS.getDate().toInstant(), ZoneId.systemDefault());

				fechaEntrada = fe.toString();
				fechaSalida = fs.toString();

				String p = lblPrecio.getText();
				precio = Double.valueOf(p);

				formaPago = (String) txtFormaPago.getSelectedItem();
				boolean hayCambios = comprobarCambios();

				if (hayCambios) {
					editar();
				}
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
		btnEditar.setLayout(null);
		btnEditar.setBackground(Colors.general);
		btnEditar.setBounds(238, 493, 122, 35);
		panel.add(btnEditar);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnCancelar = new JPanel();
		btnCancelar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setBackground(Colors.darker);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCancelar.setBackground(Colors.general);
			}
		});
		btnCancelar.setLayout(null);
		btnCancelar.setBackground(Colors.general);
		btnCancelar.setBounds(68, 493, 122, 35);
		panel.add(btnCancelar);
		btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblCancelar = new JLabel("CANCELAR");
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setForeground(Color.WHITE);
		lblCancelar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblCancelar.setBounds(0, 0, 122, 35);
		btnCancelar.add(lblCancelar);

	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private boolean comprobarCambios() {
		if (fechaEntrada.equals(this.reserva.getEntrada()) && fechaSalida.equals(this.reserva.getSalida())
				&& precio == this.reserva.getValor() && formaPago.equals(this.reserva.getFormaPago())) {
			JOptionPane.showMessageDialog(this, "Nada para editar!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}

	}

	private void editar() {
		var reserva = new Reserva(this.reserva.getId(), fechaEntrada, fechaSalida, precio, formaPago);

		this.reservaController.editar(reserva);

		JOptionPane.showMessageDialog(this, "Actualizado con éxito!", "EXITO", JOptionPane.INFORMATION_MESSAGE);

		dispose();

	}

	private void generarValor() {
		fe = LocalDate.ofInstant(txtFechaE.getDate().toInstant(), ZoneId.systemDefault());
		fs = LocalDate.ofInstant(txtFechaS.getDate().toInstant(), ZoneId.systemDefault());

		double pr = Properties.getPrecioXDia();

		if (txtFechaE.getDate() != null && txtFechaS.getDate() != null) {
			long days = ChronoUnit.DAYS.between(fe, fs);

			double result = pr * days;

			lblPrecio.setText(String.valueOf(result));
		}

	}
}
