package Api;

public class FichaUbicada {
	private Ficha ficha;
	private int posFila;
	private int posColumna;
	private String rotacion;
	
	public FichaUbicada(Ficha ficha, int posFila, int posColumna, String rotacion){
		this.setFicha(ficha);
		this.setPosFila(posFila);
		this.setPosColumna(posColumna);
		this.setRotacion(rotacion);
	}

	
	public Ficha getFicha() {
		return ficha;
	}

	
	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public int getPosFila() {
		return posFila;
	}

	public void setPosFila(int posFila) {
		this.posFila = posFila;
	}

	public int getPosColumna() {
		return posColumna;
	}

	public void setPosColumna(int posColumna) {
		this.posColumna = posColumna;
	}

	public String getRotacion() {
		return rotacion;
	}

	public void setRotacion(String rotacion) {
		this.rotacion = rotacion;
	}
	
	

}
