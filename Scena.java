package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Scena extends Canvas {
    List<AktivnaFigura> figure = new ArrayList<>();
    List<Figura> statickeFigure = new ArrayList<>();
    List<Igrac> igraci = new ArrayList<>();
    List<Cigla> cigle = new ArrayList<>();
    
    private int width, height;

    Scena() {
        width = 790;
        height = 600;
        setSize(width, height);
        
        for (int i = 70; i <= 800; i += 130) {
            for (int j = 20; j <= 200; j += 60) {
                Cigla brick = new Cigla(this, i, j, 120, 50);
                figure.add(brick);
                cigle.add(brick);
            }
        }
        
        Igrac igrac = new Igrac(this, width / 2 - 25, height - 70, Color.BLUE, 100, 20, cigle);
        statickeFigure.add(igrac);
        igraci.add(igrac);
        
        Loptica lopta = new Loptica(this, 370, 470, 18, cigle);
        figure.add(lopta);
        
        addKeyListener(igrac);
        
        for (AktivnaFigura figura : figure) {
            new Thread(figura).start();
        }
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                igrac.ispaliLopticu();
            }
        });
        
        new Thread(new GameLoop()).start();
        
        setFocusable(true);
    }

    @Override
    synchronized public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, width, height);
        
        for (Figura figure : figure) {
            figure.draw(g);
        }
        for (Figura figure : statickeFigure) {
            figure.draw(g);
        }
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    synchronized public void addFigure(AktivnaFigura figura) {
        figure.add(figura);
        new Thread(figura).start();
    }

    public void kolizijaSaIgracem(Loptica lopta) {
        for (Igrac igrac : igraci) {
            double ballLeft = lopta.x - lopta.precnik / 2.0;
            double ballRight = lopta.x + lopta.precnik / 2.0;
            double ballTop = lopta.y - lopta.precnik / 2.0;
            double ballBottom = lopta.y + lopta.precnik / 2.0;

            double playerLeft = igrac.x - igrac.getSirina() / 2.0;
            double playerRight = igrac.x + igrac.getSirina() / 2.0;
            double playerTop = igrac.y - igrac.getVisina() / 2.0;
            double playerBottom = igrac.y + igrac.getVisina() / 2.0;

            boolean hitTop = ballBottom >= playerTop && ballTop < playerTop && ballRight > playerLeft && ballLeft < playerRight;
            boolean hitBottom = ballTop <= playerBottom && ballBottom > playerBottom && ballRight > playerLeft && ballLeft < playerRight;
            boolean hitLeft = ballRight >= playerLeft && ballLeft < playerLeft && ballBottom > playerTop && ballTop < playerBottom;
            boolean hitRight = ballLeft <= playerRight && ballRight > playerRight && ballBottom > playerTop && ballTop < playerBottom;

            if (hitTop || hitBottom) {
                lopta.setVelocityY(-lopta.getVelocityY());
                if (hitTop) lopta.y = playerTop - lopta.precnik / 2.0 - 1; 
                if (hitBottom) lopta.y = playerBottom + lopta.precnik / 2.0 + 1;
            } else if (hitLeft || hitRight) {
                lopta.setVelocityX(-lopta.getVelocityX());
                if (hitLeft) lopta.x = playerLeft - lopta.precnik / 2.0 - 1;
                if (hitRight) lopta.x = playerRight + lopta.precnik / 2.0 + 1;
            }
        }
    }

    private class GameLoop implements Runnable {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Frame frame = new Frame("Ball Game");
        Scena canvas = new Scena();
        frame.add(canvas);
        frame.setSize(800, 600);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}
