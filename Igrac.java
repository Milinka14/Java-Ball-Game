package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Igrac extends Figura implements KeyListener{

	int sirina;
	int visina;
	Scena scena;
	public int getSirina() {
		return sirina;
	}

	public int getVisina() {
		return visina;
	}

	public void setSirina(int sirina) {
		this.sirina = sirina;
	}

	public void setVisina(int visina) {
		this.visina = visina;
	}
	List<Cigla> cigle;
	
	public Igrac(Scena scene, int x, int y, Color color, int sirina, int visina,List<Cigla> cigle) {
		super(x, y, color);
		this.sirina = sirina;
		this.visina = visina;
		this.scena=  scene;
		oznaka = 'I';
		this.cigle = cigle;
	}

	void ispaliLopticu() {
		Loptica nova = new Loptica(scena,x+50, y - 20 - 15, 18,cigle);
		scena.addFigure(nova);
	}
	
	public void draw(Graphics g) {
		g.setColor(boja);
        g.fillRect((int) x - sirina /2, (int) y - visina / 2, sirina, visina);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
        	if (x  - sirina /2> 0) {
                pomjeri(-10,0);	
        	}
            break;
        case KeyEvent.VK_RIGHT:
        	if (x  + sirina / 2 < scena.getWidth()) {
                pomjeri(10,0);	
        	}break;
    }
		
	}
	/*
	Rectangle getBounds() {
        return new Rectangle(x, y, sirina, visina);
	}*/
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
