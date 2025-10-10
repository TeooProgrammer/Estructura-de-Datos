package arbolb_parcial;


public class menu {
    public static void main(String[] args) {
		ArbolB arbol=new ArbolB(5);
		boolean creado=false;
		int opcion;
		do{
			System.out.println();
			System.out.println("============MENU============");
			System.out.println("1-Crear Arbol");
			System.out.println("0-Finalizar");
			opcion=Console.readInt("Ingrese una opcion: ");
			switch (opcion) {
				case 1 -> {
					System.out.println("----------Nuevo arbol---------");
					int aridad=Console.readInt("Ingrese el orden(aridad) del arbol: ");
					arbol=new ArbolB(aridad);
					System.out.println("\n======Arbol creado======");
					creado=true;
					arbol.agregarLlave(23);
					arbol.agregarLlave(20);
					arbol.agregarLlave(25);
					arbol.agregarLlave(10);
					arbol.agregarLlave(15);
					arbol.agregarLlave(27);
					arbol.agregarLlave(38);
					arbol.agregarLlave(49);
					arbol.agregarLlave(59);
					arbol.agregarLlave(67);
					arbol.agregarLlave(65);
					arbol.agregarLlave(45);
					arbol.agregarLlave(5);
					arbol.agregarLlave(3);
					arbol.agregarLlave(6);
					System.out.println();
				}
			
				default -> {
					if(opcion!=0){
						System.out.println("Error, Ingrese un numero correcto");
					}
					System.out.println();
				}
			}
		}while(!creado && opcion!=0);
		if (creado)
			menus(opcion, arbol);

    }
    public static void menus(int opcion, ArbolB arbol){
		do{
			System.out.println();
			System.out.println("============MENU============");
			System.out.println("1-Nuevo Arbol");
			System.out.println("2-Insertar");
			System.out.println("3-Borrar");
			System.out.println("4-Buscar");
			System.out.println("5-Mostrar");
			System.out.println("0-Finalizar");
			opcion=Console.readInt("Ingrese una opcion: ");
			System.out.println();
			switch(opcion){
			case 1 -> {
				System.out.println("----------Nuevo arbol---------");
				int aridad=Console.readInt("Ingrese el orden(aridad) del arbol: ");
				arbol=new ArbolB(aridad);
				System.out.println("\n======Arbol creado======");
				arbol.agregarLlave(23);
				arbol.agregarLlave(20);
				arbol.agregarLlave(25);
				arbol.agregarLlave(10);
				arbol.agregarLlave(15);
				arbol.agregarLlave(27);
				arbol.agregarLlave(38);
				arbol.agregarLlave(49);
				arbol.agregarLlave(59);
				arbol.agregarLlave(67);
				arbol.agregarLlave(65);
				arbol.agregarLlave(45);
				arbol.agregarLlave(5);
				arbol.agregarLlave(3);
				arbol.agregarLlave(6);
				System.out.println();
			}
			case 2 -> {
				System.out.println("----------Insertar Numero---------");
				arbol.agregarLlave(Console.readInt("Ingrese el numero que quiera agregar: "));
				System.out.println("Numero agregado\n");
			}
			case 3 -> {
				System.out.println("----------Borrar Numero---------");
				int num=Console.readInt("Ingrese el numero a borrar:	");
				if(arbol.borrarLlave(num)){
					System.out.println("Numero: "+num+" borrado exitosamente!");
				}else{
					System.out.println("El numero: "+num+" no se encuentra en el arbol");
				}
				System.out.println();
			}
			case 4 -> {
				System.out.println("----------Buscar Numero---------");
				int num=Console.readInt("Ingrese el numero a buscar:	");
				if(arbol.buscarLlave(num)){
					System.out.println("El numero: "+num+" se encuentra en el arbol.");
				}else{
					System.out.println("El numero: "+num+" no se encuentra en el arbol");
				}
				System.out.println();
			}
			case 5 -> {
				System.out.println("----------Mostrar Arbol---------");
				arbol.muestra();
				System.out.println();
			}
			default -> {
				if(opcion!=0){
					System.out.println("Error, Ingrese un numero correcto");
				}
				System.out.println();
			}
			}
		}while(opcion!=0);
	}
}
