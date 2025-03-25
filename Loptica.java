package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

public class Loptica extends AktivnaFigura{

	int precnik;
	int updateCount = 0;
    private double velocityX, velocityY;
    
    List<Cigla> cigle;
    List<Igrac> igraci;
    
    Random random = new Random();
	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	Scena scena;
	
    //Random pocetna = new Random();
    
    public Loptica(Scena scena,double d, double e, int precnik,List<Cigla> cigle) {
		super(d, e, Color.GREEN,10);
		oznaka = 'L';
		this.scena = scena;
		this.precnik = precnik;
		this.cigle = cigle;
		
		velocityX = random.nextDouble() * 2.0  - 1.0; // !!!!!!!!!!!!!!! RANDOM
		velocityY = random.nextDouble() - 1.0;// !!!!!!!!!!!!!!! RANDOM
		System.out.println("Initial velocityX: " + velocityX + ", velocityY: " + velocityY);
	}

	@Override
	synchronized void pomjeri() {
		super.pomjeri(velocityX, velocityY);
		System.out.println("Current position: x=" + x + ", y=" + y);
		
		if (updateCount++ == 100 && velocityX < 6 && velocityY < 6 && velocityY > -6 && velocityX >- 6) {
			updateCount = 0;
			velocityX *= 1.1;
			velocityY *= 1.1;
		}
		
		if (x - precnik / 2 < 0 || x + precnik / 2 > scena.getWidth() ) {
	        velocityX = -velocityX;
	    } else if (y  - precnik / 2 < 0) {
	        velocityY = -velocityY;
	    } else if (y + precnik / 2 - 20> scena.getHeight()) {
	        destroy();
	    }
		
		for (Cigla c : cigle) {
			/*
            if (!c.pogodjena && getBounds().intersects(c.getBounds())) {
                c.pogdjen();
                velocityY = -velocityY;
                break;
            }*/
            if (!c.pogodjena) {
                if (this.y >= c.y - c.getVisina()/2 && this.y  <= c.y + c.getVisina()/2) {
                    if (this.x + (this.precnik/2) >= c.x - c.getSirina()/2 && this.x<c.x-c.getSirina()/2) {
                    	velocityX = -velocityX;
                        c.pogdjen();
                    }
                    if (this.x - (this.precnik/2) <= c.x + c.getSirina()/2 && this.x>c.x+c.getSirina()/2) {
                    	velocityX = -velocityX;
                        c.pogdjen();
                    }
                }
                if (this.x>= c.x - c.getSirina()/2 && this.x<= c.x+c.getSirina()/2) {
                    if (this.y+(this.precnik/2)>= c.y - c.getVisina()/2 && this.y < c.y-c.getVisina()/2) {
                    	velocityY = -velocityY;
                        c.pogdjen();
                    }
                    if (this.y-(this.precnik/2)<= c.y + c.getVisina()/2 && this.y > c.y + c.getVisina()/2) {
                    	velocityY = -velocityY;
                        c.pogdjen();
                    }
                }
            }
        }
		scena.kolizijaSaIgracem(this);
	}
	
	void promjeniY() {
		velocityY = -velocityY;
	}
	
	void promjeniX() {
		velocityX = -velocityX;
	}	
	
	public void draw(Graphics g) {
	        g.setColor(boja);
	        g.fillOval((int)x - precnik/2, (int)y - precnik / 2, precnik, precnik);
	    }
	
}
