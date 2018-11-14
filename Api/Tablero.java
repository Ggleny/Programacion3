package Api;

import java.util.ArrayList;
import java.util.Iterator;

public class Tablero {
	private int filas;
	private int columnas;
	private int[][] tablero;
	private ArrayList<FichaUbicada> fichas;
	
	public Tablero(int filas, int columnas) {
		super();
		this.filas = filas;
		this.columnas = columnas;
		this.tablero = new int[filas][columnas];
		for(int i = 0; i<filas;i++)
			for(int j = 0; j<columnas;j++)
				tablero[i][j]=0;
		fichas = new ArrayList<>();
	}
	
	public boolean agregarFicha(Ficha ficha, int posFila, int posColumna, String rotacion) {
		boolean ubicada = false;
		for(int i = 0; i<ficha.getRotacion(rotacion).length && !ubicada;i++)
			for(int j = 0; j<(ficha.getRotacion(rotacion))[0].length && !ubicada;j++)
				ubicada = (filas>(posFila+i) && columnas>(posColumna+j) && tablero[posFila+i][posColumna+j]==0);
		if(ubicada){
			System.out.println("Agregue una ficha en " + posFila + "-" + posColumna);
			fichas.add(new FichaUbicada(ficha,posFila,posColumna,rotacion));
			for(int i = 0; i<ficha.getRotacion(rotacion).length && !ubicada;i++)
				for(int j = 0; j<(ficha.getRotacion(rotacion))[0].length && !ubicada;j++)
					tablero[posFila+i][posColumna+j] += ficha.getRotacion(rotacion)[i][j];
		}
		return ubicada;
	};
	
	public void mostrarTablero() {
		for(int i = 0; i<filas; i++){
			for(int j = 0; j<columnas; j++)
				System.out.print(tablero[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
}
