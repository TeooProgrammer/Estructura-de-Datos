import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Principal {
	
	public static void main (String[] args) {
		
		Hashing objetoDePrueba = new Hashing();
		
		for (int i = 0; i < 8; i++) // Se estacionan 8 autos para probar.
			objetoDePrueba.estacionarAuto(new Automovil(ThreadLocalRandom.current().nextInt(100000, 999998 + 1)));
		
		System.out.print("Autos estacionados [N.° DE PLAZA: PATENTE DEL AUTO]:\n\n[ ");
		
		int plazaActual = 0;
		ArrayList<Automovil> estacionamiento = objetoDePrueba.getEstacionamiento();
		
		for (Automovil automovil : estacionamiento) {
			plazaActual++;
			
			if (! automovil.equals(estacionamiento.get(estacionamiento.size() - 1))) {
				if (automovil.getPatente() == 0)
					System.out.print(plazaActual + ": LIBRE | ");
				else
					System.out.print(plazaActual + ": " + automovil.getPatente() + " | ");
			} else {
				if (automovil.getPatente() == 0)
					System.out.print(plazaActual + ": LIBRE ]");
				else
					System.out.println(plazaActual + ": " + automovil.getPatente() + " ]");
			}
		}
		
	}
	
}
