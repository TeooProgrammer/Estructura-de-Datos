package Parcial2024.Automovil;

class Automovil {
    private String patente;
    private String marca;
    public Automovil(String patente, String marca) {
        this.patente = patente;
        this.marca = marca;
    }
    public String getPatente() {
        return this.patente;
    }
    public void setPatente(String patente) {
        this.patente = patente;
    }
    public String getMarca() {
        return this.marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    @Override
    public String toString() {
        return "Patente: " + patente + ", Marca: " + marca;
    }
}


