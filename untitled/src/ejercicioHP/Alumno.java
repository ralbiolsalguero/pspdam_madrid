package ejercicioHP;

public class Alumno implements Runnable{
    private Caldero caldero;
    private String nombre;

    public Alumno(Caldero c, String nombre){
        this.caldero = c;
        this.nombre = nombre;
    }


    @Override
    public void run() {
        for(int i = 0;i<5;i++){
            caldero.aniadirIngrediente(nombre);
        }
    }
}
