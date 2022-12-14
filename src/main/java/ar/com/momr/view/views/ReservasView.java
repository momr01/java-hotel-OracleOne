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
public class ReservasView extends JFrame {

	private JPanel contentPane;
	public static JDateChooser txtFechaE;
	public static JDateChooser txtFechaS;
	public static JComboBox<Format> txtFormaPago;
	int xMouse, yMouse;
	private JLabel labelAtras;
	private static JLabel lblPrecio;
	private LocalDate fe;
	private LocalDate fs;

	public ReservasView() {
		super("Reserva");

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ReservasView.class.getResource("/ar/com/momr/view/imagenes/aH-40px.png")));
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

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(Colors.general);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(Colors.general);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Colors.general);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		txtFechaE = new JDateChooser();
		txtFechaE.getCalendarButton().setBackground(Colors.general);
		txtFechaE.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/ar/com/momr/view/imagenes/icon-reservas.png")));
		txtFechaE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaE.setBounds(68, 161, 289, 35);
		txtFechaE.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaE.setBackground(Color.WHITE);
		txtFechaE.setBorder(new LineBorder(SystemColor.window));
		txtFechaE.setDateFormatString("yyyy-MM-dd");
		txtFechaE.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaE.setMinSelectableDate(
				new Date(LocalDate.now().atStartOfDay(ZoneId.of("America/New_York")).toEpochSecond() * 1000));
		txtFechaE.getDateEditor().addPropertyChangeListener((PropertyChangeEvent e) -> {
			if (e.getPropertyName().equals("date")) {
				fe = LocalDate.ofInstant(ReservasView.txtFechaE.getDate().toInstant(), ZoneId.systemDefault());
				generarValor();
			}

		});
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
		txtFechaS.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/ar/com/momr/view/imagenes/icon-reservas.png")));
		txtFechaS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaS.setBounds(68, 246, 289, 35);
		txtFechaS.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaS.setBackground(Color.WHITE);
		txtFechaS.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaS.setDateFormatString("yyyy-MM-dd");
		txtFechaS.getCalendarButton().setBackground(Colors.general);
		txtFechaS.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		txtFechaS.setMinSelectableDate(
				new Date(LocalDate.now().atStartOfDay(ZoneId.of("America/New_York")).toEpochSecond() * 1000));
		txtFechaS.getDateEditor().addPropertyChangeListener((PropertyChangeEvent e) -> {
			if (e.getPropertyName().equals("date")) {
				fs = LocalDate.ofInstant(ReservasView.txtFechaS.getDate().toInstant(), ZoneId.systemDefault());
				generarValor();
			}

		});
		panel.add(txtFechaS);

		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 285, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);

		lblPrecio = new JLabel("0");
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
				new String[] { "Tarjeta de Cr??dito", "Tarjeta de D??bito", "Dinero en efectivo" }));
		panel.add(txtFormaPago);

		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
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
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Colors.general);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);

		JPanel btnsiguiente = new JPanel();
		btnsiguiente.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int nroReserva = (100000) + (int) (Math.random() * (999999 - 100000 + 1));

				if (ReservasView.txtFechaE.getDate() != null && ReservasView.txtFechaS.getDate() != null) {
					guardar(nroReserva);

				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnsiguiente.setBackground(Colors.darker);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnsiguiente.setBackground(Colors.general);
			}

		});
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(Colors.general);
		btnsiguiente.setBounds(238, 493, 122, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);
		btnsiguiente.add(lblSiguiente);

	}

	// C??digo que permite mover la ventana por la pantalla seg??n la posici??n de "x"
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

	private void guardar(int nroReserva) {
		String fechaEntrada = fe.toString();
		String fechaSalida = fs.toString();

		String p = ReservasView.lblPrecio.getText();
		Double precio = Double.valueOf(p);

		String formaPago = (String) ReservasView.txtFormaPago.getSelectedItem();

		JOptionPane.showMessageDialog(this, "Se gener?? la reserva " + nroReserva + ".", "EXITO",
				JOptionPane.INFORMATION_MESSAGE);

		RegistroHuesped registro = new RegistroHuesped(fechaEntrada, fechaSalida, precio, formaPago, nroReserva);
		registro.setVisible(true);
		dispose();

	}

	private void generarValor() {
		double pr = Properties.getPrecioXDia();

		if (ReservasView.txtFechaE.getDate() != null && ReservasView.txtFechaS.getDate() != null) {
			long days = ChronoUnit.DAYS.between(fe, fs);

			double result = pr * days;

			lblPrecio.setText(String.valueOf(result));
		}

	}
}
