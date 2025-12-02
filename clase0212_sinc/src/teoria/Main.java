package teoria;

public class Main {
    public static void main(String[] args) {
        //Condiciones de carrera
        //Insconsistencia de datos
        //Lectura y escritura intercalada
        //Problemas de rendimiento
        //Los hilos NO se ejecutan en el que orden tu quieras
        //Interbloqueas (Deadlocks)

        //sychronized -> pone un lock a un objeto o a un clase
        //Zona critica -> bloque de código donde se manipula un recurso compartido

        //Forma 1 Método sincronizado: public synchronized void incrementar()

        /*
         * Forma 2: Bloque sincronizado
         * public void incrementar(){
         *   syncronized(this){
         *
         *   }
         *
         * }
         * */

        /*
         * Forma 3: Método estático sincronizado
         *
         * public static synchronized void log(String mensaje){
         *   System.out.println(mensaje);
         * }
         *
         * */


        /*
         *
         * cerrojo/candado/lock
         *
         * Syncronized por dentro
         *
         * Java usa monitores: Lock, cola de hilos esperando, métocos wait/notify
         * 1. Un hilo intenta entrar al monitor.
         * 2. Si no puede se queda bloqueado.
         * 3. Cuando el hilo dentro termina, libera el cerrojo
         * 4. El sistema despierta a uno de los hilos bloqueados
         *
         * */


    }
/*
    public void escribir(){
        System.out.println("Preparando...");

        System.out.println("Zona critica");
        synchronized (this){
            contador++;
        }
    }


*/


}
