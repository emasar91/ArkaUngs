package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Disparo {
	private int direccion;	//VARIABLE AUXILIAR PARA GUARDAR LA COORDENADA 
	final int x1;			//DONDE FUE LANZADO EL DISPARO	
	private int radio=10;
	Image disparo=Herramientas.cargarImagen("disparo.png");
	
	public int getRadio() {
		return radio;
	}
	
	public int getDireccion() {
		return direccion;
	}
	
	Disparo(int x, int y){
		this.direccion=y;
		this.x1=x;
	}
	
	void mover(){						  	//MUEVE EL DISPARO HACIA ARRIBA
		this.direccion-=5;
	}
	
	void dibujarDisparo(Entorno entorno){ 	//DIBUJA EL DISPARO
		entorno.dibujarImagen(disparo, this.x1, this.direccion, 0);	
	}
	
}
