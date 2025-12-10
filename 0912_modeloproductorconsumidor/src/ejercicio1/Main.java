package ejercicio1;

public class Main {
    public static void main(String[] args) {
        /*
         *
         * Implementar un sistema con:
         * - 1 productor
         * - 1 consumidor
         * - Un buffer con solo un espacio
         *
         * El productor genera números del 1 al 20 y los deposita en el buffer.
         * El consumidor los retira y los imprime por pantalla.
         *
         * Restricciones:
         * - Usar wait() y notifyAll()
         * El buffer no puede permitir escribir si está lleno, ni leer si está vacío.
         * El programa debe terminar cuando el consumidor haya leído todos los números
         *
         * */


        Buffer buffer = new Buffer();

        Thread productor = new Thread(new Productor(buffer));
        Thread consumidor = new Thread(new Consumidor(buffer));

        productor.start();
        consumidor.start();
    }
}
