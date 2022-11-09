package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	Image img1;
	
	public Obstaculo(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.img1 = Herramientas.cargarImagen("obstaculo.png");
	}
	
	public void dibujar(Entorno e) {
		//e.dibujarRectangulo(x, y, ancho, alto, 0, Color.WHITE);
		e.dibujarImagen(img1, this.x, this.y, Math.toRadians(0), 0.25);
	}
	
	
	
	
	
	//geter y seter
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	

}
