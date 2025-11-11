package ejercicios2;

public class Main {
    public static void main(String[] args) {
        GestorProcesos g = new GestorProcesos();
        g.ejecutarComando("dir");
        g.redirigirSalida("ipconfig","salida.txt");
        g.mostrarErrores("comandoInexistente");
    }
}
