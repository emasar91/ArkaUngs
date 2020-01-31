package juego;


import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	private Entorno entorno;
	
	Image fondo = Herramientas.cargarImagen("fondo.png");				//SE CARGA LA IMAGEN FONDO 
	Image logo = Herramientas.cargarImagen("logo.png"); 				//SE CARGA LA IMAGEN LOGO
	Image win = Herramientas.cargarImagen("YouWin.jpg");				//SE CARGA LA IMAGEN WIN
	Image marcos = Herramientas.cargarImagen("marcos.png");				//SE CARGA LA IMAGEN MARCOS
	Image explosion = Herramientas.cargarImagen("explosion.png");		//SE CARGA LA IMAGEN EXPLOSION
	Image pegote=Herramientas.cargarImagen("pegote.png");				//SE CARGA LA IMAGEN PEGOTE
	Image gameover=Herramientas.cargarImagen("gameover.png");			//SE CARGA LA IMAGEN GAMEOVER
	Image verde=Herramientas.cargarImagen("verde.png");					//SE CARGA LA IMAGEN LADRILLO VERDE
	Image verdeclaro=Herramientas.cargarImagen("verde claro.png");		//SE CARGA LA IMAGEN LADRILLO VERDE CLARO
	Image azul=Herramientas.cargarImagen("azul.png");					//SE CARGA LA IMAGEN LADRILLO AZUL
	Image celeste=Herramientas.cargarImagen("celeste.png");				//SE CARGA LA IMAGEN LADRILLO CELESTE
	Image naranja=Herramientas.cargarImagen("naranja.png");				//SE CARGA LA IMAGEN LADRILLO NARANJA
	Image amarillo=Herramientas.cargarImagen("amarillo.png");			//SE CARGA LA IMAGEN LADRILLO NARANJA
	Image gris=Herramientas.cargarImagen("gris.png");					//SE CARGA LA IMAGEN LARILLO GILS
	Image vida = Herramientas.cargarImagen("vidas.png");				//SE CARGA LA IMAGEN DE LAS VIDAS
	Image negro=Herramientas.cargarImagen("negro.png");					//SE CARGA LA IMAGEN LADRILLO NEGRO
	Image especial=Herramientas.cargarImagen("ladrillo especial.png");	//SE CARGA LA IMAGEN LADRILLO ESPECIAL
	
	private Disparo disparo; 						//DISPARO EN PANTALLA
	private int alto=8;								//CANIDAD DE LADRILLOS POR FILA
	private int ancho=14; 							//CANTIDAD DE LADRILLOS POR COLUMNAS
	private int puntos=0; 							//CANTIDAD DE PUNTOS OBTENIDOS
	private int vidas =3;							//CANTIDAD DE VIDAS EN EL JUEGO
	private int random;								//GENERADOR DE RANDOM PARA PODERES
	private int contador;							//CONTADOR AUXILIAR PARA LA VIDA EXTRA	
	private Barra barra= new Barra(); 							//BARRA
	private Pelota pelota= new Pelota(250,barra.getY()-10); 							//PELOTA 
	private Ladrillos [][] ladrillos= new Ladrillos[alto][ancho]; 				//LADRILLOS EN PANTALLA
	private boolean terminado;						//MIENTRAS SEA FALSE EL JUEGO CONTINUA
	private String [] poderes= {"NINGUNO","DISPAROS", "VIDA EXTRA","PELOTA RAPIDA", "PELOTA LENTA","BARRA GRANDE", "PEGOTE"};	//NOMBRE DE LOS PODERES
	Random ran= new Random();						//RANDOM	
	
	
	
	
	Juego()
	{
		this.entorno = new Entorno(this, "ArkaUngs - SARCO - V0.01", 700, 600);
		int conty=180; 												//CONTADOR PARA QUE LOS LADRILLOS NO SE SUPERPONGAN EN EJE Y
		for (int i=0; i<alto;i++){									//SE RECORRE LA MATRIZ POR EL ALTO
			int contx=63;											//CONTADOR PARA QUE LOS LADRILLOS NO SE SUPERPONGAN EN EL EJE X
			for (int j=0; j<ancho;j++){								//SE RECORRE LA MATRIZ POR EL ANCHO
				if (i==0){											//LA PRIMERA FILA DE LADRILLOS
					ladrillos[i][j]= new Ladrillos (contx,conty);	//SE CREAN LOS LADRILLOS CON LOS X, Y DE LOS CONTADORES
					ladrillos[i][j].ladrilloDuro();						//ESTOS LADRILLOS TIENEN 2 GOLPES
				}else
					ladrillos[i][j]= new Ladrillos (contx,conty);	//TODOS LOS DEMAS TIENEN UN GOLPE POR CONSTRUCTOR
				contx+=29;											//SE INCREMENTA EN CONTADOR DE X
			}
			conty+=14;												//SE INCREMENTA EL CONTADOR DE Y
		}
		this.entorno.iniciar();	
	}
	
	public void tick()
	{	

		//////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////INICIO DEL JUEGO/////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////
		if (terminado==false){											//MIETNRAS NO ESTE TERMINADO
			if(vidas>=1){												//Y TENGA VIDAS

				//////////////////////////////////////////////////////////////////////////////////
				/////////////SI NO HAY LADRILLOS EN PANTALLA SE GANA EL JUEGO/////////////////////
				//////////////////////////////////////////////////////////////////////////////////
				int contador1= ancho*alto;								//EL CONTADOR VALE LO MSMO QUE LA CANTIDAD DE LADRILLOS CUANDO EMPIEZ EL JUEGO
				for (int i=0; i<alto;i++){								//RECORRE LA MATRIZ POR EL ALTO
					for (int j=0; j<ancho;j++){							//RECORRE LA MATRIZ POR EL ANCHO
						if (ladrillos[i][j]==null)						//SI EL LADRILLO ES NULO
							contador1--;								// SE RESTA AL CONTADOR
					}
					if (contador1==0)									//SI EL CONTADOR ES IGUAL A CERO (TODOS LOS LADRIILOS SON NULOS)
						terminado=true;									//EL JUEGO TERMINA
				}
				

				/////////////////////////////////////////////////////////////////////////////////
				//////////////////////////VENTANA DEL JUEGO//////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////
				entorno.dibujarImagen(fondo, 350, 300, 0);				//SE DIBUJA EL FONDO
				entorno.dibujarImagen(logo, 350, 58, 0);				//SE DIBUJA EL LOGO
				entorno.dibujarImagen(marcos, 250, 355, 0);				//IMAGE DE LOS BORDES
				if(vidas>=4)											//LA CANTIDAD DE VIDAS MAXIMA ES DE 4
					vidas=4;
				for(int i=0;i<vidas;i++){								//SE DIBUJAN LAS VIDAS
					int contador=60;
					for (int j=0;j<vidas;j++){
						entorno.dibujarImagen(vida, contador, 600,0);	//CON LA IMAGEN VIDAS
						contador+=35;
					}
					
				}
				entorno.cambiarFont("Impact", 18, Color.red);			//CAMBIO DE LA FUENTE
				entorno.escribirTexto("PUNTOS: "+puntos, 490,570 );		//DIBUJO DE PUNTOS
				entorno.cambiarFont("Impact", 18, Color.white);			//CAMBIO DE LA FUENTE
				entorno.escribirTexto("PODER: " + poderes[random], 490, 590);   	//DIBUJO DE LOS PODERES
				

				if(contador>=1 && random!=2)
					contador=0;
				if(random!=3 && random!=4)
					pelota.velNormal();
				
				/////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////PODERES//////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////
				if (entorno.estaPresionada('6'))						//SI SE PRESIONA EL NUMERO 6
					random=6;											//SE UTILIZA EL PODER 6
				if (entorno.estaPresionada('5'))						//SI SE PRESIONA EL NUMERO 5
					random=5;											//SE UTILIZA EL PODER 5		
				if (entorno.estaPresionada('4'))						//SI SE PRESIONA EL NUMERO 4	
					random=4;											//SE UTILIZA EL PODER 4		
				if (entorno.estaPresionada('3'))						//SI SE PRESIONA EL NUMERO 3			
					random=3;											//SE UTILIZA EL PODER 3				
				if (entorno.estaPresionada('2'))						//SI SE PRESIONA EL NUMERO 2			
					random=2;											//SE UTILIZA EL PODER 2		
				if (entorno.estaPresionada('1'))						//SI SE PRESIONA EL NUMERO 1								
					random=1;											//SE UTILIZA EL PODER 1				
				
				
				
				if(random==6)											//POODER PEGOTE
					pelota.poderPegote(barra.colisionCentro(pelota));
				
				if (random==5){											//PODER BARRA GRANDE
					barra.grandeBarra();
				}else{
					barra.chicaBarra();
					
					
				}
				if (random==4)											//PODER PELOTA LENTA
					pelota.poderPelotaLenta();
						
				if (random==3)											//PODER PELOTA RAPIDA
					pelota.poderPelotaRapida();
				
				if (random==2){											//PODER VIDA EXTRA
					if (contador<1){
						contador++;
						vidas++;
					}	
				}
				
				
				if (random==1){														//PODER DISPAROS
					barra.disparo();										
					barra.dibujarEquipada(entorno);									//SE DIBUJA LA BARRA EQUIPADA
					if (entorno.sePresiono(entorno.TECLA_ESPACIO)){
						if (disparo==null)											//SI NO HAY DISPARO EN PANTALLA
							disparo =new Disparo(barra.getX(), barra.getY());		//SE CREA UN DISPARO CON LAS COORDENADA DE LA BARRA
					}
				}else
					barra.normal();
				
				if (disparo!=null){										//SI YA HAY DISPARO EN PANTALLA
					disparo.mover();									//EL DISPARO SE MUEVE
					disparo.dibujarDisparo(entorno);					//EL DISPARO SE DIBUJA
					if (disparo.getDireccion()==115)							//SI EL DISPARO LLEGA AL BORDE DE ARRIBA
						disparo=null;									//SE QUITA DE LA PANTALLA
				}
								
				/////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////BARRA////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////
				if (random==6){
					barra.dibujarBarra(entorno);						//DIBUJO DE LA BARRA CON PEGOTE
					entorno.dibujarImagen(pegote, barra.getX(), barra.getY()-2, 0);
				}else
					if (barra.isEquipada()==false)						//SI LA BARRA NO ESTA EQUIPADA
						barra.dibujarBarra(entorno);					//SE DIBUJA LA BARRA NORMAL
											
				if (entorno.estaPresionada(entorno.TECLA_DERECHA)) 		//SI ESTA PRESIONADO LA TECLA DERECHA
					barra.moverDerecha();								//LA BARRA SE MUEVE A LA DERECHA
				if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) 	//SI ESTA PRESIONADO LA TECLA IZQUIERDA
					barra.moverIzquierda();								//LA BARRA SE MUEVE A LA IZQUIERDA
				barra.limite(); 										//LIMITE DE LA BARRA EN LOS LADOS
				if (entorno.estaPresionada(entorno.TECLA_ESPACIO)){ 	//PELOTA INICIAL PEGADA A LA BARRA, SI SE APRETA ESPACIO SE LIBERA
						if(pelota!=null)								
							pelota.sinPegote();	
				}
				
				/////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////PELOTA/////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////
				if(pelota!=null){
						if(pelota.estado()){ 							//SI LA PELOTA ESTA PEGADA A LA BARRA
							if (puntos%2==0){							//SI LOS PUNTOS SON PARES LA PELOTA SALE PARA LA DERECHA
								pelota.saleDer();;
							}
							if (puntos%2!=0){							// SI LOS PUNTOS SON IMPARES LA PELOTA VA PARA LA IZQUIERDA
								pelota.saleIzq();
							}
							pelota.moverConBarra(barra.getX(),barra.getY());//SE MUEVE CON LA BARRA
						}
						pelota.mover();									//SINO LA PELOTA SE MUEVE 
						pelota.dibujarPelota(entorno);					//DIBUJO DE LA PELOTA
					
						
					///////////////////////////////COLISION DE BARRA PELOTA(CENTRO)///////////////////
					if(barra.colisionCentro(pelota)){					//SI LA PELOTA COLISIONA CON EL CENTRO DE LA BARRA
						pelota.cambiarDiry();							//CAMBIA DE DIRECCION EN EL EJE Y
					}
											
					////////////////////////////PERDIDA DE VIDAS////////////////////////////////////////
					if (pelota.getY()>600){									//SI LA PELOTA SUPERA LA BARRA
						pelota=null; 									//SE QUITA
						vidas--; 										//SE RESTA UNA VIDA
						random=0;
						if (vidas>=1){ 									//SI TODAVIA TIENE VIDAS
							for(int j=0; j<1;j++)
								pelota= new Pelota(barra.getX(),barra.getY()-10); //SE CREA UNA PELOTA NUEVA
						}
					}	
				}
				//////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////////LADRILLOS////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////
				
				
				
				///////////////DIBUJO DE LOS LADRILLOS///////////////////////////////////////////
				for (int i=0; i<alto;i++){								//RECORRE LA MATRIZ POR EL ALTO
					for (int j=0; j<ancho;j++){							//RECORRE LA MATRIZ POR EL ANCHO
						if(ladrillos[i][j]!=null && ladrillos[i][j].getGolpes()>=1){						//SI EL LADRILLO NO ES NULO
							if (i==0 && ladrillos[i][j].getGolpes()==1)									//SE DIBUIJAN TODOS LOS LADRILLOS POR COLORES DEPENDIENDO DE I
								entorno.dibujarImagen(gris, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
							if (i==0 && ladrillos[i][j].getGolpes()==2)
								entorno.dibujarImagen(negro, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
							if (i==1)
								entorno.dibujarImagen(verde, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
							if (i==2)
								entorno.dibujarImagen(verdeclaro, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
							if (i==3)
								entorno.dibujarImagen(azul, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
							if (i==4)
								entorno.dibujarImagen(celeste, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
							if (i==5)
								entorno.dibujarImagen(naranja, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
							if (i>5)
								entorno.dibujarImagen(amarillo, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
						}
					}
				}
				//////////////COLISION ENTRE DISPARO Y LADRILLOS/////////////////////////////////
				for (int i=0; i<alto;i++){								//RECORRE LA MATRIZ POR EL ALTO
					for (int j=0; j<ancho;j++){							//RECORRE LA MATRIZ POR EL ANCHO
						if(ladrillos[i][j]!=null){						//SI HAY LADRILLOS EN PANTALLA
							if(disparo!=null){							//SI HAY DISPARO EN LA PANTALLA
								if (ladrillos[i][j].colisionDisparo(disparo)){	//SI EL DISPARO TOCA CONTRA UN LADRILLO
									disparo=null;						//SE QUITA EL DISPARO DE LA PANTALLA
									ladrillos[i][j].quitarGolpes();			//SE LE RESTA UN GOLPE AL LADRILLO
									entorno.dibujarImagen(explosion, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
									puntos+=23;							//SE SUMAN 23 PUNTOS
								}
							}
						}
					}
				}
					
				//////////////COLISION ENTRE PELOTA Y LADRILLOS/////////////////////////////////	
				for (int i=0; i<alto;i++){								//RECORRE LA MATRIZ POR EL ALTO
					for (int j=0; j<ancho;j++){							//RECORRE LA MATRIZ POR EL ANCHO
						if (pelota!=null){								//SI LA PELOTA NO ES NULA
							if(ladrillos[i][j]!=null && ladrillos[i][j].getGolpes()>=1){					//SI EL LADRILLO NO ES NULO
								if (ladrillos[i][j].colisionDer(pelota) || ladrillos[i][j].colisionIzq(pelota) ){ //SI HAY COLISION POR LA DERECHA O IZQUIERDA
									ladrillos[i][j].quitarGolpes();			//LE QUITA UN GOLPE AL LADRILLO
									puntos+=23;							//SUMA 23 PUNTOS
									entorno.dibujarImagen(explosion, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
									pelota.cambiarDirx();				//CAMBIA LA DIRECCION EN EL EJE X	
								
								}
								if(ladrillos[i][j]!=null && ladrillos[i][j].getGolpes()>=1){
									if (ladrillos[i][j].colisionAbajo(pelota) || ladrillos[i][j].colisionArriba(pelota) ){ //SI HAY COLISION POR ABAJO
										ladrillos[i][j].quitarGolpes();			//LE QUITA UN GOLPE AL LADRILLO
										puntos+=23;							//SUMA 23 PUNTOS
										entorno.dibujarImagen(explosion, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
										pelota.cambiarDiry();				//CAMBIA DE DIRECCION EN EL EJE Y
									}
								}
							}
						}		
					}
				}	
				
					
				/////////////////////////////////////LADRILLO ESPCIAL////////////////////////////////////////////	
				for (int i=0; i<alto;i++){								//RECORRE LA MATRIZ POR EL ALTO
					for (int j=0; j<ancho;j++){							//RECORRE LA MATRIZ POR EL ANCHO
						if (pelota!=null){								//SI LA PELOTA NO ES NULA
							if(ladrillos[i][j]!=null){					//SI EL LADRILLO NO ES NULO
								if (ladrillos[i][j].getGolpes()==0){			//Y EL LADRILLO NO TIENE MAS GOLPES
									if(puntos%5==0)						//Y LOS PUNTOS SON MULTIPLO DE 5
										ladrillos[i][j].darPoder();;		//EL LADRILLO SE CONBVIERTE EN UNO CON PODER
									if(ladrillos[i][j].isPoder()==true){	//SI EL LADRILLO TIENE PODER
										ladrillos[i][j].mover();		//EL  LADRILLO SE MUEVE HACIA ABAJO
										entorno.dibujarImagen(especial, ladrillos[i][j].getX(), ladrillos[i][j].getY(), 0);
										if (ladrillos[i][j].colisionBarra(barra)){	//SI EL LADRILLO SUPERA LA BARRA O LA BARRA LOS TOCA
											ladrillos[i][j]=null;		//EL LADRILLO SE CONVIERTE EN NULO
											random=(int)(ran.nextDouble()*6)+1;;	//Y LA VARIABLE RANDOM TOMA UN VALOR ENTRE 1 Y 7	
										}
										else{
											if(ladrillos[i][j].getY()>590)	//SI EL LADRIILLO SOBRE PASA A LA BARRA
											ladrillos[i][j]=null;	//SE QUITA DE PANTALLA
										}
									}
								}
							}
						}		
					}
			}
			///////////////////////////////////////LADRILLO COMUN//////////////////////////////////////////////
			for (int i=0; i<alto;i++){								//RECORRE LA MATRIZ POR EL ALTO
				for (int j=0; j<ancho;j++){							//RECORRE LA MATRIZ POR EL ANCHO	
					if (pelota!=null){								//SI LA PELOTA NO ES NULA
						if(ladrillos[i][j]!=null){					//SI EL LADRILLO NO ES NULO
							if (ladrillos[i][j].getGolpes()==0){			//SI EL LADRILLO NO TIENE MAS GOLPES
								if(ladrillos[i][j].isPoder()==false)	//SI EL LADRILLO NO TIENE PODES
									ladrillos[i][j]=null;			//EL LADRILLO SE QUITA						
								}
							}
						}
					}
				}
			}
			
		}
			
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////EL JUEGO TERMINA PERDIO ////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (vidas==0){												//SI NO TIENE VIDAS
			entorno.dibujarImagen(gameover, 350, 300, 0);			//SE DIBUJA GAMEOVER
			entorno.cambiarFont("Arial", 18, Color.white);			//CAMBIO DE LA FUENTE
			entorno.escribirTexto("PRESIONE ENTER PARA REINICIAR", 210, 550);	//TIENE LA OPCION DE REINICIAR CON ENTER
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////EL JUEGO TERMINA PERDIO ////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (terminado){												//SI EL JUEGO ESTA TERINADO
			entorno.dibujarImagen(win, 350, 300, 0);				//SE DIBUJA WIN
			entorno.cambiarFont("Arial", 18, Color.white);			//CAMBIO DE LA FUENTE
			entorno.escribirTexto("PUNTOS: "+puntos, 320, 550);		//SE MUESTRA LA CANTIDAD DE PUNTOS
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////REINICIO DE JUEGO//////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		if (entorno.estaPresionada(entorno.TECLA_ENTER)){				//SI SE APRETO ENTER DESPUES DE PERDER
			vidas=3;													//SE REINICIAN TODAS LAS VARIABLES PARA EMPEZAR DE CERO
			puntos=0;
			barra= new Barra(); 										//SE CREA LA BARRA DE JUEGO
			pelota= new Pelota(250,barra.getY()-10);							//SE CREA LA PRIMERA PELOTA CON X=MEDIO DE PANTALLA Y=UN PIXEL POR ENCIMA DE LA BARRA
			ladrillos= new Ladrillos[alto][ancho];						//LA MATRIZ QUE CONTIENE LOS LADRILLOS
			int conty=180; 												//CONTADOR PARA QUE LOS LADRILLOS NO SE SUPERPONGAN EN EJE Y
			for (int i=0; i<alto;i++){									//SE RECORRE LA MATRIZ POR EL ALTO
				int contx=63;											//CONTADOR PARA QUE LOS LADRILLOS NO SE SUPERPONGAN EN EL EJE X
				for (int j=0; j<ancho;j++){								//SE RECORRE LA MATRIZ POR EL ANCHO
					if (i==0){											//LA PRIMERA FILA DE LADRILLOS
						ladrillos[i][j]= new Ladrillos (contx,conty);	//SE CREAN LOS LADRILLOS CON LOS X, Y DE LOS CONTADORES
						ladrillos[i][j].ladrilloDuro();						//ESTOS LADRILLOS TIENEN 2 GOLPES
					}else
						ladrillos[i][j]= new Ladrillos (contx,conty);	//TODOS LOS DEMAS TIENEN UN GOLPE POR CONSTRUCTOR
					contx+=31;											//SE INCREMENTA EN CONTADOR DE X
				}
				conty+=16;												//SE INCREMENTA EL CONTADOR DE Y
			}
		}		
	} 

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
