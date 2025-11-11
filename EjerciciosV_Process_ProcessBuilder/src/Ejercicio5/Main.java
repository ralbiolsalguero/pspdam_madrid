package Ejercicio5;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Obtenemos el mapa de variables de entorno
            ProcessBuilder pb = new ProcessBuilder();
            Map<String, String> entorno = pb.environment();

            System.out.println("Variables de entorno del sistema:\n");

            // Recorremos el mapa e imprimimos cada par clave=valor
            for (String clave : entorno.keySet()) {
                System.out.println(clave + " = " + entorno.get(clave));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

