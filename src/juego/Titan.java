package juego;
import javax.sound.sampled.Clip;
//import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Titan {
	private double x;
	private double y;
	private double velocidad;
	private double angulo;
	private int radio;
	Image img1;
	private Clip sonido; 
	private Clip sonido2; 
	
	public Titan(double x, double y, double velocidad, double angulo, int radio) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.radio = radio;
		this.img1 = Herramientas.cargarImagen("titan.png");
		this.sonido = Herramientas.cargarSonido("titanvive.WAV"); 
		this.sonido2 = Herramientas.cargarSonido("titanmuere.WAV"); 

	}
	
	public void dibujar(Entorno e) {
		//e.dibujarCirculo(x, y, 2 * radio, Color.red);
		e.dibujarImagen(img1, this.x, this.y, this.angulo, 0.2);
	}

	public void mover() {
		y += velocidad * Math.sin(angulo);
		x += velocidad * Math.cos(angulo);
	}
	
	
	public boolean chocasteCon(Entorno e) { // choca contra las paredes
		return x <= radio || y <= radio || x >= e.ancho() - radio|| y >= e.alto() - radio;		
	}
	public boolean chocasteCon(Obstaculo obstaculo) {
		return (this.x + this.radio > obstaculo.getX() - obstaculo.getAncho() / 2) &&
				(this.x - this.radio < obstaculo.getX() + obstaculo.getAncho() / 2) &&
				(this.y + this.radio > obstaculo.getY() - obstaculo.getAlto()/2)&&
				(this.y - this.radio < obstaculo.getY() + obstaculo.getAlto() / 2); 
	}
	public boolean chocasteCon(Titan titan, double dist) {
		return (this.x - titan.getX()) * (this.x - titan.getX()) + (this.y - titan.getY()) * (this.y - titan.getY()) < dist * dist; 
	}

	
	public void cambiarTrayectoria() { // cambia de trayectoria
		angulo += Math.PI/2;
	}
	
	public boolean mataMikasa(double mikasaX, double mikasaY, double dist) {
		return (this.x - mikasaX) * (this.x - mikasaX) + (this.y - mikasaY) * (this.y - mikasaY) < dist * dist;
	}
	
	public boolean detectaMikasa(double mikasaX, double mikasaY, double dist) {
		return (this.x - mikasaX) * (this.x - mikasaX) + (this.y - mikasaY) * (this.y - mikasaY) < dist * dist;
		
	}
	public void persigueMikasa(double x2, double y2){
		this.angulo = Math.atan2(y2 - this.y, x2 - this.x);
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
	public Clip getSonido() {
		return sonido;
	}
	public Clip getSonido2() {
		return sonido2;
	}
		
}
