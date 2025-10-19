package SJF;


import java.util.Scanner;


public class Main {


    public static void main(String[] args) {


        int n; // Aquí creamos una variable para guardar el número de procesos que el usuario quiere introducir.

        Scanner scn = new Scanner(System.in);

        System.out.println("Introduzca el número de procesos");

        n = scn.nextInt();

        // --------------------------------------------------------------
        // Ahora creamos varios arrays (listas de valores) del tamaño "n",
        // uno por cada dato que necesitamos guardar de los procesos.
        // --------------------------------------------------------------

        int[] llegada = new int[n];       // "llegada[i]" guarda el momento en el que el proceso i llega al sistema.
        int[] rafaga = new int[n];        // "rafaga[i]" guarda cuánto tiempo necesita ejecutarse el proceso i (duración).
        int[] finalizacion = new int[n];  // "finalizacion[i]" guarda el momento en el que termina el proceso i.
        int[] retorno = new int[n];       // "retorno[i]" guarda el tiempo total desde el inicio (t=0) hasta que termina.
        int[] espera = new int[n];        // "espera[i]" guarda cuánto tiempo ha estado esperando antes de ejecutarse.
        boolean[] completado = new boolean[n];
        // "completado[i]" indica si el proceso i ya ha terminado (true) o no (false).

        // --------------------------------------------------------------
        // PEDIMOS LOS DATOS DE CADA PROCESO
        // --------------------------------------------------------------

        for (int i = 0; i < n; i++) {
            // Este bucle se repetirá una vez por cada proceso (desde 0 hasta n-1).
            // Así pedimos al usuario los datos de todos los procesos.

            System.out.println("\nProceso " + (i + 1) + ":");
            // Mostramos "Proceso 1", luego "Proceso 2", etc.
            // El "+ (i + 1)" es porque los arrays empiezan en 0, pero los procesos se numeran desde 1.

            System.out.println("Tiempo de llegada: ");
            // Pedimos al usuario el instante de llegada del proceso.

            llegada[i] = scn.nextInt();
            // Leemos ese número y lo guardamos en el array "llegada".

            System.out.println("Tiempo de ráfaga: ");
            // Pedimos ahora cuánto tiempo necesita ese proceso para ejecutarse (su ráfaga o duración).

            rafaga[i] = scn.nextInt();
            // Guardamos ese número en el array "rafaga".
        }

        // --------------------------------------------------------------
        // VARIABLES DE CONTROL DE TIEMPO Y PROCESOS
        // --------------------------------------------------------------

        int tiempo = 0; // "tiempo" representa el reloj del sistema (el instante actual en el diagrama de Gantt).
        int procesosCompletados = 0; // Contador para saber cuántos procesos se han ejecutado ya.

        // --------------------------------------------------------------
        // BUCLE PRINCIPAL DEL ALGORITMO SJF (Shortest Job First)
        // --------------------------------------------------------------
        // Este algoritmo siempre elige el proceso con la ráfaga más corta
        // entre los que ya han llegado al sistema.
        // --------------------------------------------------------------

        while (procesosCompletados < n) {
            // Repetimos este bucle mientras haya procesos sin ejecutar.

            int idx = -1; // Este índice servirá para guardar el número del proceso seleccionado.
            // Comienza en -1 (que significa "ninguno seleccionado todavía").

            int menorRafaga = Integer.MAX_VALUE;
            // Guardará la ráfaga más pequeña encontrada hasta el momento.
            // Empezamos con un valor muy grande (MAX_VALUE = número máximo posible)
            // para asegurarnos de que cualquier ráfaga real será menor.

            // ----------------------------------------------------------
            // BUSCAR ENTRE LOS PROCESOS CUÁL TIENE LA RÁFAGA MÁS CORTA
            // ----------------------------------------------------------
            for (int i = 0; i < n; i++) {
                // Recorremos todos los procesos.
                if (!completado[i] && llegada[i] <= tiempo && rafaga[i] < menorRafaga) {
                    // Condiciones:
                    // 1️ !completado[i]  → el proceso todavía no ha terminado.
                    // 2️ llegada[i] <= tiempo → el proceso ya ha llegado al sistema (está disponible).
                    // 3️ rafaga[i] < menorRafaga → su ráfaga es la más pequeña encontrada hasta ahora.

                    menorRafaga = rafaga[i]; // Actualizamos el valor de la ráfaga mínima.
                    idx = i; // Guardamos la posición del proceso elegido.
                }
            }

            if (idx == -1) {
                // Si no encontramos ningún proceso disponible en este instante (ninguno ha llegado todavía),
                // simplemente adelantamos el tiempo una unidad (como si pasara un segundo).
                tiempo++;
            } else {
                // Si sí encontramos un proceso disponible, lo ejecutamos completo (SJF no es apropiativo).
                tiempo += rafaga[idx];
                // Avanzamos el reloj el tiempo que dura ese proceso (sumamos su ráfaga).
                finalizacion[idx] = tiempo;
                // Guardamos en qué instante del reloj terminó.

                // ----------------------------------------------------------
                // CÁLCULO DE TIEMPOS (ajustado al modelo del diagrama)
                // ----------------------------------------------------------
                retorno[idx] = finalizacion[idx];
                // Tiempo de retorno = instante de finalización (desde t=0).
                // Ejemplo: si termina en el segundo 15, retorno = 15.

                espera[idx] = finalizacion[idx] - rafaga[idx] - llegada[idx];
                // Tiempo de espera = tiempo total - tiempo que ejecutó - tiempo que tardó en llegar.
                // Es decir, cuánto tiempo ha estado esperando antes de poder ejecutarse.

                completado[idx] = true; // Marcamos este proceso como terminado.
                procesosCompletados++;  // Aumentamos el contador de procesos terminados.
            }
        }

        // --------------------------------------------------------------
        // MOSTRAR RESULTADOS EN FORMA DE TABLA
        // --------------------------------------------------------------

        System.out.println("\n---------------------------------------------------------------");
        System.out.println("Proceso\tLlegada\tRafaga\tFinal\tRetorno\tEspera");
        System.out.println("---------------------------------------------------------------");

        // Recorremos todos los procesos para mostrar sus valores uno a uno
        for (int i = 0; i < n; i++) {
            System.out.println(
                    "P" + (i + 1) + " \t" +     // Nombre del proceso (P1, P2, ...).
                            llegada[i] + "\t" +         // Momento de llegada.
                            rafaga[i] + "\t" +          // Duración (tiempo de ejecución).
                            finalizacion[i] + "\t" +    // Instante en el que termina.
                            retorno[i] + "\t" +         // Tiempo total desde el inicio hasta su fin.
                            espera[i]                   // Tiempo total que pasó esperando.
            );
        }

        // --------------------------------------------------------------
        // CALCULAR Y MOSTRAR PROMEDIOS
        // --------------------------------------------------------------

        double promRetorno = 0, promEspera = 0;
        // Variables para acumular los tiempos totales de retorno y espera.

        for (int i = 0; i < n; i++) {
            promRetorno += retorno[i]; // Sumamos todos los tiempos de retorno.
            promEspera += espera[i];   // Sumamos todos los tiempos de espera.
        }

        // Dividimos entre el número de procesos y mostramos con dos decimales.
        System.out.printf("\nTiempo medio de retorno: %.2f", promRetorno / n);
        System.out.printf("\nTiempo medio de espera: %.2f", promEspera / n);

    }
}
