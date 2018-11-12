package classes;

public class FichaUbicada {

	private Ficha ficha;
	private int posFila;
	private int posColumna;
	private String rotacion;
	
	public FichaUbicada(Ficha ficha, int posFila, int posColumna, String rotacion){
		this.ficha = ficha;
		this.posFila = posFila;
		this.posColumna=posColumna;
		this.rotacion = rotacion;
	}
	
	public Ficha obtenerFicha() {
		return this.ficha;
	}

	public String obtenerRotacion() {
		return this.rotacion;
	}

	public int obtenerPosFila() {
		return this.posFila;
	}
	
	public int obtenerPosColumna() {
		return this.posColumna;
	}

}
