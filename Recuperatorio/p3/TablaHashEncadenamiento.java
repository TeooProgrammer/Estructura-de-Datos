package trabajo_3_2020;

import java.util.ArrayList;

public class TablaHashEncadenamiento {

	ArrayList[] table;
	 
	 public TablaHashEncadenamiento(int dim){
		 table= new ArrayList[dim];
		 
		for(int i=0; i < table.length; i++){
			table[i]= new ArrayList<Integer>();
		}
	 }
	 
	 public void addSequentialEncadenamiento(int valor){
		 int place = getHash(valor);
		 table[place].add(valor);
	}
	 
	 public void searchSequetialEncadenamiento(int search){
		 int place= getHash(search);
		 System.out.println("El numero "+ search + " se encontro en la posici�n " +  table[place].indexOf(search));
	 }
	 
	 private int getHash(int value) {
		 return value % 10;
	 }
	 
	 public void mostrarEncadenamiento(){
		 for(int i=0; i < table.length; i++) {
			 for(int j=0; j < table[i].size(); j++) 
				 	System.out.print(" |" +table[i].get(j));
		 }
	 }
}
