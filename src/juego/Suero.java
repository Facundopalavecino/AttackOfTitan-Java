package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Suero {

	private double x;
	private double y;
	private int radio;
	Image img1;
	
	public Suero(double x, double y,int radio) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.img1 = Herramientas.cargarImagen("suero.png");
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


	public void dibujar(Entorno e) {
		//e.dibujarCirculo(x, y, 2 * radio, Color.green);
		e.dibujarImagen(img1, this.x, this.y, Math.toRadians(0) , 0.03);
	}

	

}
