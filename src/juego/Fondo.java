package juego;

import java.awt.Image;
import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	private double x;
	private double y;
	private Image imgFondo;
	double anguloFondo;
	private Clip sonido; 
	
	public Fondo(double x, double y) {
		this.x = x;
		this.y = y;
		imgFondo = Herramientas.cargarImagen("fondo.jpg");
		anguloFondo=0;
		this.sonido = Herramientas.cargarSonido("Expecting the Unexpected.WAV"); 
		
	}
	
	public void dibujarse(Entorno entorno)
	{
		
		entorno.dibujarImagen(imgFondo, this.x, this.y, this.anguloFondo, 1);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public Clip getSonido() {
		return sonido;
	}
	
}
