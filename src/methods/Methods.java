package methods;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import classes.Ficha;
import classes.Tablero;

public class Methods {

	public static ArrayList<String> leerArchivoCSV(String path){
		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		ArrayList<String> datosSend=new ArrayList<>();
		System.out.println(csvFile);
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
	
	public static boolean estamparEnTablero(Tablero miTablero,Ficha ficha,String rotacion) {
		boolean agregada = false,estaCompleto = false;
		//INTENTO AGREGARLA HASTA QUE YA ESTE AGREGADA
		for(int posFila = 0; posFila<miTablero.obtenerAlto() &&!agregada; posFila++) {
			for(int posColumna = 0; posColumna<miTablero.obtenerAncho() && ! agregada; posColumna++) {
				//Aca veo si la pude agregar
				//System.out.println("Ficha en " + posFila + " " + posColumna);
				if(miTablero.agregarFicha(ficha, posFila, posColumna, rotacion)) {
					System.out.println("Muestro tablero");
					System.out.println();miTablero.mostrarTablero();
					System.out.println(); System.out.println();
					agregada = true;
					
				}
			}
		}
		return agregada;
	}
	public static String[] rotaciones = new String[]{"0","90","180","-90"}; 
	public static int Juego2(Tablero miTableroActual, Ficha[] fichas,List<Integer> fichasSeleccionadas,int nivelFicha, int rotacionesActuales, int minRotaciones,boolean solucionFinal,boolean solucionParcial,int nivelRama){
		System.out.println(); System.out.println();
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Ficha Seleccionada NIVEL; "+nivelFicha+" "+nivelRama);
		int rotacionActual = 0;
		boolean hayFichas = nivelFicha>=fichas.length?false:true;
		if(hayFichas) {
			Ficha  fichaSeleccionada = fichas[nivelFicha];
			fichasSeleccionadas.add(nivelFicha);
			boolean solucion = false;
			if(rotacionesActuales>minRotaciones){
				solucionFinal = true;
			}
			
			
			int cantidadRotaciones = !fichaSeleccionada.esRotable?4:1;
			System.out.println("************************** WHILE RAMA: "+nivelRama+" FICHA "+nivelFicha+"***************************");
			boolean fichaEstampada = false;
			while(!solucionFinal && !solucion && rotacionActual<cantidadRotaciones && rotacionesActuales<minRotaciones && hayFichas) {
				String rotacion = Methods.rotaciones[rotacionActual];
				System.out.println("Rotacion: "+rotacion);
				System.out.println("FICHA ");
				fichaSeleccionada.mostrarRotacion(rotacion); 
				System.out.println("---------------");
				fichaEstampada =  Methods.estamparEnTablero(miTableroActual,fichaSeleccionada,rotacion);
				miTableroActual.mostrarTablero();
				if(fichaEstampada ) {
					nivelFicha++;
					System.out.println("ESTAMPE NIVEL FICHA");
					rotacionesActuales = Juego2(miTableroActual,fichas,fichasSeleccionadas,nivelFicha,rotacionesActuales,minRotaciones,solucionFinal,miTableroActual.estaCompleto(),nivelRama);
					solucion = miTableroActual.estaCompleto();
				}else {
					rotacionesActuales++;
					
				}
				solucion = miTableroActual.estaCompleto();
				
				
		

				if(!solucion && rotacionActual<cantidadRotaciones) {
					//nivelFicha=0;
					
					if(fichaEstampada) {
						rotacionesActuales--;
						nivelFicha--;
						miTableroActual.removerFicha();
					}else {
						
					}
					miTableroActual.mostrarTablero();
				}
				
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				rotacionActual++;
				nivelRama++;
			
			}
			
			if(rotacionesActuales<minRotaciones){
				minRotaciones = rotacionesActuales;
			}
			
		}else {
			System.out.println("**************************NO HAY MAS FICHAS************************");
			System.out.println();
			//miTableroActual.mostrarTablero();
		}
		
		System.out.println("NIVEL FICHA: "+nivelFicha+" ROT "+rotacionesActuales+" MIN "+minRotaciones);
		if(rotacionesActuales<minRotaciones){
			minRotaciones = rotacionesActuales;
			return rotacionesActuales;
		}else {
			return minRotaciones;
		}
	}
	
	private static boolean quedanFichas(Ficha[] fichas, List<Integer> fichasSeleccionadas) {
		System.out.println("VERIFICO SI QUEDAN FICHAS POR PONER: "+fichas.length+" - "+fichasSeleccionadas.size());
		
		return  fichasSeleccionadas.size()>=fichas.length?false:true;
	}

	public static int Juego(Tablero miTablero, Ficha[] fichas, int nroFicha, int rotacionesActuales, int minRotaciones,boolean estoyCompleto){
		//Aca elijo la ficha
		System.out.println();System.out.println("--------------------INIT ---------------------------");
				System.out.println("ROTACIONES ACTUALES: "+rotacionesActuales+"; MIN ROTACIONES: "+minRotaciones);
		boolean agregada = false;
		boolean solucion = false;
		ArrayList<int[][]> fichaRama = new ArrayList<>();
		//for(int i = nroFicha; i<fichas.length && !miTablero.estaCompleto(); i++) {
		
		for(int i = nroFicha; i<fichas.length && !solucion; i++) {
			//Aca elijo la rotacion a utilizar
			Ficha ficha = fichas[i];
			System.out.println();System.out.println();System.out.println("OBTENGO FICHA  + ROTACION ");
			
			//
			for(String rotacion: ficha.obtenerRotaciones().keySet()) {
				
				
				System.out.println();System.out.println("--------------------INIT ROTACION "+rotacion+"-------------------------");
				fichas[i].mostrarRotacion(rotacion);
				//Aca recorro el tablero y chequeo si encuentro un lugar donde podria meter la ficha
				
				for(int posFila = 0; posFila<miTablero.obtenerAlto(); posFila++) {
					for(int posColumna = 0; posColumna<miTablero.obtenerAncho(); posColumna++) {
						//Aca veo si la pude agregar
						//System.out.println("Ficha en " + posFila + " " + posColumna);
						if(miTablero.agregarFicha(fichas[i], posFila, posColumna, rotacion)) {
							
							agregada = true;
							solucion = miTablero.estaCompleto();
							if(!solucion) {
								if(rotacion.equals("0")) {
									System.out.println("Muestro tablero");miTablero.mostrarTablero();
									System.out.println(); System.out.println();
									minRotaciones = Juego(miTablero,fichas,nroFicha+1,rotacionesActuales,minRotaciones,solucion);
								}else {
									System.out.println("ROTO");
									minRotaciones = Juego(miTablero,fichas,nroFicha+1,rotacionesActuales+1,minRotaciones,solucion);
									System.out.println("Muestro tablero");miTablero.mostrarTablero();
									System.out.println(); System.out.println();
								}
								fichaRama.add(ficha.getRotacion(rotacion));
							}
						}else {
							
						}
						if(agregada && !solucion)
							miTablero.removerFicha();
					}
				}
			}
			
		}
		System.out.println("-------------------- FIN  ---------------------------");
		System.out.println();
		if(solucion){
			return (rotacionesActuales<minRotaciones)?rotacionesActuales:minRotaciones;
		}else {
			return minRotaciones;
		}
		
	}
}
