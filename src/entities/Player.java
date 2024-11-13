package entities;

import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	KeyHandler keyH;
	GamePanel gamePanel;

	public Player(GamePanel gamePanel, KeyHandler keyH) {
		this.gamePanel = gamePanel;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		this.x = 100;
		this.y = 300;
		this.speedX = 4.0f;
		this.speedY = 0.0f;
		this.gravity = 0.5f;
		this.jump = -12;
		direction = "right";

	}

	public void getPlayerImage() {

	}

	public void update() {
		if (keyH.left)
		{
			x -= speedX;
			jumping();
		} 
		if (keyH.right )
		{
			x += speedX;
			jumping();
		} 
		
			jumping();
		
		speedY += gravity;
		y += speedY;
		
		if(y >= 300) {
			y = 300;
			speedY = 0;
			isOnFloor = true;
		}

//		spriteCounter++;
//		if (spriteCounter > 14) {
//			if (spriteNum == 1) {
//				spriteNum = 2;
//			} else if (spriteNum == 2) {
//				spriteNum = 1;
//			}
//			spriteCounter = 0;
//		}
	}

	public void jumping() {
		if (keyH.up && isOnFloor) {
			speedY += jump;
			isOnFloor = false;
		}
	}

	public void draw(Graphics2D g2) {
		g2.fillRect((int) x, (int) y, gamePanel.tileSize, gamePanel.tileSize);
	}

}
