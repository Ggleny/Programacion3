package api;

import java.util.ArrayList;

import classes.Ficha;
import classes.Rotaciones;
import classes.Tablero;
import methods.Methods;

public class Main {

	public static void main(String[] args) {
		ArrayList<String> infoJuego;
		Tablero miTablero;
		Ficha[] fichas;
		Rotaciones rotaciones = new Rotaciones();

		
		infoJuego = Methods.leerArchivoCSV("/home/gleny/facu/git/Programacion3/src/tetris.txt");
		if(infoJuego.size()!=0) {
			miTablero = Methods.obtenerTablero(infoJuego.get(0));
			fichas = Methods.obtenerFichas(infoJuego.get(0), miTablero.obtenerCantidadFichas());
			if(Methods.esSolucionable(Methods.obtenerTamanio(miTablero), Methods.obtenerTamanio(fichas))) {
				Methods.ordenarFichas(fichas);
				Methods.Juego(miTablero, fichas, 0, rotaciones);
			}
			if(!rotaciones.haySolucion()) {
				System.out.println("El juego no tiene soluci�n.");
			}else {
				System.out.println("El juego se puede resolver con " + rotaciones.getMinimas() + " rotaciones.");
				miTablero.mostrarTablero();
			}
		}
	}
	
}


