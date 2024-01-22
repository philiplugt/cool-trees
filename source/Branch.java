import java.awt.Color;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;


public class Branch {

	private Vector3f origin;
	private Vector3f end;
	private int iteration;
	private Color color;
	
	public Branch(Vector3f origin, Vector3f end, int iteration) {
		this.origin = new Vector3f( origin.x, origin.y, origin.z );
		this.end = new Vector3f( end.x, end.y, end.z );
		this.iteration = iteration;
		Random rand = new Random();
		color = new Color(rand.nextInt(200) , rand.nextInt(200), rand.nextInt(200));
	}
	
	public Vector3f getOrigin() {
		return origin;
	}
	
	public Vector3f getEnd() {
		return end;
	}
	
	public int getIter() {
		return iteration;
	}
	
	public Color getColor() {
		return color;
	}
}