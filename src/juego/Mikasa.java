package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Mikasa {
	
	private double x;
	private double y;
	private double velocidad;
	private double angulo;
	private int radio;
	private int vida;
	private boolean transformada;
	private int puntaje;
	Image img1;
	Image img2;

	
	public Mikasa(double x, double y, double velocidad, double angulo, int radio,int vida) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.radio = radio;
		this.vida = vida;
		this.transformada = false;
		this.puntaje = 0;
		this.img1 = Herramientas.cargarImagen("mikasa.png");
		this.img2 = Herramientas.cargarImagen("transformada.png");

	}
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	public boolean getTransformada() {
		return transformada;
	}
	
	public double getAngulo() {
		return this.angulo;
	}

	public void setTransformada(boolean transformada) {
		this.transformada = transformada;
	}
	
	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	


	public void dibujar(Entorno e) {
		//e.dibujarCirculo(x, y, 2 * radio, Color.blue);
		
		if (this.transformada) {
		e.dibujarImagen(img2, this.x, this.y, this.angulo, 0.2);
		}else {
		e.dibujarImagen(img1, this.x, this.y, this.angulo, 0.2);
		}
	}


	public void moverArriba() {
		this.x -= Math.cos(this.angulo)*velocidad;
		this.y -= Math.sin(this.angulo)*velocidad;
	}

	public void moverAbajo() {
		this.x += Math.cos(this.angulo)*velocidad;
		this.y += Math.sin(this.angulo)*velocidad;
	}
	public void girar(double modificador) 
	{
		this.angulo += modificador;
	}
	
	public boolean viva() {
		return this.vida > 0;
	}
	public void chocaObs(Obstaculo obstaculo) { // funcion que impide que mikasa traspase objetos
		int mitadAncho = obstaculo.getAncho()/2;
		int mitadAlto = obstaculo.getAlto()/2;
		int iniX = obstaculo.getX() - mitadAncho;
		int finX = obstaculo.getX() + mitadAncho;
		int iniY = obstaculo.getY() - mitadAlto;
		int finY = obstaculo.getY() + mitadAlto;
		
		if (this.y >= iniY && this.y <= finY) {
			if (this.x <= finX + this.radio && this.x >= finX) {
				this.x += 4; 
			}
			if (this.x >= iniX - this.radio && this.x <= iniX) {
				this.x -= 4;
			}
			
		}
		if (this.x >= iniX && this.x <= finX ) {
			if (this.y >= iniY - this.radio && this.y <= iniY) {
				this.y -= 4; 
			}
			if (this.y <= finY + this.radio && this.y >= finY) {
				this.y += 4;
			}
		}
		
		
		
	}
	
	public boolean chocasteCon(Obstaculo obstaculo) { // funcion que detecta si mikasa choco con obstaculos
		return (this.x + this.radio > obstaculo.getX() - obstaculo.getAncho() / 2) &&
				(this.x - this.radio < obstaculo.getX() + obstaculo.getAncho() / 2) &&
				(this.y + this.radio > obstaculo.getY() - obstaculo.getAlto()/2)&&
				(this.y - this.radio < obstaculo.getY() + obstaculo.getAlto() / 2); 
	}
	
	public boolean tomaSuero(Suero suero, int dist) { // funcion que detecta si mikasa toma el suero
		return (this.x - suero.getX()) * (this.x - suero.getX()) + (this.y - suero.getY()) * (this.y - suero.getY()) < dist * dist;
		}
	public void seTransforma() {
		this.transformada = true;
	}
	public void seDestransforma() {
		this.transformada = false;
	}
	
	
	
	
	
	public void chocaEntorno() { // choca contra las paredes
		
		if (this.y <= this.radio ) {
			this.y = this.radio;
		}
		
		if (this.y >= 720 - this.radio) {
			this.y = 720 - this.radio;
		}
		
		if (this.x <= this.radio ) {
			this.x = this.radio;
		}
		
		if (this.x >= 1024 - this.radio) {
			this.x = 1024 - this.radio;
		}
		
	}
	
	




}