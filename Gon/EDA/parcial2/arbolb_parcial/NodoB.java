package arbolb_parcial;



/*
 * Clase NodoB (Nodo del Arbol B)
 * 
 */
public class NodoB
{
	Llaves dato;
	NodoB [] hijos;
	int aridad; // maxima cantidad de elementos
	int tamanio; //cantidad de hijos
	NodoB (int aridad)
	{
		this.aridad=aridad;
		tamanio=0;
		hijos=new NodoB [aridad];
	}
	
	NodoB (int aridad, Llaves dato)
	{
		this.aridad=aridad;
		tamanio=0;
		hijos=new NodoB [aridad];
		this.dato=dato;
	}
	
	void cambiarDato(Llaves dato)
	{
		this.dato=dato;
	}
	
	Llaves obtenerDato()
	{
		return dato;
	}
	
	NodoB obtenerHijo (int indice)
	{
		if(indice>=tamanio || indice<0)
			return null;
		return (NodoB)hijos [indice];
	}
	
	NodoB agregarHijo (NodoB dato)
	{
		if(tamanio==aridad)
			return null;
		else
		{
			Llaves temp=dato.obtenerDato();
			NodoB nodoTemp=new NodoB(aridad,temp);
			
			//hijos[tamanio++]=nodoTemp;
			hijos[tamanio++]=dato;
			return nodoTemp;
		}
	}
	
	NodoB agregarHijo(NodoB dato, int indice)
	{
		Llaves temp=dato.obtenerDato();
		if(indice<0 || indice>=tamanio)
			return agregarHijo(dato);
		for(int i=tamanio; i>indice; i--)
			hijos[i]=hijos[i - 1];
		NodoB nodoTemp=new NodoB (aridad,temp);
		//hijos[indice]=nodoTemp;
		hijos[indice]=dato;
		tamanio++;
		return nodoTemp;
	}
	
	NodoB borrarHijo (int indice)
	{
		if(indice<0 || indice>=tamanio)
			return null;
		NodoB nodoTemp=(NodoB)hijos[indice];
		for(int i=indice+1; i<=tamanio-1; i++)
			hijos[i-1]=hijos[i];
		tamanio--;
		return nodoTemp;
	}
	
	void borrarHijo (NodoB dato)
	{
		if(tamanio>0)
		{
			int j;
			for(int i=0; i<tamanio; i++)
			if((NodoB)hijos[i]==dato)
			borrarHijo(i);
		}
	}
	
	int grado()
	{
		return tamanio;
	}
	
	public boolean esHoja()
	{
	 return (hijos[0]==null);   
	}
}
