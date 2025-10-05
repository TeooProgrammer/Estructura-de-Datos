package tp3.ej3;

public class FuncionesHash {

    // a) restas sucesivas
    public static int restasSucesivas(int clave, int tamaño) {
        int suma = 0;
        while (clave > 0) {
            suma -= clave % 10;
            clave /= 10;
        }
        return Math.abs(suma) % tamaño;
    }

    // b) metodo de división o módulo
    public static int modulo(int clave, int tamaño) {
        return clave % tamaño;
    }

    // c) metodo del medio cuadrado
    public static int medioCuadrado(int clave, int tamaño) {
        long cuadrado = (long) clave * clave;
        String s = String.valueOf(cuadrado);
        int medio = s.length() / 2;
        String extraido = s.substring(Math.max(0, medio - 2), Math.min(s.length(), medio + 2));
        int valor = Integer.parseInt(extraido);
        return valor % tamaño;
    }

    // d) funcion truncamiento
    public static int truncamiento(int clave, int tamaño) {
        return (clave % 1000) % tamaño;
    }

    // e) metodo superposicion por desplazamiento
    public static int desplazamiento(int clave, int tamaño) {
        int suma = 0;
        while (clave > 0) {
            suma += (clave & 0xFF);
            clave >>= 8;
        }
        return suma % tamaño;
    }

    // f) metodo superposición por plegamiento
    public static int plegamiento(int clave, int tamaño) {
        String s = String.valueOf(clave);
        int suma = 0;
        for (int i = 0; i < s.length(); i += 2) {
            String parte = s.substring(i, Math.min(i + 2, s.length()));
            suma += Integer.parseInt(parte);
        }
        return suma % tamaño;
    }
}
