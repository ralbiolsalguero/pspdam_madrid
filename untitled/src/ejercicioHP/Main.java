package ejercicioHP;

import javax.swing.plaf.TableHeaderUI;

public class Main {
    public static void main(String[] args) {

        /*
        *
        * “El Caldero Compartido de Pociones” (Control de concurrencia con synchronized)

En el aula de Pociones de Hogwarts, varios alumnos están preparando recetas simultáneamente. Todos ellos comparten un mismo caldero donde deben añadir ingredientes en un orden concreto.
Si dos alumnos añaden ingredientes a la vez, la poción explota … así que debes evitar accesos simultáneos.

Implementa un programa en Java que cumpla:

1. Clase Caldero

Crea una clase llamada Caldero con:

Un atributo nivelIngredientes (int) que empieza en 0.

Un método añadirIngrediente(String alumno) que:

Sea synchronized.

Imprima:

[ALUMNO] añade un ingrediente...


Espere entre 300 y 700 ms (Thread.sleep) simulando tiempo de añadido.

Incremente el nivel en 1.

Imprima:

Nivel actual del caldero: X ingredientes

2. Clase Alumno

Debe implementar Runnable.

Cada alumno añadirá 5 ingredientes al caldero.

En run() llama al método añadirIngrediente().

Nombres sugeridos:

“Harry”

“Hermione”

“Ron”

“Draco”

3. Clase Main

Crea un único objeto Caldero.

Crea 4 hilos (Thread) con alumnos distintos.

Lánzalos.

Haz join() para esperar a que todos terminen.

Al final imprime:

Ingredientes finales en el caldero: X
        *
        *
        *
        *
        * */

        Caldero caldero = new Caldero();
        Thread t1 = new Thread(new Alumno(caldero,"Harry"));
        Thread t2 = new Thread(new Alumno(caldero, "Hermione"));
        Thread t3 = new Thread(new Alumno(caldero, "Ron"));
        Thread t4 = new Thread(new Alumno(caldero, "Draco"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Ingredientes finales en el caldero: "+caldero.getNivelIngredientes());

    }
}
