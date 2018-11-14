package methods;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.Soundbank;

import classes.Ficha;
import classes.Rotaciones;
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
	
	
	public static boolean Puzzle(Tablero miTablero, Ficha[] fichas, int nroFicha, Rotaciones rotaciones,int tablerosCompletos){
		if(miTablero.estaCompleto()) {
			miTablero.huboSolucion = true;
			tablerosCompletos = tablerosCompletos+1;
			System.out.println();System.out.println();
			System.out.println();
			System.out.println("TABLERO COMPLETO ");
			miTablero.mostrarTablero();
			System.out.println();System.out.println();
		}
		//System.out.println("TOTAL FICHAS "+fichas.length+" NROFICHA "+nroFicha+" COMPLETO "+miTablero.estaCompleto());
		if(nroFicha>=fichas.length && miTablero.estaCompleto()){
		    rotaciones.setHaySolucion(true); //que compare y actualice minRotaciones dentro de la funcion.
		    return true;
		  }else if((nroFicha>=fichas.length && !miTablero.estaCompleto()) || (rotaciones.getActuales()>rotaciones.getMinimas())){
		    return false;
		  }else{
			System.out.println("NUMERO FICHA "+nroFicha);
			Ficha ficha = fichas[nroFicha];
			ArrayList<String> rotacionesDeFicha = ficha.getRotaciones();
		    for(String rotacion: rotacionesDeFicha){
		    	if(nroFicha==0 || nroFicha==1) 
		    		System.out.println("FICHA "+nroFicha+" rotacion : "+rotacion);
		    	ficha.mostrarRotacion(rotacion);
		    	for(int i=0; i<miTablero.getAlto(); i++){
		    		for(int j=0; j<miTablero.getAncho(); j++){
		    			if(miTablero.agregarFicha(ficha, i, j, rotacion)){
		    				System.out.println();System.out.println();
		    				System.out.println("Se agrego ficha : "+nroFicha);
		    				ficha.mostrarRotacion(rotacion);
		    				System.out.println();System.out.println();
		    				if(rotacion!="0") {
		    					rotaciones.setActuales(rotaciones.getActuales()+1);
		    				} 
		    				nroFicha++;
		    				Puzzle(miTablero, fichas, nroFicha, rotaciones,tablerosCompletos);
		    				miTablero.quitarFicha(ficha, i, j, rotacion);
		    				nroFicha--;
		    				if(rotacion!="0")
		    					rotaciones.setActuales(rotaciones.getActuales()-1);           
		    			}
		    		}
		    	}
		    }
		  }
		  return rotaciones.haySolucion();
	}
	
	public static int Juego2(Tablero miTableroActual, Ficha[] fichas,List<Integer> fichasSeleccionadas,
			int nivelFicha, int rotacionesActuales, int minRotaciones,boolean solucionFinal,
			boolean solucionParcial,int nivelRama,int[][] tableroSolucion, int rotacionSolucion){
		
		
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
			int rotacionRama = 0;
			if(solucionFinal) System.out.println("Sale por solucion Final");
			if(solucion) System.out.println("Sale por solucion 2");
			if(!(rotacionActual<cantidadRotaciones)) System.out.println("rotacionActual<cantidadRotaciones");
			if(!(rotacionesActuales<minRotaciones)) System.out.println("rotacionesActuales<minRotaciones");
			if(!(hayFichas)) System.out.println("no hay fichas");
			
			while(!solucionFinal && rotacionActual<cantidadRotaciones 
					&& rotacionesActuales<minRotaciones && hayFichas) {
				solucion = false;
				
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
					rotacionRama = Juego2(miTableroActual,fichas,fichasSeleccionadas,
							nivelFicha,rotacionRama,minRotaciones,solucionFinal,
							miTableroActual.estaCompleto(),nivelRama,
							tableroSolucion,rotacionSolucion
							);
					solucion = miTableroActual.estaCompleto();
					
				}else {
					miTableroActual.rotaciones++;
					rotacionRama++;
					
				}
				solucion = miTableroActual.estaCompleto();
				if(solucion) {
					miTableroActual.huboSolucion = true;
				}
				
		

				if(!solucion && rotacionActual<cantidadRotaciones) {
					//nivelFicha=0;
					
					if(fichaEstampada) {
						if(rotacionesActuales>0)
						rotacionesActuales--;
						if(miTableroActual.rotaciones>0)
							miTableroActual.rotaciones--;
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
				System.out.println("ROTACION RAMA "+rotacionRama+" "+solucion+" "+rotacionesActuales);
				if(solucion && rotacionRama!=0) {
					rotacionesActuales = rotacionRama;
				}
				//System.out.println("RESET "+miTableroActual.huboSolucion+" act"+rotacionActual+" menor "+cantidadRotaciones);

				if(!solucion && cantidadRotaciones<=rotacionActual) {
					System.out.println("RESET "+miTableroActual.huboSolucion+" act"+rotacionActual+" menor "+cantidadRotaciones);
					if(!miTableroActual.huboSolucion) miTableroActual.rotaciones=0;
				}else {
					System.out.println("STATUS"+miTableroActual.rotaciones);
				}
				
				if(solucionFinal) System.out.println("Sale por solucion Final");
				if(!(rotacionActual<cantidadRotaciones)) System.out.println("rotacionActual<cantidadRotaciones");
				if(!(rotacionesActuales<minRotaciones)) System.out.println("rotacionesActuales<minRotaciones");
				if(!(hayFichas)) System.out.println("no hay fichas");
				if(solucion) {
					miTableroActual.guardarTablero(tableroSolucion);
					System.out.println("SOLUCION RESETEO"+miTableroActual.rotaciones);
					if(miTableroActual.rotaciones>=0)
						rotacionSolucion = miTableroActual.rotaciones+0;
					miTableroActual.vaciarTablero();
				}
			}
			
			if(rotacionesActuales<minRotaciones && solucion){
				minRotaciones = rotacionesActuales;
			}
		}else {
			System.out.println("**************************NO HAY MAS FICHAS************************");
			System.out.println();
			//miTableroActual.mostrarTablero();
		}
		
		System.out.println("NIVEL FICHA: "+nivelFicha+" ROT "+rotacionesActuales+" MIN "+minRotaciones);
		/*if(rotacionesActuales<minRotaciones ){
			minRotaciones = rotacionesActuales;
			return rotacionesActuales;
		}else {
			return minRotaciones;
		}*/
		return rotacionesActuales;
	}
	
	private static boolean quedanFichas(Ficha[] fichas, List<Integer> fichasSeleccionadas) {
		System.out.println("VERIFICO SI QUEDAN FICHAS POR PONER: "+fichas.length+" - "+fichasSeleccionadas.size());
		
		return  fichasSeleccionadas.size()>=fichas.length?false:true;
	}

}
