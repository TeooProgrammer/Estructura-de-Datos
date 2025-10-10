package arbolb_parcial;

public class Main {

	public static void main(String[] args) {
		ArbolB arbol=new ArbolB(2);
		menu(arbol);
	}
	public static void menu(ArbolB arbol){
		boolean creado=false;
		int opcion;
		do{
			System.out.println();
			System.out.println("1-	Nuevo Arbol");
			System.out.println("2-	Insertar");
			System.out.println("3-	Borrar");
			System.out.println("4-	Buscar");
			System.out.println("5-	Mostrar");
			System.out.println("6-	Cantidad de Nodos");
			System.out.println("7-	Cantidad de LLaves");
			System.out.println("8-	Llaves Hojas");
			System.out.println("9-	LLaves No Hojas ");
			System.out.println("10-	Mostrar desde cierto rango ");
			System.out.println("0-	Finalizar");
			opcion=Console.readInt("Ingrese una Opcion:	");
			System.out.println();
			switch(opcion){
			case 1 -> {
				System.out.println("----------Crear Nuevo Arbol---------");
				int aridad=Console.readInt("Ingrese el orden de grafo:	");
				arbol=new ArbolB(aridad);
				System.out.println("Arbol Creado Exitosamente!");
				creado=true;
				arbol.agregarLlave(190);
				arbol.agregarLlave(57);
				arbol.agregarLlave(89);
				arbol.agregarLlave(90);
				arbol.agregarLlave(121);
				arbol.agregarLlave(170);
				arbol.agregarLlave(35);
				arbol.agregarLlave(48);
				
				arbol.agregarLlave(91);
				arbol.agregarLlave(22);
				arbol.agregarLlave(126);
				arbol.agregarLlave(132);
				arbol.agregarLlave(80);
				
				System.out.println();
			}
			case 2 -> {
				if(!creado){
					System.out.println("Error, Arbol no creado");
				}else{
					System.out.println("----------Insertar Numero---------");
					arbol.agregarLlave(Console.readInt("Ingrese el numero que quiera agregar:	"));
				}
				System.out.println();
			}
			case 3 -> {
				if(!creado){
					System.out.println("Error, Arbol no creado");
				}else{
					System.out.println("----------Borrar Numero---------");
					int num=Console.readInt("Ingrese el numero a borrar:	");
					if(arbol.borrarLlave(num)){
						System.out.println("N�mero: "+num+" borrado exitosamente!");
					}else{
						System.out.println("El n�mero: "+num+" no se encuentra en el arbol");
					}
					System.out.println();
				}
			}
			case 4 -> {
				if(!creado){
					System.out.println("Error, Arbol no creado");
				}else{
					System.out.println("----------Buscar Numero---------");
					int num=Console.readInt("Ingrese el numero a buscar:	");
					if(arbol.buscarLlave(num)){
						System.out.println("El numero: "+num+" se encuentra en el arbol.");
					}else{
						System.out.println("El numero: "+num+" no se encuentra en el arbol");
					}
					System.out.println();
				}
			}
			case 5 -> {
				if(!creado){
					System.out.println("Error, Arbol no creado");
				}else{
					System.out.println("----------Mostrar Arbol---------");
					arbol.muestra();
					System.out.println();
				}
			}
			case 6 -> {
				System.out.println("Cantidad de Nodos");
				System.out.println (arbol.cantidadNodos());
				
			}
			
			case 7 -> {
				System.out.println("Cantidad de LLaves :");
				System.out.println(arbol.cantidadLlaves());
			}
			case 8 -> {
				System.out.println ("Las llaves hojas son :");
			    arbol.muestraHojas();
				
				
			}
			case 9 -> {
				
				System.out.println("La cantidad de No hojas son :");
				arbol.muestraNoHojas();
			}
			
			case 10 -> {
				System.out.println("Inserte los rango de valores :");
				int bajo = Console.readInt("Desde :");
				int alto= Console.readInt("Hasta:");
				arbol.muestraValor(bajo, alto);
				
				
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
