package game_objects;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * This class gives the basic structure to all the objects that will be displayed in the screen.
 * @author Victor Castrillo
 *
 */
public abstract class GameObject {
	private int posX, posY, width, height;
	private BufferedImage sprite;
	
	public GameObject(int posX, int posY, int width, int height, String spriteName) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;

		try {
			URL url = this.getClass().getResource("/images/"+spriteName+".png");
			sprite = ImageIO.read(url);
		} catch(IOException e) {e.printStackTrace();}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getSprite() {
		return sprite;
	}
	
	public void setImage(String spriteName) {
		try {
			URL url = this.getClass().getResource("/images/"+spriteName+".png");
			sprite = ImageIO.read(url);
		} catch(IOException e) {e.printStackTrace();}
	}
}
