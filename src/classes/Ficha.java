package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ficha {
	Map<String , int [][]> rotaciones;
	private int alto;
	private int ancho;
	private int tamanio;
	public boolean esRotable;
	private ArrayList<String> rotacionesDisponibles;
	
	
	public Ficha(int alto, int ancho, String[] forma) {
		int[][] aux = new int[alto][ancho];
		this.alto = alto;
		this.ancho = ancho;
		this.rotaciones  = new HashMap<String, int [][] >();
		this.rotacionesDisponibles = new ArrayList<>();
		int k = 0;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				aux[i][j] = Integer.parseInt(forma[k]);
				k++;
			}
		}
		this.initRotaciones(aux);
		this.calcularTamanio();
		this.esRotable = this.esRotable();
	}
	
	private void calcularTamanio(){
	// O(n) = n^2
		for(int i = 0; i < alto; i++)
			for(int j = 0; j < ancho; j++)
				this.tamanio+=this.getRotacion("0")[i][j];
	}
	
	public boolean esRotable(){
	// O(n) = cte
		return (alto==ancho && tamanio==(alto*ancho));
	}
	
	public void initRotaciones(int[][] matriz){
	// O(n) = n^2
		int[][] aux0 = new int[alto][ancho];
		int[][] aux90= new int[ancho][alto];
		int[][] aux180= new int[alto][ancho];
		int[][] auxMenos90= new int[ancho][alto];
		/*
		for (int alt = 0; alt < alto; alt++) {
			for (int anc = 0; anc < ancho; anc++) {
				aux0[alt][anc]=matriz[alt][anc];
				aux90[anc][alto-alt-1] = matriz[alt][anc];
				aux180[alto-alt-1][ancho-anc-1] = matriz[alt][anc];
				auxMenos90[ancho-anc-1][alt] = matriz[alt][anc];
			}
		}
		rotaciones.put("0", aux0);
		rotaciones.put("90", aux90);
		rotaciones.put("180", aux180);
		rotaciones.put("-90", auxMenos90);*/
		for (int alt = 0; alt < alto; alt++) {
			for (int anc = 0; anc < ancho; anc++) {
				aux0[alt][anc]=matriz[alt][anc];
				rotaciones.put("0", aux0);
				rotacionesDisponibles.add("0");
				if(!this.esRotable() && this.alto!=this.ancho) {
					aux90[anc][alto-alt-1] = matriz[alt][anc];					
					rotaciones.put("90", aux90);
					rotacionesDisponibles.add("90");
				}else if(this.esRotable()) {
					aux90[anc][alto-alt-1] = matriz[alt][anc];					
					aux180[alto-alt-1][ancho-anc-1] = matriz[alt][anc];
					auxMenos90[ancho-anc-1][alt] = matriz[alt][anc];
					rotacionesDisponibles.add("90");
					rotacionesDisponibles.add("180");
					rotacionesDisponibles.add("-90");
					rotaciones.put("90", aux90);
					rotaciones.put("180", aux180);
					rotaciones.put("-90", auxMenos90);
				}
			}
		}
	}
	public  ArrayList<String> getRotaciones() {
		return rotacionesDisponibles;
	}
	public int getAlto(){
		return alto;
	}

	public int getAncho(){
		return ancho;
	}
	
	public int[][] getRotacion(String rotacion){
		return this.rotaciones.get(rotacion);
	}
	
	public Map<String, int[][]> obtenerRotaciones() {
		return this.rotaciones;
	}
	
 	public int obtenerTamanio() {
		return this.tamanio;
	}
	
 	public void mostrarRotacion(String rotacion) {
 		int[][] mAux = rotaciones.get(rotacion);
 		for(int i=0; i<mAux.length; i++) {
 			for(int j=0; j<(mAux[0]).length;j++)
 				System.out.print(mAux[i][j]);
 			System.out.println();
 		}
 	}
 	
}
