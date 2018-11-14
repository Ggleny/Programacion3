package Api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import classes.Ficha;
import classes.Rotaciones;
import classes.Tablero;
import methods.Methods;

public class Main {

	public static void main(String[] args) {
		ArrayList<String> infoJuego;
		Tablero miTablero;
		Ficha[] fichas;
		ArrayList<ArrayList<int[][]>> arbol = new ArrayList<>();
		int minRotaciones=Integer.MAX_VALUE;
		int rotacionesActuales = 0;
		int rotacionSolucion = 0;
		infoJuego = Methods.leerArchivoCSV("/home/gleny/facu/git/Programacion3/src/tetris.txt");
		Rotaciones rotaciones = new Rotaciones();

		if(infoJuego.size()!=0) {
			miTablero = Methods.obtenerTablero(infoJuego.get(0));
			fichas = Methods.obtenerFichas(infoJuego.get(0), miTablero.obtenerCantidadFichas());
			List<Integer> fichasSeleccionadas = new ArrayList<Integer>(); 
			int[][] tableroSolucion = new int[miTablero.alto][miTablero.ancho];
			int cantidadDeTablerosCompletados = 0;

			if(Methods.esSolucionable(Methods.obtenerTamanio(miTablero), Methods.obtenerTamanio(fichas))) {
				Methods.ordenarFichas(fichas);
				/*for (Ficha is : fichas) {
						for (String rotacion : is.getRotaciones()) {
							System.out.println("Rotacion "+rotacion);
							is.mostrarRotacion(rotacion);
							System.out.println();
							System.out.println();
						}
				}*/
				/*minRotaciones = Methods.Juego2(miTablero, fichas,fichasSeleccionadas, 
						0, rotacionesActuales, minRotaciones,false,false,0, tableroSolucion,rotacionSolucion);*/
				Methods.Puzzle(miTablero, fichas, 0, rotaciones,cantidadDeTablerosCompletados);
				
			}
			if(!miTablero.huboSolucion) {
				System.out.println("El juego no tiene soluci�n.");
			}else {
				System.out.println("El juego se puede resolver con " + rotacionSolucion + " rotaciones. Se completo el tablero: "+cantidadDeTablerosCompletados);
				System.out.println("ROTACIONES rotaciones.haySolucion() "+rotaciones.haySolucion()+ "" );
				System.out.println("ROTACIONES rotaciones.getmininas() "+rotaciones.getMinimas()+ "" );
				System.out.println("ROTACIONES rotaciones.getActuales() "+rotaciones.getActuales()+ "" );

			}
			System.out.println("FINAL "+rotacionSolucion+" SOL "+miTablero.huboSolucion);
		

			mostrarArbol(arbol);
		}
	}
	
	static void mostrarArbol(ArrayList<ArrayList<int[][]>> arbol){
		for (ArrayList<int[][]> rama : arbol) {
			System.out.println("RAMA --------------------------------");
			for (int[][] mAux : rama) {
				System.out.println("FICHA ");
				for(int i=0; i<mAux.length; i++) {
		 			for(int j=0; j<(mAux[0]).length;j++)
		 				System.out.print(mAux[i][j]);
		 				System.out.println();
		 		}
				System.out.println();System.out.println();
			}
			System.out.println("-----------------------------"); System.out.println();System.out.println();
		}
	 	
	}
	
}
