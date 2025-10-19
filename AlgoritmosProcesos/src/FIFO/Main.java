package FIFO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n; // Variable para guardar cuántos procesos quiere introducir el usuario.

        System.out.println("Introduzca el número de procesos:"); // Mostramos un mensaje pidiendo el número de procesos.
        n = scn.nextInt(); // Leemos un número entero (el total de procesos) y lo guardamos en n.

        // A partir de aquí, reservamos memoria (arrays) para guardar la info de cada proceso.
        int[] llegada = new int[n];       // 'llegada[i]' será el tiempo en que "llega" el proceso i (cuando aparece en el sistema).
        int[] rafaga = new int[n];        // 'rafaga[i]' es el tiempo de CPU que necesita el proceso i (duración de su ejecución).
        int[] finalizacion = new int[n];  // 'finalizacion[i]' es el instante de tiempo en el que termina el proceso i.
        int[] retorno = new int[n];       // 'retorno[i]' es el tiempo total desde t=0 hasta que termina (para cuadrar con tu gráfico).
        int[] espera = new int[n];        // 'espera[i]' es cuánto tiempo ha estado esperando el proceso i sin ejecutar.
        String[] proceso = new String[n]; // 'proceso[i]' guarda el nombre del proceso (P1, P2, ...), para no perderlos al ordenar.

        // Pedimos por teclado los datos de cada proceso, uno a uno.
        for (int i = 0; i < n; i++) { // Repetimos desde i=0 hasta i=n-1 (es decir, para cada proceso).
            proceso[i] = "P" + (i + 1); // Asignamos un nombre legible: P1, P2, P3, ... según el orden de introducción.
            System.out.println("\nProceso " + proceso[i] + " : "); // Indicamos de qué proceso estamos pidiendo los datos.

            System.out.print("Tiempo de llegada: "); // Pedimos el tiempo de llegada (cuando aparece en el sistema).
            llegada[i] = scn.nextInt(); // Guardamos el tiempo de llegada que escribe el usuario.

            System.out.print("Tiempo de ráfaga: "); // Pedimos el tiempo de ráfaga (lo que tarda en ejecutarse).
            rafaga[i] = scn.nextInt(); // Guardamos el tiempo de ráfaga que escribe el usuario.
        }

        // Antes de calcular, ordenamos los procesos por su tiempo de llegada (FIFO ejecuta según el orden de llegada).
        for (int i = 0; i < n - 1; i++) { // Bucle externo de ordenación (método sencillo tipo burbuja/selección).
            for (int j = i + 1; j < n; j++) { // Comparamos el elemento i con los siguientes (i+1, i+2, ...).
                if (llegada[i] > llegada[j]) { // Si el que está antes tiene llegada mayor que el siguiente...
                    int tmp = llegada[i];      // ...intercambiamos sus tiempos de llegada...
                    llegada[i] = llegada[j];
                    llegada[j] = tmp;

                    tmp = rafaga[i];           // ...y también sus ráfagas correspondientes (para que no se desalineen).
                    rafaga[i] = rafaga[j];
                    rafaga[j] = tmp;

                    String tmpP = proceso[i];  // ...y también intercambiamos los nombres (P1, P2, ...) para mantener la correspondencia.
                    proceso[i] = proceso[j];
                    proceso[j] = tmpP;
                }
            }
        }

        // Calculamos el instante de finalización de cada proceso siguiendo FIFO (uno detrás de otro sin huecos).
        finalizacion[0] = llegada[0] + rafaga[0]; // El primero termina cuando llega + su ráfaga.
        for (int i = 1; i < n; i++) { // Para los demás, van encadenados uno tras otro.
            finalizacion[i] = finalizacion[i - 1] + rafaga[i]; // El final del actual = final del anterior + su propia ráfaga.
        }

        // Calculamos métricas de cada proceso.
        for (int i = 0; i < n; i++) {
            retorno[i] = finalizacion[i]; // 'retorno' lo tomamos como "desde t=0 hasta que termina" (así cuadra con tu imagen).
            // Espera = tiempo total que no estuvo ejecutando.
            // Fórmula: finalización - lo que ejecuta (ráfaga) - cuando llegó
            // Intuición: del tiempo total transcurrido hasta que termina, restas lo que se ejecutó y restas lo que tardó en llegar.
            espera[i] = finalizacion[i] - rafaga[i] - llegada[i];
        }

        // Imprimimos una tabla ordenada para ver todos los datos calculados.
        System.out.println("\n---------------------------------------------------------------"); // Línea decorativa.
        System.out.println("Proceso\tLlegada\tRafaga\tFinal\tRetorno\tEspera"); // Cabecera de columnas con tabuladores.
        System.out.println("---------------------------------------------------------------"); // Línea decorativa.
        for (int i = 0; i < n; i++) { // Recorremos todos los procesos ya ordenados por llegada.
            // Imprimimos: nombre, llegada, ráfaga, final, retorno y espera de cada proceso.
            System.out.println(
                    proceso[i] + "\t" +      // Nombre del proceso (P1, P2, ...).
                            llegada[i] + "\t" +      // Tiempo de llegada.
                            rafaga[i] + "\t" +       // Tiempo de ráfaga.
                            finalizacion[i] + "\t" + // Instante en que termina.
                            retorno[i] + "\t" +      // Tiempo desde t=0 hasta que termina (como en tu gráfico).
                            espera[i]                // Tiempo total que estuvo esperando.
            );
        }

        // Calculamos los promedios (medias) de retorno y de espera.
        double promRetorno = 0, promEspera = 0; // Variables acumuladoras para sumar todos los valores.

        for (int i = 0; i < n; i++) { // Sumamos todos los retornos y todas las esperas.
            promRetorno += retorno[i]; // Acumulamos el retorno del proceso i.
            promEspera += espera[i];   // Acumulamos la espera del proceso i.
        }

        // Mostramos las medias dividiendo entre el número de procesos, con 2 decimales.
        System.out.printf("\nTiempo medio de retorno: %.2f", promRetorno / n); // Imprime la media de retorno (con formato).
        System.out.printf("\nTiempo medio de espera: %.2f", promEspera / n);   // Imprime la media de espera (con formato).
    }
}




