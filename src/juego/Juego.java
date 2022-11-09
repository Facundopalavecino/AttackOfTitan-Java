package juego;
import java.util.Random;

import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Mikasa mikasa;
	private Titan[] titanes = new Titan[6];
	private Obstaculo[] obstaculos = new Obstaculo[4];
	private Suero suero;
	private Proyectil proyectil;
	private Fondo fondo;
	private SoundFX sonido;

	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego(){
		// Inicializa el objeto entorno
		entorno = new Entorno(this, "Attack on Titan, Final Season - Grupo 2 - v1", 1024, 720);
		fondo = new Fondo(512, 360);
		sonido = new SoundFX();
		
		// Inicializar lo que haga falta para el juego
		// ...
		Inicializar();

		// Inicia el juego!
		
		entorno.iniciar();
	}
	
	public void teclasMovMikasa() {
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
			mikasa.moverArriba();
		if (entorno.estaPresionada(entorno.TECLA_ABAJO))
			mikasa.moverAbajo();
		if (entorno.estaPresionada(entorno.TECLA_DERECHA))
			mikasa.girar(Herramientas.radianes(2));
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
			mikasa.girar(Herramientas.radianes(-2));
	}

	public void interaccionMikasaEntornoObstaculosSuero(){
		
		mikasa.chocaEntorno(); // impide que se pase de los limites del nivel
		
		for (int i = 0; i < obstaculos.length; i++) {
			if (mikasa.chocasteCon(obstaculos[i])) {
				mikasa.chocaObs(obstaculos[i]);
			}	
		}
		
		if (suero!= null && mikasa.tomaSuero(suero, 35)) {
			sonido.getMikasaSuero().setFramePosition(0);
			sonido.getMikasaSuero().start();
			mikasa.seTransforma();
			suero = null;
			
			
		}
		
	}
	
	public void dibujarTitan(Titan titan){
		titan.dibujar(entorno);
		titan.mover();
	}

	public boolean hayTitanAhi(Titan[] titanes,int x, int y) {
		for (int i = 0; i < titanes.length; i++) {
			if (titanes[i]!= null) {
				double titanX = titanes[i].getX();
				double titanY = titanes[i].getY();
				int titanRad = titanes[i].getRadio() * 2;
				
				if (x <= titanX + titanRad && x >= titanX - titanRad && y >= titanY - titanRad && y <= titanY + titanRad) {
					return true;
					
				}
			}
		}
		
		return false;
	}

	public void generadorTitanes() {
		for (int i = 0; i<6; i++) {
			while(titanes[i] == null) {			
			Random generador = new Random();
			
			int x = 60 + (generador.nextInt(2)*900);
			int y = 60 + (generador.nextInt(600));
			int velocidad =  1 + generador.nextInt(1);// algunos 2 otros 3
			int angulo = generador.nextInt(360);
				if (!hayTitanAhi(titanes,x,y)) {
				//( x,  y,  velocidad,angulo,radio)
				titanes[i] = new Titan (x, y, velocidad, Math.toRadians(angulo), 30);
				}
			}
		}
		
	}
	
	public void revivirTitan() {
		for (int i = 0; i<6; i++) {
			if(titanes[i] == null) {			
				Random generador = new Random();
				int revivirTitan = generador.nextInt(667);

				if (revivirTitan == 666) {
					while(titanes[i] == null) {
						int x = 60 + (generador.nextInt(2)*900);
						int y = 60 + (generador.nextInt(600));
						int velocidad =  1 + generador.nextInt(2);// algunos 2 otros 3
						int angulo = generador.nextInt(360);
						if (!hayTitanAhi(titanes,x,y)) {
							//( x,  y,  velocidad,angulo,radio)
							titanes[i] = new Titan (x, y, velocidad, Math.toRadians(angulo), 30);
						}
					}
				}
			}
		}
		
	}
	
	public void interaccionTitanesEntornoObstaculosTitanes(Titan titan){
		if (titan.chocasteCon(entorno)) {	//controla choque con entorno
			titan.cambiarTrayectoria();
		}
		
		for (int j = 0; j < obstaculos.length; j++) {
			if (titan.chocasteCon(obstaculos[j]))
				titan.cambiarTrayectoria();
		}
		for (int k = 0; k < titanes.length; k++) {
			if(titanes[k] != null){
				if (titan != titanes[k]) {
					if (titan.chocasteCon(titanes[k],60)) {
						titan.cambiarTrayectoria();
					}
				}
			}
		}
	}
	
	public void generadorSuero() {
		
		if (suero == null && !mikasa.getTransformada()) {
			Random generador = new Random();
			int numeroSuerte = generador.nextInt(4001);
			if (numeroSuerte == 1000) {
				sonido.getSuero().setFramePosition(0);
				sonido.getSuero().start();
				
				suero = new Suero(512,200,15);// arriba
			}
			if (numeroSuerte == 2000) {
				sonido.getSuero().setFramePosition(0);
				sonido.getSuero().start();
				
				suero = new Suero(512,600,15); // abajo
			}
			if (numeroSuerte == 3000) {
				sonido.getSuero().setFramePosition(0);
				sonido.getSuero().start();
				
				suero = new Suero(200,360,15); // izquierda
			}
			if (numeroSuerte == 4000) {
				sonido.getSuero().setFramePosition(0);
				sonido.getSuero().start();
				
				suero = new Suero(800,360,15); // derecha
			}
		}
		
		if (suero != null) {
			suero.dibujar(entorno);
		}	
	}
	
	public void dibujarObstaculos() {
		obstaculos[0].dibujar(entorno);
		obstaculos[1].dibujar(entorno);
		obstaculos[2].dibujar(entorno);
		obstaculos[3].dibujar(entorno);
	}
	
	public void dibujarProyectil() {
		if(this.entorno.sePresiono(this.entorno.TECLA_ESPACIO) && proyectil == null){
			proyectil = new Proyectil(mikasa.getX(),mikasa.getY(),8,mikasa.getAngulo() + Math.toRadians(180),5) ;
			sonido.getProyectil().setFramePosition(0);
			sonido.getProyectil().start();
		}
		if (proyectil != null) {
			
			
			proyectil.dibujar(entorno);
			proyectil.mover();
			

		}
	}
	
	public void interaccionProyectilEntornoObstaculos() {
		if (proyectil != null) {
			if(proyectil.chocasteCon(entorno)) {
				proyectil = null;
				sonido.getProyectil().stop();
			}
		}	
		if (proyectil != null){
				for (int i = 0; i < obstaculos.length; i++) {
					if (proyectil.chocasteCon(obstaculos[i])) {
						proyectil = null;
						sonido.getProyectil().stop();
						break;
					}
				}
			}
		
		
	}
	
	public void interaccionProyectilTitan(){
		if (proyectil != null) {
			for(int i = 0; i < titanes.length; i++) {
				if (titanes[i] != null) {
					if (proyectil.mataTitan(titanes[i].getX(),
						titanes[i].getY(), 
						titanes[i].getRadio() + proyectil.getRadio() )) {
						mikasa.setPuntaje(mikasa.getPuntaje() + 1);
						proyectil = null;
						titanes[i] = null;
						sonido.getMuerteTitan().setFramePosition(0);
						sonido.getMuerteTitan().start();
						break;
						
					}
				}
			}
		}
	}
	

	
	public void borrarParaReinicio() { // resetea los elementos incluidos para iniciar nuevo juego
		for(int i = 0; i< titanes.length; i++) {
			titanes[i] = null;
		}
		suero = null;
		proyectil = null;
		if (mikasa != null) {
			mikasa.setTransformada(false);
		}
		
	}
	
	public boolean ganaste(Titan[] titanes) {
		for (int i = 0; i< titanes.length; i++) {
			if (titanes[i] != null) {
				return false;
			}
		}
		return true;
	}
	
	public void iniciarJuego() {
		if(entorno.estaPresionada(entorno.TECLA_ENTER)&& (mikasa.getVida()==0) 
				|| entorno.estaPresionada(entorno.TECLA_ENTER)&& (ganaste(titanes))) {
			Inicializar();
			sonido.getIntro().stop();
			sonido.getIntro().setFramePosition(0);
			sonido.getGanar().stop();
			
			mikasa.setVida(1);
			sonido.getBatalla().start();
			
		}
	}
	
	public void Inicializar() {
		
		
		borrarParaReinicio();
		
		
		
		//mikasa
		mikasa = new Mikasa(512 ,360 ,4,Math.toRadians(0),20,0);		
		
		//titanes
		generadorTitanes();
		
		//obstaculos
		
		obstaculos[0] = new Obstaculo (250,200,100,100);
		obstaculos[1] = new Obstaculo (250,520,100,100);
		obstaculos[2] = new Obstaculo (774,200,100,100);
		obstaculos[3] = new Obstaculo (774,520,100,100);
		

	}

	/*
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	public void tick(){	
	
		// Procesamiento de un instante de tiempo
		// ...
		iniciarJuego();
		
		fondo.dibujarse(entorno);
			
		// obstaculos 
		
		dibujarObstaculos();
		
		//mikasa**************************************************			
		
		if (mikasa.viva() && !ganaste(titanes)) {
			
			mikasa.dibujar(entorno);
			
			teclasMovMikasa();
			
			interaccionMikasaEntornoObstaculosSuero();
		// hacer trampa
		if(this.entorno.sePresiono(this.entorno.TECLA_SHIFT)){
			mikasa.seTransforma();
		}
			
			
				
		//proyectil------------------------------------------
			dibujarProyectil();
			
			interaccionProyectilEntornoObstaculos();
			
			interaccionProyectilTitan();


			// titanes **************************************************
		
			for (int i = 0; i<titanes.length; i++) {
				if (titanes[i] != null) {
					dibujarTitan(titanes[i]);
				
					interaccionTitanesEntornoObstaculosTitanes(titanes[i]);
					//interaccion con mikasa no se pudo realizar funcion
					if(titanes [i].detectaMikasa(mikasa.getX(),mikasa.getY(),180)
							&& !titanes [i].chocasteCon(entorno) 
							&& !titanes [i].chocasteCon(obstaculos[0]) 
							&& !titanes [i].chocasteCon(obstaculos[1]) 
							&& !titanes [i].chocasteCon(obstaculos[2]) 
							&& !titanes [i].chocasteCon(obstaculos[3])) {
						
						titanes[i].persigueMikasa(mikasa.getX(),mikasa.getY());
					}
					// controla si mata a mikasa
					if (titanes[i].mataMikasa(mikasa.getX(),mikasa.getY(),45)){
						if (mikasa.getTransformada()) {
							titanes[i] = null;
							sonido.getMuerteTitan().setFramePosition(0);
							sonido.getMuerteTitan().start();
							mikasa.seDestransforma();
							mikasa.setPuntaje(mikasa.getPuntaje() + 1);
						}else {
											
							mikasa.setVida(mikasa.getVida()-1);
							sonido.getMuerteMikasa().setFramePosition(0);
							sonido.getMuerteMikasa().start();
						}
						
					}
				}
			}
			revivirTitan();
			//suero ********************************************
			
			generadorSuero();	
			
			entorno.cambiarFont("Arial", 30, Color.white);
			entorno.escribirTexto("tu puntaje es  " + mikasa.getPuntaje(), 800, 50);
		
			
		}else { 
			
			if (ganaste(titanes)) {
				entorno.cambiarFont("Arial", 30, Color.yellow);
				entorno.escribirTexto("Ganaste tu puntaje es  " + mikasa.getPuntaje(), 400, 310);
				entorno.escribirTexto("Presione ENTER para jugar", 380, 360);
				sonido.getBatalla().stop();
				sonido.getBatalla().setFramePosition(0);
				sonido.getGanar().start();
				
			
			}else{
				entorno.cambiarFont("Arial", 18, Color.white);
				entorno.escribirTexto("Tu puntaje es  " + mikasa.getPuntaje(), 400, 310);
				entorno.escribirTexto("Presione ENTER para jugar", 380, 360);
				
				sonido.getBatalla().stop();
				sonido.getBatalla().setFramePosition(0);
				
				sonido.getIntro().start();
				
				
			}
			
		}
	
		
	}//------------------------------------- fin tick ---------------------------------
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
