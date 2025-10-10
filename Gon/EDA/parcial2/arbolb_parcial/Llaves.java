package arbolb_parcial;

public class Llaves
{
	int aridad, tamanio;
	Object [] llaves;
	
	/*Llaves()
	{
		this(3);
	}*/
	
	Llaves (int aridad)
	{
		this.aridad=aridad;
		tamanio=0;
		llaves=new Object[aridad*2];
	}

	Object llaveEn(int indice)
	{
		if(indice<0 || indice>=tamanio)
			return null;
		return llaves[indice];
	}
	
	int buscar(String llave){
		for(int i=0; i<tamanio; i++){
			//int c=llave.compareTo(llaves[i]);
			int c=llave.compareTo(llaves[i].toString());
			if(c<0)
				return i;
			if(c==0)
				return -1;
		}
		return tamanio;
	}
	
	int buscar(Integer llave)
	{
		for(int i=0; i<tamanio; i++)
		{
			//int c=llave.compareTo(llaves[i]);
			int c=llave.compareTo((Integer)llaves[i]);
			if(c<0)
				return i;
			if(c==0)
				return -1;
		}
		return tamanio;
	}
	
	int agregarLlave(Object llave){
		if (tamanio < aridad)
		{
			int indice;
			if (llave instanceof String){
				indice=buscar((String)llave);
			}else	
				indice=buscar((Integer)llave);
			if(indice==-1)
				return -1;
			for(int k=tamanio; k>indice; k--)
				llaves[k]=llaves[k - 1];
			llaves[indice]=llave;
			tamanio++;
			return indice;
		}
		else
			return -1;
	}
	
	boolean borrarLlave(Object llave){
		int indice=-1;
		for(int i=0; i<tamanio; i++)
		{
			int c;
			if (llave instanceof String)
				c=((String)llave).compareTo((String)llaves[i]);
			else
				c=((Integer)llave).compareTo((Integer)llaves[i]);
			if(c<0)
				return false;
			if(c==0)
			{
				indice=i;
				break;
			}
		}
		if(indice==-1)
			return false;
		for(int j=indice + 1; j<=tamanio; j++)
			llaves[j - 1]=llaves[j];
		tamanio--;
		return true;
	}
	public void mostrarLlaves(){    
	    int i=0;
	    if (llaves[i] instanceof Integer){
	        Integer n;
	        while(llaves[i]!=null)
	        {
	            n=(Integer)llaves[i];
	            System.out.print(n.intValue()+" ");
	            i++;
	        }
	    }
	    else
	        if (llaves[i] instanceof String)
	        {
	            String l;
		        while(llaves[i]!=null)
		        {
		            l=(String)llaves[i];
		            System.out.print(l+" ");
		            i++;
		        }
	        }
	}
	public int cantidadElementos(){
		int i=0;
		while(llaves[i]!=null){
			i++;
		}
		return i;
	}
}
