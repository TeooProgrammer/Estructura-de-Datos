package Parcial2024.Automovil;

class TablaHashAutomovil {
    private Automovil[] tabla;
    private int tam;
    public TablaHashAutomovil(int tam) {
        this.tam = tam;
        tabla = new Automovil[tam];
    }
    private int hash1(int clave){
        return clave % tam;
    }
    private int hash2(int clave){
        return (7 * clave + 1) % tam; //H1(K) = (7K + 1) % tam
    }
    public void agregar(Automovil auto) {
        int clave = Math.abs(auto.getPatente().hashCode());
        int indice = hash1(clave);  //hash1
        int rehash = hash2(clave); // hash 2

        while (tabla[indice] != null) {
            indice = (indice + rehash) % tam;
        }
        tabla[indice] = auto;
    }
    public void mostrarAutomoviles(){
        for (int i = 0; i < tam; i++){
            if (tabla[i] != null){
                System.out.println("Posicion " + i + ":\n" + tabla[i]);
            }
        }
    }
}