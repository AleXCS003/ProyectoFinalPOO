import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuOpcionesCajero  extends JFrame{
    private JButton historialDeTransaccionesButton;
    private JButton ventaDeProductosButton;
    private JButton cerrarSesiónButton;
    private JPanel panelCajero;
    private Login datosLogin;

    public MenuOpcionesCajero(Login datosLogin) {
        this.datosLogin=datosLogin;
        historialDeTransaccionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        HistorialTransaccionCajero historial =new HistorialTransaccionCajero(datosLogin);
        historial.IniciarHistorial();
        dispose();

            }
        });
        ventaDeProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentaProductosCajero venta=new VentaProductosCajero(MenuOpcionesCajero.this,datosLogin.getUser().getText());
                venta.iniciarVenta();
                dispose();
            }
        });
        cerrarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventanaLogin = new Login();
                ventanaLogin.IniciarLogin();
                dispose();

            }
        });
    }

    public void IniciarMenu(){
        setContentPane(panelCajero);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}

