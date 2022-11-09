package juego;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	private double x;
	private double y;
	private double velocidad;
	private double angulo;
	private int radio;
	Image img1;

	
	public Proyectil(double x, double y, double velocidad, double angulo, int radio) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.radio = radio;
		this.img1 = Herramientas.cargarImagen("proyectil.png");
		
	}
	
	public void dibujar(Entorno e) {
		//e.dibujarCirculo(x, y, 2 * radio, Color.pink);
		e.dibujarImagen(img1, this.x, this.y, this.angulo, 0.2);
	}

	public void mover() {
		y += velocidad * Math.sin(angulo);
		x += velocidad * Math.cos(angulo);
	}
	
	
	public boolean chocasteCon(Entorno e) { // choca contra las paredes
		return x <= radio || y <= radio || x >= e.ancho() - radio|| y >= e.alto() - radio;		
	}
	
	
	public boolean chocasteCon(Obstaculo obstaculo) { // funcion que detecta si proyectil choco con obstaculos
		
		return (this.x + this.radio > obstaculo.getX() - obstaculo.getAncho() / 2) &&
				(this.x - this.radio < obstaculo.getX() + obstaculo.getAncho() / 2) &&
				(this.y + this.radio > obstaculo.getY() - obstaculo.getAlto()/2)&&
				(this.y - this.radio < obstaculo.getY() + obstaculo.getAlto() / 2);
		}
	
	public boolean mataTitan(double titanX, double titanY, double dist) {
		return (this.x - titanX) * (this.x - titanX) + (this.y - titanY) * (this.y - titanY) < dist * dist;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public double getAngulo() {
		return angulo;
	}

	public int getRadio() {
		return radio;
	}
		

}
