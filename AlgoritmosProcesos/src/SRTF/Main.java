package SRTF;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el número de procesos: ");
        int n = sc.nextInt();

        int[] llegada = new int[n];
        int[] rafaga = new int[n];
        int[] restante = new int[n];
        int[] finalizacion = new int[n];
        int[] retorno = new int[n];
        int[] espera = new int[n];
        boolean[] completado = new boolean[n];

        // Entrada de datos
        for (int i = 0; i < n; i++) {
            System.out.println("\nProceso " + (i + 1) + ":");
            System.out.print("Tiempo de llegada: ");
            llegada[i] = sc.nextInt();
            System.out.print("Tiempo de ráfaga: ");
            rafaga[i] = sc.nextInt();
            restante[i] = rafaga[i];
        }

        int tiempo = 0;
        int completados = 0;
        int procesoActual = -1;

        StringBuilder gantt = new StringBuilder("\nDiagrama de Gantt:\n");
        gantt.append("0 "); // primera marca de tiempo

        while (completados < n) {
            int idx = -1;
            int menorRestante = Integer.MAX_VALUE;

            // Seleccionar el proceso con menor ráfaga restante entre los que han llegado
            for (int i = 0; i < n; i++) {
                if (!completado[i] && llegada[i] <= tiempo && restante[i] < menorRestante) {
                    menorRestante = restante[i];
                    idx = i;
                }
            }

            if (idx == -1) {
                tiempo++;
                continue;
            }

            // Solo imprimir cuando cambia el proceso
            if (procesoActual != idx) {
                if (procesoActual != -1) gantt.append("| ").append(tiempo).append(" "); // cierre del anterior
                gantt.append("| P").append(idx + 1).append(" ");
                procesoActual = idx;
            }

            // Ejecutar 1 unidad de tiempo
            restante[idx]--;
            tiempo++;

            // Si termina
            if (restante[idx] == 0) {
                completado[idx] = true;
                completados++;
                finalizacion[idx] = tiempo;
                retorno[idx] = finalizacion[idx];
                espera[idx] = retorno[idx] - llegada[idx] - rafaga[idx];
            }
        }

        gantt.append("| ").append(tiempo).append(" |"); // cierre final
        System.out.println(gantt);

        System.out.println("\n------------------------------------------------------------");
        System.out.println("Proceso\tLlegada\tRáfaga\tFinal\tRetorno\tEspera");
        System.out.println("------------------------------------------------------------");

        double totalEspera = 0, totalRetorno = 0;

        for (int i = 0; i < n; i++) {
            System.out.printf("P%d\t%d\t%d\t%d\t%d\t%d\n",
                    (i + 1), llegada[i], rafaga[i], finalizacion[i], retorno[i], espera[i]);
            totalEspera += espera[i];
            totalRetorno += retorno[i];
        }

        System.out.printf("\nTiempo medio de espera: %.2f", totalEspera / n);
        System.out.printf("\nTiempo medio de retorno: %.2f\n", totalRetorno / n);
    }
}
