package Parcial2024.Automovil;

class Main {
    public static void main(String[] args) {
        TablaHashAutomovil tabla = new TablaHashAutomovil(10);
        Automovil auto1 = new Automovil("ABC123", "Toyota");
        Automovil auto2 = new Automovil("DEF456", "Chevrolet");
        Automovil auto3 = new Automovil("XYZ789", "Ford");
        Automovil auto4 = new Automovil("JKL999", "Fiat");
        tabla.agregar(auto1);
        tabla.agregar(auto2);
        tabla.agregar(auto3);
        tabla.agregar(auto4);
        tabla.mostrarAutomoviles();
    }
}