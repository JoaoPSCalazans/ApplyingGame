package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	
	final int originalTileSize = 16;//16x16
	final int scala = 3;
	
	final int tileSize = originalTileSize * scala;//48x48
	final int maxColumns = 16;
	final int maxRows = 12;
	final int screenWidth = maxColumns * tileSize;//768
	final int screenHeight = maxRows * tileSize;
	
	int x = 100;
	int y = 100;
	int speed = 4;
	
	final int Fps = 30;
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));  
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
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
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
			
		}
		
	}
	
	public void update() {	
		
		if(keyH.up == true) {
			y -= speed;
		}
		else if(keyH.down == true) {
			y += speed;
		}
		else if(keyH.left == true) {
			x -= speed;
		}
		else if(keyH.right == true) {
			x += speed;
		}
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setColor(Color.white);
		graphics2D.fillRect(x, y, tileSize,tileSize);
		
		graphics2D.dispose();	
	}

	

	

}
