package ejercicio4;
import java.io.IOException;
import java.io.EOFException;
import java.io.RandomAccessFile;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie t = new Trie();
		int control = 1;
		String aux;
		char c =' ';
		RandomAccessFile raf;

		t.insert("arbol");
		t.insert("arabe");
		t.insert("armario");
		t.insert("abecedario");
		t.insert("motocicleta");
		t.insert("motor");
		t.insert("motoneta");
		t.insert("mobiliario");
		t.insert("cartera");
		t.insert("cuaderno");
		t.insert("libro");
		t.insert("bolso");
		
		while(control!=0) {
			System.out.println("0: Salir");
			System.out.println("1: Insertar"+ "");
			System.out.println("2: Eliminar");
			System.out.println("3: Cantidad de palabras");
			System.out.println("4: Listar palabras");
			System.out.println("5: Listar por prefijo");
			System.out.println("6: Cantidad de prefijos");
			control = Console.readInt();
			System.out.println();
			switch(control) {
			case 0:{break;}
			case 1:{
				aux = Console.readString("Ingrese la palabra a insertar: ");
				t.insert(aux);
				System.out.println();
				break;
			}
			case 2:{
				aux = Console.readString("Que palabra quiere borrar: ");
				t.remove(aux);
				System.out.println();
				break;
			}
			case 3:{
				System.out.println("Cantidad de palabras del arbol = "+t.cantPalabras());
				System.out.println();
				break;
			}
			case 4:{
				t.listarPalabras();
				System.out.println();
				break;
			}
			case 5:{
				aux = Console.readString("Ingrese el prefijo: ");
				t.listarPrefijo(aux);
				System.out.println();
				break;
			}
		
			case 6:{
				System.out.println("Cantidad de prefijos del trie = "+t.cantPrefijos());
				System.out.println();
				break;
			}
			default:{
				System.out.println("No existe esa opcion");
				break;
			}
			}
		}
		
		
	}
}
