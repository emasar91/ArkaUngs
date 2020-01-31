package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Pelota {
	private int x;
	private int y;
	final int radio=5;
	private double dx=1, dy=-1;
	int limiteIz=39;
	int limiteDer=460;
	int limiteArr=115;
	private double velocidad=2;
	private boolean pegada=true;
	Image pelota= Herramientas.cargarImagen("pelota.png");
		
	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public boolean isPegada() {
		return pegada;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadio() {
		return radio;
	}

	Pelota(int x, int y){
		this.x=x;
		this.y=y;
	}
												
	void mover(){									//MOVIMIENTO DE LA PELOTA
		if (this.pegada==false){ 					//SE MUEVE SI LA PELOTA NO ESTA PEGADA
			this.x+=(dx*velocidad);					//EL X DE LA PELOTA SE INCREMENTA SEGUN LA VELOCIDAD
			this.y+=(dy*velocidad);					//EL Y DE LA PELOTA SE INCREMETAN SEGUN LA VELOCIDAD
													//MOVIMIENTO DE LA PELOTA EN FORMA DE CUADRADO
			if(this.x>460-this.radio)				//SI LA PELOTA SUPERA EL LIMITE DE LA DERECHA
				cambiarDirx();						//CAMBIA DE DIRECCION EN EL EJE X
			if(this.x<39+this.radio)				//SI LA PELOTA SUPERA EL LIMITE DE LA IZQUIERDA
				cambiarDirx();						//CAMBIA DE DIRECCION EN EL EJE X
			if(this.y>115+this.radio)				//SI LA PELOTA SUPERA EL LIMITE DE ARRIBA
				cambiarDiry();						//CAMBIA DE DIRECCION EN EL EJE Y
			if(this.y<620)							//SI LA PELOTA SUPERA EL LIMITE DE ABAJO
				cambiarDiry();						//CAMBIA DE DIRECCION EN EL EJE Y
		}
	}
	
	void dibujarPelota(Entorno entorno){			//DIBUJO DE LA PELOTA (CIRCULO)
		entorno.dibujarImagen(pelota, this.x, this.y, 0);
	}
	
	void cambiarDirx(){ 							//CAMBIO DE DIRECCION EN EJE X
		this.dx*=-1;
	}
	
	void cambiarDiry(){								//CAMBIO DE DIRECCION EN EJE Y
		this.dy*=-1;
	}
	
	boolean estado(){								
		if (this.pegada)
			return true;
		return false;
	}
	
	void saleIzq(){									//LA PEOTA SALE PARA LA IZQUIERDA
		this.dx=-1;
		this.dy=-1;
	}
	void saleDer(){									//LA PELOTA SALE PARA LA DERECHA
		this.dx= 1;
		this.dy=-1;
	}
	
	///////////////////////////////////////////////////////////////////////////
	/////////////////////////PODERES DE PELOTA//////////////////////////////////
	
	void poderPelotaLenta(){						//CAMBIA LA VELOCIDAD DE LA PELOTA
		this.velocidad=1;
	}
	void poderPelotaRapida(){						//CAMBIA LA VELOCIDAD DE LO PELOTA
		this.velocidad=2;
	}
	void poderPegote(boolean colision){				//LA PELOTA QUEDA PEGADA EN LA BARRA
		if(colision)
			this.pegada=true;
	}
	
	void sinPegote(){								//QUITA EL PODER DE PEGOTE
		this.pegada=false;
	}
	
	void velNormal(){								//VUELVE A LA NORMALIDAD LA VELOCIDAD
		this.velocidad=2;
	}

	void moverConBarra(int x, int y){				//LA PELOTA SE MUEVE CON LA BARRA
		this.y= y-10;
		this.x= x;
	}
}
