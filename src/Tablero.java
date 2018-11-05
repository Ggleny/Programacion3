import java.util.ArrayList;
import java.util.Iterator;

public class Tablero {
	private int filas;
	private int columnas;
	private int[][] tablero;
	
	private ArrayList<Ficha> fichas;
	public Tablero(int filas, int columnas) {
		super();
		this.filas = filas;
		this.columnas = columnas;
		this.tablero = new int[filas][columnas];
		fichas = new ArrayList<>();
	}
	public void agregarFicha(Ficha ficha) {
		fichas.add(ficha);
	};
	public void mostrarFichas() {
		for (Iterator iterator = fichas.iterator(); iterator.hasNext();) {
			Ficha ficha = (Ficha) iterator.next();
			System.out.println("FICHA--------------");
			ficha.mostrarRotaciones();
			System.out.println(); System.out.println();
		}
	}
}
