package classes;

public class Rotaciones {
	private int rotActuales;
	private int minRotaciones;
	private boolean existeSol;
	
	public Rotaciones() {
		rotActuales=0;
		minRotaciones=Integer.MAX_VALUE;
		existeSol=false;
	}
	

	
	public void setActuales(int rotActuales) {
		this.rotActuales = rotActuales;
	}
	
	public int getMinimas() {
		return minRotaciones;
	}
	
	public void setMinRotaciones(int minRotaciones) {
		this.minRotaciones = minRotaciones;
	}

	public boolean haySolucion() {
		return existeSol;
	}

	public void setHaySolucion(boolean existeSol) {
		if(existeSol)
			minRotaciones = (minRotaciones<rotActuales)?minRotaciones:rotActuales;
		this.existeSol = existeSol;
	}

	public int getActuales() {
		// TODO Auto-generated method stub
		return rotActuales;
	}


}