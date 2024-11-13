package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entities.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	final int originalTileSize = 16;//16x16
	final int scala = 3;
	
	public final int tileSize = originalTileSize * scala;//48x48
	public final int maxColumns = 16;
	public final int maxRows = 12;
	final int screenWidth = maxColumns * tileSize;//768
	final int screenHeight = maxRows * tileSize;
	
	final int Fps = 60;
	boolean running = false;
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler(this);
	TileManager tileM = new TileManager(this);
	Player player = new Player(this,keyH);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));  
		this.setBackground(Color.blue);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/Fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long timer = 0;
		int frames = 0;
		long currentTime;
		
		running = true;
		
		while(running) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			while(delta >= 1) {
				update();
				delta--;
				frames++;
			}
			repaint();
			
			if(timer >= 1000000000) {
				System.out.println("Fps: "+frames);
				frames = 0;
				timer = 0;
			}
			
			try {
				Thread.sleep(5);
				Thread.yield();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void stopGameThread() {
		running = false;
	}
	
	public void update() {	
		player.update();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		tileM.draw((Graphics2D) g);
		player.draw((Graphics2D) g);
		g.dispose();	
	}

	

	

}
