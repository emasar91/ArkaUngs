package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Barra {
	
	
	final int alto=8;
	final int velocidad=3;
	private int ancho=72;
	private int x=250;
	private int y=580;
	private boolean equipada=false;
	
	
	public int getAncho() {
		return ancho;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isEquipada() {
		return equipada;
	}

	
	
	Image nave=Herramientas.cargarImagen("nave.png");
	Image naveEquipada=Herramientas.cargarImagen("equipada.png");
	Image naveGrande=Herramientas.cargarImagen("nave grande.png");
	
	
	//DIBUJO DE LA BARRA SI ESTA EQUIPADA
	void dibujarEquipada(Entorno entorno){
		if(this.equipada)
				entorno.dibujarImagen(naveEquipada, this.x, this.y, 0);
	}
	
	//DIBUJO DE LA BARRA NORMAL O GRANDE
	void dibujarBarra(Entorno entorno){	
		if(this.ancho==72)
			entorno.dibujarImagen(nave, this.x, this.y, 0);
		if(this.ancho==120)
			entorno.dibujarImagen(naveGrande, this.x, this.y, 0);
		if (this.ancho!=120 && this.ancho!=72)
			entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.gray);
	}
	
	//MOVIMIENTO DE LA BARRA HACIA LA DERECHA
	void moverDerecha(){								
		this.x+=this.velocidad;
	}
	
	//MOVIMIENTO DE LA BARRA HACUA LA IZQUIERDA
	void moverIzquierda(){								
		this.x-=this.velocidad;
	}
	
	//LIMITES DE LA BARRA
	void limite(){										
		if (this.ancho==72){
			if (this.x>426)
				this.x=426;
			if (this.x<75)
				this.x=75;
		}else{
			if (this.x>426-24)
				this.x=426-24;
			if (this.x<75+24)
				this.x=75+24;
		}
	}
	
	boolean colisionCentro(Pelota pelota){				//SI LA BARRA ESTA TOCANDO CON LA PELOTA, DEVUELVE TRUE
		if (pelota.getY()<574 && this.ancho==70){						//SI LA PELOTA SUPERA LOS 576 EN Y, NO SE PUEDE GOLPEAR POR LOS LADOS
			if ( (this.y-this.alto/2)<=pelota.getY()+pelota.radio && 
			(this.x-36)-pelota.radio<pelota.getX() - pelota.radio && (this.x+36)+pelota.radio> pelota.getX()+pelota.radio)
				return true;
			return false;
		}
		else
			if (pelota.getY()<574){
				if ((this.y-this.alto/2)  <=pelota.getY()+pelota.radio && 
				(this.x-60)-pelota.radio<pelota.getX() - pelota.radio && (this.x+60)+pelota.radio> pelota.getX()+pelota.radio)
					return true;
			}
			
		return false;
	}
	
	void grandeBarra(){								//CAMBIA EL ANCHO DE LA BARRA
		if (this.ancho==120)
			this.ancho=120;
		else
			this.ancho+=1;
	}	

	void chicaBarra(){								//VUELVE LA BARRA A LA NORMALIDAD
		if (this.ancho==72)
			this.ancho=72;
		else
			this.ancho-=1;
	}	
	
	
	void disparo(){									
		this.equipada=true;
	}
	
	void normal(){
		this.equipada=false;
	}

}
