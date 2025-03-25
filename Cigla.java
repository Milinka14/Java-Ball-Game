package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Cigla extends AktivnaFigura {

	int sirina;
	int visina;
	boolean pogodjena = false;
	Scena scena;
	public Cigla(Scena scena, int x, int y ,int sirina, int visina) {
		super(x, y, Color.RED,1000);
		this.sirina = sirina;
		this.visina = visina;
		this.scena = scena;
		oznaka = 'C';
	}
	
	void pogdjen() {
		this.pogodjena = true;
		boja = Color.GRAY;
	}
	
	int getSirina() {
		return sirina;
	}
	
	int getVisina() {
		return visina;
	}
	/*
	Rectangle getBounds() {
        return new Rectangle(x, y, sirina, visina);
	}*/
	
	@Override
	void pomjeri() {
		if (pogodjena) { 
			if (y + 5 > scena.getHeight()) {
				aktivna = false;
			}
			this.pomjeri(0, 5);
		}
	}
	
	public void draw(Graphics g) {
        g.setColor(boja);
        g.fillRect((int)x - sirina /2 , (int) y - visina / 2, sirina, visina);
    }
}
