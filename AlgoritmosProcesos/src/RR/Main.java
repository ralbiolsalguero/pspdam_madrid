package RR;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el número de procesos: ");
        int n = sc.nextInt();

        int[] llegada = new int[n];
        int[] rafaga  = new int[n];
        int[] restante = new int[n];
        int[] finalizacion = new int[n];
        int[] retorno = new int[n];
        int[] espera  = new int[n];
        boolean[] terminado = new boolean[n];

        System.out.print("Introduce el valor del quantum: ");
        int Q = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nProceso " + (i + 1) + ":");
            System.out.print("Tiempo de llegada: ");
            llegada[i] = sc.nextInt();
            System.out.print("Tiempo de ráfaga: ");
            rafaga[i] = sc.nextInt();
            restante[i] = rafaga[i];
        }

        // --- RR "docente" con dos colas: ronda actual y siguiente ---
        Deque<Integer> actual   = new ArrayDeque<>();
        Deque<Integer> siguiente= new ArrayDeque<>();

        int tiempo = 0;
        int completados = 0;

        // Encolar lo disponible al inicio (t=0) en la ronda actual
        for (int i = 0; i < n; i++) {
            if (llegada[i] <= tiempo) actual.addLast(i);
        }

        StringBuilder gantt = new StringBuilder("\nDiagrama de Gantt:\n");
        gantt.append("0 ");

        while (completados < n) {

            // Si la ronda actual se quedó vacía, pasamos a la siguiente ronda
            if (actual.isEmpty()) {
                // si no hay nada en siguiente pero aún no ha llegado nadie, adelanta el reloj
                if (siguiente.isEmpty()) {
                    int prox = Integer.MAX_VALUE;
                    for (int i = 0; i < n; i++) if (!terminado[i] && llegada[i] > tiempo) prox = Math.min(prox, llegada[i]);
                    if (prox != Integer.MAX_VALUE) tiempo = prox;
                    // encolar todo lo que ya haya llegado a partir de ese tiempo
                    for (int i = 0; i < n; i++) if (!terminado[i] && llegada[i] <= tiempo) actual.addLast(i);
                } else {
                    // empezamos nueva ronda
                    actual.addAll(siguiente);
                    siguiente.clear();
                }
            }

            if (!actual.isEmpty()) {
                int p = actual.removeFirst();
                gantt.append("| P").append(p + 1).append(" ");

                int ejecutado = 0;
                // Ejecutamos hasta Q o hasta terminar, avanzando el tiempo tick a tick
                while (ejecutado < Q && restante[p] > 0) {
                    restante[p]--;
                    tiempo++;
                    ejecutado++;

                    // Cualquier proceso que llegue durante ESTA ronda va a "siguiente"
                    for (int i = 0; i < n; i++) {
                        if (!terminado[i] && llegada[i] == tiempo) {
                            // evitar duplicados: solo añadir si no está en actual ni en siguiente
                            if (!actual.contains(i) && !siguiente.contains(i)) {
                                siguiente.addLast(i);
                            }
                        }
                    }
                }

                gantt.append(tiempo).append(" ");

                if (restante[p] == 0) {
                    terminado[p] = true;
                    completados++;
                    finalizacion[p] = tiempo;
                    retorno[p] = finalizacion[p] - llegada[p];
                    espera[p]  = retorno[p] - rafaga[p];
                } else {
                    // No terminó: este proceso pasa a la SIGUIENTE ronda
                    siguiente.addLast(p);
                }
            }
        }

        gantt.append("|");
        System.out.println(gantt);

        // ---- Resultados ----
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Proceso\tLlegada\tRáfaga\tFinal\tRetorno\tEspera");
        System.out.println("------------------------------------------------------------");

        double sumR = 0, sumE = 0;
        for (int i = 0; i < n; i++) {
            System.out.printf("P%d\t%d\t%d\t%d\t%d\t%d%n",
                    (i + 1), llegada[i], rafaga[i], finalizacion[i], retorno[i], espera[i]);
            sumR += retorno[i];
            sumE += espera[i];
        }

        System.out.printf("%nTiempo medio de espera: %.2f", sumE / n);
        System.out.printf("%nTiempo medio de retorno: %.2f%n", sumR / n);
    }
}
