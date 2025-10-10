import java.util.ArrayList;

public class Hashing {

	private ArrayList<Automovil> autosPrestados; // ¡Sí, prestados! No había presupuesto para comprarlos. :-)
	
	public Hashing () {
		this.autosPrestados = new ArrayList<Automovil>();
		
		for (int i = 0; i < 16; i++) // Se generan algunas plazas disponibles.
			this.autosPrestados.add(i, new Automovil(0));
	}
	
	public ArrayList<Automovil> getEstacionamiento () {
		return this.autosPrestados;
	}
	
	private int getHash (int n) {
		int hashGenerado = n % 18;
		
		if (hashGenerado >= this.autosPrestados.size())
			hashGenerado = this.autosPrestados.size() - 1;
		
		return hashGenerado;
	}
	
	public void estacionarAuto (Automovil automovil) {
		int lugarEstacionamiento = this.getHash(automovil.getPatente());
		
		while (this.autosPrestados.get(lugarEstacionamiento).getPatente() != 0)
			lugarEstacionamiento = this.reasignar(lugarEstacionamiento);
		
		this.autosPrestados.add(lugarEstacionamiento, automovil);
	}
	
	private int reasignar (int lugarEstacionamiento) {
		lugarEstacionamiento += 4;
		
		if (lugarEstacionamiento >= this.autosPrestados.size())
			lugarEstacionamiento = 0;
		
		return lugarEstacionamiento;
	}

}
