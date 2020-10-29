package game_objects;

import java.awt.Graphics2D;

/**
 * This interface implements the draw and update methods to the objects.
 * @author Victor Castrillo
 *
 */
public interface Drawable {
	public void draw(Graphics2D g);
	public void update(double delta);
}
