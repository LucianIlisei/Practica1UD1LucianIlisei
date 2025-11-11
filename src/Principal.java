import com.lucianilisei.lecturamvc.gui.LecturasControlador;
import com.lucianilisei.lecturamvc.gui.LecturasModelo;
import com.lucianilisei.lecturamvc.gui.Ventana;

public class Principal {
    public static void main(String [] args) {
        Ventana vista = new Ventana();
        LecturasModelo modelo = new LecturasModelo();
        LecturasControlador controlador = new LecturasControlador(vista, modelo);
    }
}
