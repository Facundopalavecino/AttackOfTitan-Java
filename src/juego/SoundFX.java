package juego;
import javax.sound.sampled.Clip;
import entorno.Herramientas;

public class SoundFX {
	private Clip musica;
	private Clip proyectil;
	private Clip muerteTitan;
	private Clip muerteMikasa;
	private Clip suero;
	private Clip mikasaSuero;
	private Clip intro;
	private Clip batalla;
	private Clip ganar;
	private Clip perder;
	
	
	public SoundFX() {
		this.musica = Herramientas.cargarSonido("Expecting the Unexpected.WAV"); 
		this.proyectil = Herramientas.cargarSonido("explosionlarge2.WAV"); 
		this.muerteTitan = Herramientas.cargarSonido("titanmuere.WAV"); 
		this.muerteMikasa = Herramientas.cargarSonido("mikasamuere.WAV"); 
		this.suero = Herramientas.cargarSonido("suero.WAV"); 
		this.mikasaSuero = Herramientas.cargarSonido("mikasatomasuero.WAV"); 
		this.intro = Herramientas.cargarSonido("intro.WAV");
		this.batalla = Herramientas.cargarSonido("batalla.WAV"); 
		this.ganar = Herramientas.cargarSonido("ganar.WAV");
		this.perder = Herramientas.cargarSonido("perder.WAV");
	
	
	
	}



	public Clip getIntro() {
		return intro;
	}



	public void setIntro(Clip intro) {
		this.intro = intro;
	}



	public Clip getBatalla() {
		return batalla;
	}



	public void setBatalla(Clip batalla) {
		this.batalla = batalla;
	}



	public Clip getGanar() {
		return ganar;
	}



	public void setGanar(Clip ganar) {
		this.ganar = ganar;
	}



	public Clip getPerder() {
		return perder;
	}



	public void setPerder(Clip perder) {
		this.perder = perder;
	}



	public Clip getSuero() {
		return suero;
	}



	public void setSuero(Clip suero) {
		this.suero = suero;
	}



	public Clip getMikasaSuero() {
		return mikasaSuero;
	}



	public void setMikasaSuero(Clip mikasaSuero) {
		this.mikasaSuero = mikasaSuero;
	}



	public Clip getProyectil() {
		return proyectil;
	}

	public void setProyectil(Clip proyectil) {
		this.proyectil = proyectil;
	}



	public Clip getMuerteTitan() {
		return muerteTitan;
	}



	public void setMuerteTitan(Clip muerteTitan) {
		this.muerteTitan = muerteTitan;
	}



	public Clip getMusica() {
		return musica;
	}



	public void setMusica(Clip musica) {
		this.musica = musica;
	}
	
	public Clip getMuerteMikasa() {
		return muerteMikasa;
	}


	public void setMuerteMikasa(Clip muerteMikasa) {
		this.muerteMikasa = muerteMikasa;
	}
	
	
	
}
