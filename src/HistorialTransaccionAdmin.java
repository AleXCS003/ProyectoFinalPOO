import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class HistorialTransaccionAdmin extends JFrame {
    private JPanel panel1;
    private JButton regresarAlMenúButton;
    private JTextArea idtra;
    private JTextArea nombre;
    private JTextArea fecha;
    private JTextArea produ;
    private JTextArea cantidad;
    private JTextArea total;
    private JTextField buscarID;
    private JButton buscarButton;
    private Connection conexion;

    private MenuOpcionesAdmin datosMenuOp;

    public HistorialTransaccionAdmin(MenuOpcionesAdmin datosMenuOp) {
        super("Historial de Transacciones");
        this.datosMenuOp = datosMenuOp;
        regresarAlMenúButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                datosMenuOp.IniciarMenu();
                dispose();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valorID = Integer.parseInt(buscarID.getText());
                ConexionDB db = new ConexionDB();
                conexion = db.conectar();
                try {
                    String consultar_transacciones = "SELECT idTra,nombre_cajero,fecha,IdProducto,cantidad,totalProducto FROM transacciones WHERE FK_id_Cajero=?";
                    PreparedStatement consulta4 = conexion.prepareStatement(consultar_transacciones);
                    consulta4.setInt(1, valorID);
                    ResultSet resultadoTransacciones = consulta4.executeQuery();

                    while (resultadoTransacciones.next()) {
                        idtra.append(resultadoTransacciones.getString("idTra") + "\n");
                        nombre.append(resultadoTransacciones.getString("nombre_cajero") + "\n");

                        fecha.append(resultadoTransacciones.getString("fecha") + "\n");
                        produ.append(resultadoTransacciones.getString("idProducto") + "\n");
                        cantidad.append(resultadoTransacciones.getString("cantidad") + "\n");
                        total.append(resultadoTransacciones.getString("totalProducto") + "\n");
                    }


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta: " + ex.getMessage());
                }
            }
        });
    }

    public void IniciarHistorial() {
        setContentPane(panel1);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
