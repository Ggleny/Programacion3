package methods;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import classes.Ficha;
import classes.Rotaciones;
import classes.Tablero;

public class Methods {

	public static boolean debug = true;
	public static ArrayList<String> leerArchivoCSV(String path){
		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		ArrayList<String> datosSend=new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
		    while ((line = br.readLine()) != null) {
		        datosSend.add(line);
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (br != null) {
		        try {
		            br.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	    return datosSend;
	}

	public static Tablero obtenerTablero(String infoTablero) {
		String[] dataAux = (infoTablero.split("-\\("))[0].split(",");
		//String[] dataTablero = dataAux[0].split(",");
		Tablero tablero = new Tablero(Integer.valueOf(dataAux[0]), Integer.valueOf(dataAux[1]), Integer.valueOf(dataAux[2]));
		return tablero;
	}

	public static Ficha[] obtenerFichas(String infoFichas, int cantidadFichas) {
		String[] dataAux = (infoFichas.split("-\\("))[1].replaceAll("\\(|\\)", "").split(" ");
		Ficha[] fichas = new Ficha[cantidadFichas];
		for(int i = 0; i < cantidadFichas; i++) {
			String auxAlto = ((dataAux[i].split("-"))[0].split(","))[0];
			String auxAncho = ((dataAux[i].split("-"))[0].split(","))[1];
			fichas[i] = new Ficha(Integer.valueOf(auxAlto),Integer.valueOf(auxAncho),(dataAux[i].split("-"))[1].split(",")); 
		}
		return fichas;
	}

	public static int obtenerTamanio(Tablero tablero) {
		return tablero.obtenerTamanio();
	}
	
	public static int obtenerTamanio(Ficha[] fichas) {
		int aux = 0;
		for(Ficha f:fichas)
			aux += f.obtenerTamanio();
		return aux;
	}
	
	public static boolean esSolucionable(int tamanioTablero, int tamanioFichas) {
		return (tamanioTablero==tamanioFichas);
	}
	
	public static void ordenarFichas (Ficha[] fichas) {
		for(int i=0; i< fichas.length;i++)
			for(int j=i+1; j<fichas.length;j++) {
				if(fichas[i].obtenerTamanio()<fichas[j].obtenerTamanio()) {
					Ficha aux = fichas[i];
					fichas[i] = fichas[j];
					fichas[j] = aux;
				}
			}
	}
	
	public static boolean Juego(Tablero miTablero, Ficha[] fichas, int nroFicha, Rotaciones rotaciones){
		boolean haySolucion = false;
		if(miTablero.estaCompleto() && debug) {
			System.out.println("Se completo tablero ");
			miTablero.mostrarTablero();
		}
		if (nroFicha==fichas.length && miTablero.estaCompleto()) {
			//Si llegue al final y hay solucion
			if(rotaciones.getActuales()<rotaciones.getMinimas())
				miTablero.setSolucion();
			rotaciones.setHaySolucion(true);
			return true;
		}else if((nroFicha==fichas.length && !miTablero.estaCompleto()) || (rotaciones.getActuales()>rotaciones.getMinimas())) {
			//Si llego al final y no hay solucion, o si supero el numero de rotaciones minimo
			return false;
		}else {
			//Aca elijo la rotacion a utilizar
			for(String rotacion: fichas[nroFicha].obtenerRotaciones().keySet()) {
				//Aca recorro el tablero y chequeo si encuentro un lugar donde podria meter la ficha
				for(int posFila = 0; posFila<miTablero.obtenerAlto();posFila++) {
					for(int posColumna = 0; posColumna<miTablero.obtenerAncho();posColumna++) {
						//Aca veo si la pude agregar
						if(miTablero.agregarFicha(fichas[nroFicha], posFila, posColumna, rotacion)) {
							if(!rotacion.equals("0"))
								rotaciones.setActuales(rotaciones.getActuales()+1);
							haySolucion = Juego(miTablero,fichas,nroFicha+1,rotaciones);
							miTablero.removerFicha();
							if(!rotacion.equals("0"))
								rotaciones.setActuales(rotaciones.getActuales()-1);
						}
					}
				}
			}			
		}
		return haySolucion;
	}

}
