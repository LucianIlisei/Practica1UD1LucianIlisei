import com.lucianilisei.lecturamvc.gui.LecturasControlador;
import com.lucianilisei.lecturamvc.gui.LecturasModelo;
import com.lucianilisei.lecturamvc.gui.Ventana;
import com.lucianilisei.lecturamvc.util.Utilidades;

public class Principal {
    public static void main(String [] args) {
        Ventana vista = new Ventana();
        LecturasModelo modelo = new LecturasModelo();
        Utilidades utilidades = new Utilidades();
        LecturasControlador controlador = new LecturasControlador(vista, modelo, utilidades);
    }
}
