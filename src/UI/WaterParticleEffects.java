package UI;

import UI.UIElement;

import java.awt.*;
import java.util.Timer;

public class WaterParticleEffects extends UIElement {
    Particle[] particles;
    Timer physicsTimer = new Timer("aliveTimer");
    public WaterParticleEffects(int xPos, int yPos, int xSize, int ySize, int amountOfParticles) {
        super(xPos, yPos, xSize, ySize);
        particles = new Particle[amountOfParticles];
        for (int i = 0; i < particles.length; i++) {
            particles[i] = new Particle(getxPos(), getyPos(), getxSize(), getyPos(), 6, 30);
        }
    }

    @Override
    public void render(Graphics g) {
        for (Particle particle:
             particles) {
            particle.render(g);
        }
    }

    @Override
    public void update(double deltaTime) {
        for (Particle particle:
             particles) {
            particle.update(deltaTime);
        }
    }

    @Override
    public void onClicked() {

    }

    private class Particle extends  UIElement   {
        double xVelocity;
        double yVelocity;
        public Particle(int xPos, int yPos, int xSize, int ySize, double maxXvelocity, double yVelocity) {
            super(xPos, yPos, xSize, ySize);
            xVelocity = (Math.random()-0.5)*maxXvelocity;
            this.yVelocity = yVelocity;
        }

        @Override
        public void render(Graphics g) {
            g.fillRoundRect(getxPos(), getyPos(),
                    getxSize(), getySize(),
                    getxSize()/3, getySize()/3);
        }

        @Override
        public void update(double deltaTime) {
            yVelocity-=9.8*(deltaTime/1000);
            setyPos((int)(getyPos()+yVelocity));
            setxPos((int)(getxPos()+xVelocity));
        }

        @Override
        public void onClicked() {
            
        }
    }
}