package main;

import java.awt.Color;
import java.awt.Graphics;

class Figura {
    protected double x;
    protected double y;
    protected Color boja;
    protected boolean aktivna;
    protected char oznaka;

    public char getOznaka() {return oznaka; }
    
    public Figura(double d, double e, Color color) {
        this.x = d;
        this.y = e;
        this.boja = color;
        this.aktivna = true;
    }

    public void draw(Graphics g) {}

    public void pomjeri(double dx, double dy) {
    	this.x += dx;
        this.y += dy;
    }

    public void destroy() {
    	aktivna = false;
    }
}