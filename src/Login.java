import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame{
    private JTextField user;
    public  JTextField getUser(){
        return user;
    }
    private JPasswordField password;
    public  JPasswordField getPassword(){
        return password;
    }
    private JButton iniciarSesiónButton;
    private JComboBox eleccion;

    public JComboBox getEleccion() {
        return eleccion;
    }

    private JPanel panel1;
    private JButton salirDelSistemaButton;
    Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void IniciarLogin(){
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public Login() {
        super("Iniciar Sesión");
        setSize(510,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        iniciarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConexionDB db=new ConexionDB();
                conexion=db.conectar();

                JComboBox<String> modoComboBox = new JComboBox<>(new String[]{"Administrador", "Cajero"});
                //String modoSeleccionado = (String) modoComboBox.getSelectedItem();
                int modoSeleccionado =(int)  eleccion.getSelectedIndex();
                switch (modoSeleccionado){

                    case 1:

                        if(conexion!=null) {
                            try {
                                String contrasena_ingresada = password.getText();
                                String consultausaurios = "SELECT *FROM Usuarios WHERE usuario=? AND contrasenia=?";
                                PreparedStatement consult = conexion.prepareStatement(consultausaurios);
                                consult.setString(1, getUser().getText());
                                consult.setString(2, getPassword().getText());
                                ResultSet resultado_consulta = consult.executeQuery();


                                if (resultado_consulta.next()) {
                                    JOptionPane.showMessageDialog(null, "Inicio de sesión correcto");
                                    MenuOpcionesAdmin menu = new MenuOpcionesAdmin(/*Login.this*/);
                                    menu.IniciarMenu();
                                    //opcion.setVisible(true);
                                    dispose();


                                } else {

                                    JOptionPane.showMessageDialog(null, "Usuario o clave incorrecto");

                                    password.setText("");
                                    user.setText("");
                                }
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Se ha producido un error");
                            }

                        }
                        break;
                    case 2:

                        if(conexion!=null) {
                            try {
                                String consultausaurios = "SELECT *FROM Usuarios WHERE usuario=? AND contrasenia=?";
                                PreparedStatement consult = conexion.prepareStatement(consultausaurios);
                                consult.setString(1, getUser().getText());
                                consult.setString(2, getPassword().getText());
                                ResultSet resultado_consulta = consult.executeQuery();


                                if (resultado_consulta.next()) {
                                    JOptionPane.showMessageDialog(null, "Inicio de sesión correcto");
                                    MenuOpcionesCajero opcion = new MenuOpcionesCajero(Login.this);
                                    opcion.IniciarMenu();
                                    opcion.setVisible(true);
                                    dispose();


                                } else {

                                    JOptionPane.showMessageDialog(null, "Usuario o clave incorrecto");

                                    password.setText("");
                                    user.setText("");
                                }
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Se ha producido un error");
                            }

                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Seleccione el  modo correcto");

                }

            }
        });
        salirDelSistemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Muchas gracias por usar el servicio");
                dispose();
            }
        });
    }
}