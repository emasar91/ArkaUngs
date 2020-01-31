package juego;

import java.awt.Color;

import entorno.Entorno;

public class Ladrillos {
	
	private final int alto=14;
	private final int ancho=29;
	private final int x;
	private int y;
	private int golpes=1;
	private boolean poder=false;
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getGolpes() {
		return golpes;
	}

	public boolean isPoder() {
		return poder;
	}

	Ladrillos(int x, int y){
		this.x=x;
		this.y=y;	
	}
	
	void dibujarLadrillo(Entorno entorno){				//DIBUJO DE UN LADRILLO
		if (this.golpes==2)																		//SI EL LADRILLO TIENE 2 GOLPES
			entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.RED);		//SE DIBUJA DE COLOR ROJO
		if (this.golpes==1)																		//SI EL LADRILLO TIENE 1 GOLPE
			entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.cyan);	//SE DIBUJA DE COLOR CYAN
		if (this.golpes==0)																		//SI EL LADRILLO TIENE 0 GOLPE
			entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.blue);	//SE DIBUJA DE COLOR AZUL
	}
	
	void mover(){										//MOVIMIENTO DEL LADRILLO HACIA ABAJO
		this.y+=1;
	}
	
	boolean colisionAbajo(Pelota pelota){				//COLICION DE LADRILLO Y PELOTA POR ABAJO DEL LADRILLO
		if (pelota.getX() + pelota.radio <= (this.x + this.ancho/2) + pelota.radio && 
				pelota.getX() - pelota.radio >= (this.x - this.ancho/2) - pelota.radio && pelota.getY() - pelota.radio == (this.y + this.alto/2))
			return true;
		return false;
	}
	
	boolean colisionArriba(Pelota pelota){				//COLISION DE LADRILLO Y PELOTA POR ARRIBA DEL LADRILLO
		if (pelota.getX() + pelota.radio <= (this.x + this.ancho/2) + pelota.radio && 
				pelota.getX() - pelota.radio >= this.x - (this.ancho/2) - pelota.radio && pelota.getY() + pelota.radio == (this.y - this.alto/2))
			return true;
		return false;
	}
	
	boolean colisionIzq(Pelota pelota){					//COLISION DE LADRILLO Y PELOTA POR IZQUIEDA DEL LADRILLO
		if (pelota.getY() + pelota.radio <= (this.y + this.alto/2) + pelota.radio && 
				pelota.getY() - pelota.radio >= (this.y - this.alto/2) - pelota.radio && pelota.getX() + pelota.radio == (this.x - this.ancho/2))
			return true;
		return false;
	}
	boolean colisionDer(Pelota pelota){					//COLISION DE LADRILLO Y PELOTA POR DERECHA DEL LADRILLO
		if (pelota.getY() + pelota.radio <= (this.y + this.alto/2) + pelota.radio && 
				pelota.getY() - pelota.radio >= (this.y - this.alto/2) - pelota.radio && pelota.getX() - pelota.radio == (this.x + this.ancho/2))
			return true;
		return false;
	}
	
	boolean colisionBarra(Barra barra){					//COLISION DE LADRILLO Y LA BARRA
		if ((barra.getX()-barra.getAncho()/2)-this.ancho<this.x-this.ancho/2 && 
				(barra.getX()+barra.getAncho()/2)+this.ancho>this.x+this.ancho/2 && barra.getY()-barra.alto/2==this.y+this.alto)
			return true;
		return false;
		
	}
	boolean colisionDisparo(Disparo disparo){				//COLICION DE LADRILLO DISPARO
		if (this.golpes>=1){
			if (disparo.x1+disparo.getRadio()<=(this.x+this.ancho/2)+2+ disparo.getRadio() && 
					disparo.x1-disparo.getRadio() >= (this.x-this.ancho/2)-disparo.getRadio() && disparo.getDireccion()-disparo.getRadio()<=(this.y+this.alto/2))
				return true;
			return false;
		}
		return false;
	}
	
	void quitarGolpes(){
		this.golpes--;
	}
	
	void darPoder(){
		this.poder=true;
	}
	void ladrilloDuro(){
		this.golpes=2;
	}
	
}
