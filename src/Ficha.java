import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Ficha {
	private int [][] matriz;
	Map<String , int [][]> rotaciones;
	private int alto;
	private int ancho;
	public boolean esRotable = true;
	public Ficha(int[][] matriz) {
		this.matriz = matriz;
		this.alto = matriz.length;
		this.ancho = matriz[0].length;
		this.rotaciones  = new HashMap<String, int [][] >();
		esRotable = this.esRotable();
		this.initRotaciones();
	}
	
	public Ficha(int alto, int ancho, String[] forma) {
		this.matriz = new int[alto][ancho];
		this.alto = alto;
		this.ancho = ancho;
		this.rotaciones  = new HashMap<String, int [][] >();
		this.esRotable = this.esRotable();
		int k = 0;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				//System.out.println(i+""+j+"="+k+"- "+forma[k]);
				matriz[i][j] = Integer.parseInt(forma[k]);
				k++;
			}
			
		}
		this.initRotaciones();
	}
	private boolean esRotable(){
		boolean aux = true;
		if(alto==ancho) {
			if(ancho==1) {
				aux = false;
			}else {
				int alt=0, anc=0;
				/*while(aux==true && alt<alto) {
					
				}*/
			}
		}
		return aux;
	}
	public void initRotaciones(){
		int[][] aux90= new int[ancho][alto];
		int[][] aux180= new int[alto][ancho];
		int[][] auxMenos90= new int[ancho][alto];
		
		for (int alt = 0; alt < alto; alt++) {
			for (int anc = 0; anc < ancho; anc++) {
				aux90[anc][alto-alt-1] = matriz[alt][anc];
				aux180[alto-alt-1][ancho-anc-1] = matriz[alt][anc];
				auxMenos90[ancho-anc-1][alt] = matriz[alt][anc];
			}
		}
		rotaciones.put("90", aux90);
		rotaciones.put("180", aux180);
		rotaciones.put("-90", auxMenos90);
	}
	private void mostrarRotacion(String msg,int[][] mat) {
		String matrizView = "";
		System.out.println(msg);
		for (int i = 0; i < mat.length; i++) {
			matrizView+="\n";
			for (int j = 0; j < mat[i].length; j++) {
				matrizView+=mat[i][j]+" ";
			}
		}
		System.out.println(matrizView);
	}
	public void mostrarRotaciones(){ 
		int[][] mat = matriz;
		mostrarRotacion("FORMA ORIGINAL", mat);
		 
		mat = rotaciones.get("90");
		mostrarRotacion("ROTACION A 90", mat);
		
		mat = rotaciones.get("180");
		mostrarRotacion("ROTACION A 180", mat);
		
		mat = rotaciones.get("-90");
		mostrarRotacion("ROTACION A -90", mat);
		
	};
	
	
}
