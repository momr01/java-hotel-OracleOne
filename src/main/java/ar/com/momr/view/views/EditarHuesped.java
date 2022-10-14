package ar.com.momr.view.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import ar.com.momr.back.controller.HuespedController;
import ar.com.momr.back.controller.ReservaController;
import ar.com.momr.back.domain.Huesped;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.Format;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class EditarHuesped extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JDateChooser txtFechaN;
	private JComboBox<Format> txtNacionalidad;
	int xMouse, yMouse;
	private Huesped huesped;
	private int nroReserva;
	private HuespedController huespedController;
	private ReservaController reservaController;

	private String nombre;
	private String apellido;
	private String fechaNac;
	private String nacionalidad;
	private String telefono;

	public EditarHuesped() {

	}

	public EditarHuesped(Huesped huesped) {
		this.huesped = huesped;
		this.huespedController = new HuespedController();
		this.reservaController = new ReservaController();

		this.nroReserva = this.reservaController.buscarPorId(huesped.getIdReserva()).getNroReserva();

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(RegistroHuesped.class.getResource("/ar/com/momr/view/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
		setAlwaysOnTop(true);

		JPanel header = new JPanel();
		header.setBounds(120, 0, 790, 36);
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
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		txtNombre = new JTextField(this.huesped.getNombre());
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNombre.setBounds(560, 135, 285, 33);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);

		txtApellido = new JTextField(this.huesped.getApellido());
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtApellido.setBounds(560, 204, 285, 33);
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);

		txtFechaN = new JDateChooser();
		txtFechaN.setBounds(560, 278, 285, 36);
		txtFechaN.getCalendarButton().setIcon(
				new ImageIcon(RegistroHuesped.class.getResource("/ar/com/momr/view/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(Colors.general);
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtFechaN);
		LocalDate localDate = LocalDate.of(Integer.parseInt(this.huesped.getFechaNacimiento().substring(0, 4)),
				Integer.parseInt(this.huesped.getFechaNacimiento().substring(5, 7)),
				Integer.parseInt(this.huesped.getFechaNacimiento().substring(8, 10)));
		Date date = new Date(localDate.atStartOfDay(ZoneId.of("America/New_York")).toEpochSecond() * 1000);
		txtFechaN.setDate(date);

		txtNacionalidad = new JComboBox<>();
		txtNacionalidad.setBounds(560, 350, 289, 36);
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNacionalidad.setModel(new DefaultComboBoxModel(new String[] { "afgano-afgana", "alemán-", "alemana",
				"árabe-árabe", "argentino-argentina", "australiano-australiana", "belga-belga", "boliviano-boliviana",
				"brasileño-brasileña", "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china",
				"colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana",
				"danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña",
				"escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia",
				"etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa",
				"griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa",
				"hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní",
				"irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana",
				"laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí",
				"mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa",
				"panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa",
				"puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca",
				"suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana",
				"uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita" }));
		txtNacionalidad.setSelectedItem(this.huesped.getNacionalidad());
		contentPane.add(txtNacionalidad);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(562, 119, 253, 14);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(560, 189, 255, 14);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblApellido);

		JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		lblFechaN.setBounds(560, 256, 255, 14);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblFechaN);

		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setBounds(560, 326, 255, 14);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidad);

		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(562, 406, 253, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField(this.huesped.getTelefono());
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setBounds(560, 424, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);

		JLabel lblTitulo = new JLabel("EDITAR HUÉSPED");
		lblTitulo.setBounds(560, 55, 289, 42);
		lblTitulo.setForeground(Colors.general);
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPane.add(lblTitulo);

		JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
		lblNumeroReserva.setBounds(560, 474, 253, 14);
		lblNumeroReserva.setForeground(SystemColor.textInactiveText);
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNumeroReserva);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 170, 289, 2);
		separator_1_2.setForeground(Colors.general);
		separator_1_2.setBackground(Colors.general);
		contentPane.add(separator_1_2);

		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 240, 289, 2);
		separator_1_2_1.setForeground(Colors.general);
		separator_1_2_1.setBackground(Colors.general);
		contentPane.add(separator_1_2_1);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 314, 289, 2);
		separator_1_2_2.setForeground(Colors.general);
		separator_1_2_2.setBackground(Colors.general);
		contentPane.add(separator_1_2_2);

		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 386, 289, 2);
		separator_1_2_3.setForeground(Colors.general);
		separator_1_2_3.setBackground(Colors.general);
		contentPane.add(separator_1_2_3);

		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 457, 289, 2);
		separator_1_2_4.setForeground(Colors.general);
		separator_1_2_4.setBackground(Colors.general);
		contentPane.add(separator_1_2_4);

		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 529, 289, 2);
		separator_1_2_5.setForeground(Colors.general);
		separator_1_2_5.setBackground(Colors.general);
		contentPane.add(separator_1_2_5);

		JPanel btnguardar = new JPanel();
		btnguardar.setBounds(723, 560, 122, 35);
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JButton btnGuardar = new JButton("EDITAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombre = txtNombre.getText();
				apellido = txtApellido.getText();
				LocalDate fn = LocalDate.ofInstant(txtFechaN.getDate().toInstant(), ZoneId.systemDefault());
				fechaNac = String.valueOf(fn);
				nacionalidad = txtNacionalidad.getSelectedItem().toString();
				telefono = txtTelefono.getText();

				boolean sePuedeEditar = comprobarCambios();
				if (sePuedeEditar) {
					editarHuesped();
					redirigir();
				}

			}
		});
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGuardar.setBackground(Colors.darker);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnGuardar.setBackground(Colors.general);
			}
		});
		btnGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Colors.general);
		btnGuardar.setOpaque(true);
		btnGuardar.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnGuardar.setBounds(0, 0, 122, 35);
		btnguardar.add(btnGuardar);

		JPanel btncancelar = new JPanel();
		btncancelar.setBounds(570, 560, 122, 35);

		btncancelar.setLayout(null);
		btncancelar.setBackground(Colors.general);
		contentPane.add(btncancelar);
		btncancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setBackground(Colors.darker);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCancelar.setBackground(Colors.general);
			}
		});

		btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Colors.general);
		btnCancelar.setOpaque(true);
		btnCancelar.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnCancelar.setBounds(0, 0, 122, 35);
		btncancelar.add(btnCancelar);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 0, 500, 700);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/ar/com/momr/view/imagenes/river.jpg")));

		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/ar/com/momr/view/imagenes/Ha-100px.png")));

		JLabel lblIdReserva = new JLabel(String.valueOf(nroReserva));
		lblIdReserva.setForeground(SystemColor.textInactiveText);
		lblIdReserva.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblIdReserva.setBounds(560, 505, 285, 14);
		contentPane.add(lblIdReserva);

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
		if (this.huesped.getNombre().equals(nombre) && this.huesped.getApellido().equals(apellido)
				&& this.huesped.getFechaNacimiento().equals(fechaNac)
				&& this.huesped.getNacionalidad().equals(nacionalidad) && this.huesped.getTelefono().equals(telefono)) {
			JOptionPane.showMessageDialog(this, "Nada para editar!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	private void editarHuesped() {
		var huesped = new Huesped(this.huesped.getId(), nombre, apellido, fechaNac, nacionalidad, telefono);

		this.huespedController.editar(huesped);

	}

	private void redirigir() {
		JOptionPane.showMessageDialog(this, "Modificado con éxito!", "EXITO", JOptionPane.INFORMATION_MESSAGE);
		dispose();

	}
}
