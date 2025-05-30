import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class VentanaPrincipal extends JFrame implements ItemListener {
    private JComboBox<String> comboTipoUsuario;
    private JCheckBox checkTerminos;
    private JTextField campoNombre;
    private JTextField campoEmail;
    private JTextField campoTelefono;

    public VentanaPrincipal() {
        setTitle("Formulario de Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centra la ventana

        // Menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem nuevoItem = new JMenuItem("Nuevo");
        JMenuItem guardarItem = new JMenuItem("Guardar");
        JMenuItem salirItem = new JMenuItem("Salir");

        menuArchivo.add(nuevoItem);
        menuArchivo.add(guardarItem);
        menuArchivo.addSeparator(); // separador visual
        menuArchivo.add(salirItem);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);
        salirItem.addActionListener(e -> System.exit(0));

        // Panel principal
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));

        JLabel etiquetaNombre = new JLabel("Nombre:");
        campoNombre = new JTextField(20);

        JLabel etiquetaEmail = new JLabel("Email:");
        campoEmail = new JTextField(20);

        JLabel etiquetaTelefono = new JLabel("Teléfono:");
        campoTelefono = new JTextField(20);

        // Combobox
        JLabel etiquetaTipoUsuario = new JLabel("Tipo de Usuario:");
        comboTipoUsuario = new JComboBox<String>();
        comboTipoUsuario.addItem("Cliente");
        comboTipoUsuario.addItem("Empleado");
        comboTipoUsuario.addItem("Administrador");
        comboTipoUsuario.addItemListener(this); 


        // Botón "Guardar"
        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.setEnabled(false); // Empieza deshabilitado, se habilita luego bajo una condición

        botonGuardar.addActionListener((e) -> {
            String nombre = campoNombre.getText();
            String email = campoEmail.getText();
            String telefono = campoTelefono.getText();
            String tipoUsuario = (String) comboTipoUsuario.getSelectedItem();
            if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                return;
            }

            if(!checkTerminos.isSelected()) {
                JOptionPane.showMessageDialog(null, "Debe aceptar los Términos y Condiciones para continuar.");
                return;
            }
            
            try {
                FileWriter writer = new FileWriter("datos_usuario.txt", true);
                writer.write("Nombre: " + nombre + ", Email: " + email + ", Teléfono: " + telefono + "\n");
                writer.close();
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar los datos.");
            }
        });

        // Botón "Limpiar"
        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.addActionListener(e -> {
            campoNombre.setText("");
            campoEmail.setText("");
            campoTelefono.setText("");
            comboTipoUsuario.setSelectedIndex(0);
            checkTerminos.setSelected(false);
        });

        // Check
        checkTerminos= new JCheckBox("Acepto los Términos y Condiciones");
        checkTerminos.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    botonGuardar.setEnabled(true); // Activa el botón si se marca el check
                } else {
                    botonGuardar.setEnabled(false); // Desactiva el botón si se desmarca el check
                }
            }
        });

        panelFormulario.add(etiquetaNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(etiquetaEmail);
        panelFormulario.add(campoEmail);
        panelFormulario.add(etiquetaTelefono);
        panelFormulario.add(campoTelefono);
        panelFormulario.add(etiquetaTipoUsuario);
        panelFormulario.add(comboTipoUsuario);
        panelFormulario.add(checkTerminos);
        panelFormulario.add(botonGuardar);
        panelFormulario.add(botonLimpiar);
        add(panelFormulario);
        cargarDatos();

    }  

    @Override
    public void itemStateChanged (ItemEvent e) { // método para cambiar el título según el tipo de usuario seleccionado (totalmente opcional)
        if (e.getSource() == comboTipoUsuario && e.getStateChange() == ItemEvent.SELECTED) {
            String seleccionado = (String) comboTipoUsuario.getSelectedItem();
            setTitle("Formulario - " + seleccionado);
        }
    }

    private void cargarDatos() {
        File archivo = new File("datos_usuario.txt");
        if (!archivo.exists()) return;

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            String ultimaLinea = null;

            while ((linea = lector.readLine()) != null) {
                ultimaLinea = linea;
            }
            if (ultimaLinea != null) {
                String[] partes = ultimaLinea.split(", ");
                if (partes.length == 3) {
                    campoNombre.setText(partes[0].substring(partes[0].indexOf(":") + 2));
                    campoEmail.setText(partes[1].substring(partes[1].indexOf(":") + 2));
                    campoTelefono.setText(partes[2].substring(partes[2].indexOf(":") + 2));
                }
            }
        } catch (IOException e) {}
    }


    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true); //mostrar ventana
    }
}
