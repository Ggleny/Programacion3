import java.util.List;
import java.util.ArrayList;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> data = Utilidades.LeerArchivoCSV("C:\\Users\\Marce\\Downloads\\progra2\\Programacion3\\src\\tetris.txt");
		if(data.size()!=0) {
			String aux = data.get(0);
			String[] dataAux = aux.split("-\\(");
			String[] dataTablero = dataAux[0].split(",");
			Tablero tablero = new Tablero(Integer.valueOf(dataTablero[0]), Integer.valueOf(dataTablero[1]));
			
			String[] dataFichas = dataAux[1].replaceAll("\\(|\\)", "").split(" ");
			String[] auxFichas,tamFicha;
			for (int i = 0; i < dataFichas.length; i++) {
				//System.out.println(dataFichas[i]);
				auxFichas = dataFichas[i].split("-");
				tamFicha = auxFichas[0].split(",");
				tablero.agregarFicha(new Ficha(Integer.parseInt(tamFicha[0]),Integer.parseInt(tamFicha[1]),auxFichas[1].split(",")));
			}
			tablero.mostrarFichas();
			
		}
		
		
		
		//System.out.println(data[0]);
		//tablero = new Tablero(data[0],data[1]);
		/*int[][] aux = new int[2][3];
		aux[0][0] = 0;
		aux[0][1] = 1;
		aux[0][2] = 0;
		aux[1][0] = 1;
		aux[1][1] = 1;
		aux[1][2] = 1;
		System.out.println("Ficha T");
		Ficha fichaT = new Ficha(aux);
		fichaT.mostrarRotaciones();
		
		aux = new int[2][3];
		aux[0][0] = 0;
		aux[0][1] = 1;
		aux[0][2] = 1;
		aux[1][0] = 1;
		aux[1][1] = 1;
		aux[1][2] = 0;
		System.out.println("Ficha Z");
		Ficha fichaZ = new Ficha(aux);
		fichaZ.mostrarRotaciones();
		
		aux = new int[2][3];
		aux[0][0] = 1;
		aux[0][1] = 1;
		aux[0][2] = 1;
		aux[1][0] = 0;
		aux[1][1] = 0;
		aux[1][2] = 1;
		System.out.println("Ficha L");
		Ficha fichaL = new Ficha(aux);
		fichaL.mostrarRotaciones();*/
	}

}
