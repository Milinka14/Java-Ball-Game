package main;

import java.awt.Color;

public abstract class AktivnaFigura extends Figura implements Runnable{

	protected int perioda;
	
	public AktivnaFigura(double d, double e, Color color, int ms) {
		super(d, e, color);
		perioda = ms;
	}
	
	abstract void pomjeri();	
	
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			if (!aktivna) {
				break;
			}
			try {
				pomjeri();
				Thread.sleep(perioda);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
	}
	
}
