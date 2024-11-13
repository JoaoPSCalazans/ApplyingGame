package entities;

import java.awt.image.BufferedImage;

public class Entity {
	
	public float x
				,y
				,speedX
				,speedY
				,gravity
				,jump;
	boolean isOnFloor = true;
	
	public BufferedImage up1,down1,left1,left2,right1,right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	

}
