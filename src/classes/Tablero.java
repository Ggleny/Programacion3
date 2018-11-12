package classes;

import java.util.ArrayList;

public class Tablero {

	private int alto;
	private int ancho;
	private int cantidadFichas;
	private int volumen;
	private int[][] tablero;
	private ArrayList<FichaUbicada> fichas;
	int fichasPuestas = 0;
	
	public Tablero(int filas, int columnas, int cantidadFichas) {
		super();
		this.alto = filas;
		this.ancho = columnas;
		this.cantidadFichas = cantidadFichas;
		this.tablero = new int[filas][columnas];
		this.fichas = new ArrayList<>();
		this.volumen = 0;
		for(int i = 0; i<filas;i++)
			for(int j = 0; j<columnas;j++)
				tablero[i][j]=0;
	}

	public int obtenerTamanio() {
		return this.alto*this.ancho;
	}

	public boolean estaCompleto() {
		return (volumen == alto*ancho);
	}
	
	public int obtenerAlto() {
		return alto;
	}

	public int obtenerAncho() {
		return ancho;
	}

	public int obtenerCantidadFichas() {
		return cantidadFichas;
	}

	public boolean agregarFicha(Ficha ficha, int posFila, int posColumna, String rotacion) {
		//System.out.println();System.out.println();System.out.println();
		//System.out.println("INTENTO AGREGAR FICHA");
		//ficha.mostrarRotacion(rotacion);
		int[][] mAux = ficha.getRotacion(rotacion);
		boolean ubicada = true;
		if(posFila+mAux.length<=alto && posColumna+(mAux[0]).length<=ancho) {
			for(int i = 0; i<mAux.length;i++)
				for(int j = 0; j<(mAux[0]).length;j++) {
					ubicada &= !(tablero[posFila+i][posColumna+j]==1 && mAux[i][j]==1 );
				}
		}else {
			ubicada=false;
		}
		if(ubicada){
			System.out.println("Agregue una ficha en " + posFila + "-" + posColumna);
			this.fichas.add(new FichaUbicada(ficha,posFila,posColumna,rotacion));
			for(int i = 0; i<mAux.length;i++)
				for(int j = 0; j<(mAux[0]).length;j++)
					tablero[posFila+i][posColumna+j] += mAux[i][j];
			this.volumen += ficha.obtenerTamanio();
		}else {
			//System.out.println("NO PUDE AGREGARLAR");
		}
			
		return ubicada;
	};

	public void removerFicha() {
		if(!fichas.isEmpty()) {
			FichaUbicada fAux = fichas.get(fichas.size()-1);
			int posFila = fAux.obtenerPosFila();
			int posColumna = fAux.obtenerPosColumna();
			int[][] mAux = fAux.obtenerFicha().getRotacion(fAux.obtenerRotacion());
			for(int i = 0; i<mAux.length;i++)
				for(int j = 0; j<(mAux[0]).length;j++)
					tablero[posFila+i][posColumna+j] -= mAux[i][j];
			this.volumen -= fAux.obtenerFicha().obtenerTamanio();
			fichas.remove(fichas.size()-1);
			System.out.println("Saque una ficha en " + posFila + "-" + posColumna);
		}
	}
	
	public void vaciarTablero() {
		if(!fichas.isEmpty()) {
			for(int i = 0; i<alto;i++)
				for(int j = 0; j<ancho;j++)
					tablero[i][j]=0;
		}
	}
	
	public void mostrarTablero() {
		for(int i = 0; i<this.alto; i++) {
			for(int j=0; j<this.ancho;j++)
				System.out.print(tablero[i][j]);
			System.out.println();
		}
	}
}
