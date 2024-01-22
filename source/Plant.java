import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.vector.Vector3f;


public class Plant {
	
	private final int[] SLICES = {24, 20, 16, 12, 8, 4, 4, 4, 4, 4, 4, 4};
	private final int[] STACKS = {24, 20, 16, 12, 8, 4, 4, 4, 4, 4, 4, 4};
	private final float STRT_RADIUS = 0.15f;
	private final float END_RADIUS = 0.1f;
	private final float FACTORH = 3/4f;
	private final float FACTORWIDTH = 2/3f;
	private final float CHERRY_SIZE = 1/50f;

	static int ITER_MAX = 6;
	static boolean toggle;
	
	private Random rand = new Random();
	private ArrayList<Branch> branchData;
	
	public Plant(){
		toggle = false; 
		branchData = new ArrayList<Branch>();
	}
	
	public void makeBranches(Vector3f ori, Vector3f end, int iter_depth) {
		Vector3f savestate = new Vector3f(ori.x, ori.y, ori.z);
		if (iter_depth < ITER_MAX) {
			if (iter_depth != 0) {
				branchData.add(new Branch(ori, end, iter_depth));
				savestate = new Vector3f(end.x, end.y, end.z);
				end = nextVector(ori, end, iter_depth + 1);
			}
			
			int times = Math.round((float) Math.abs(rand.nextGaussian()) + 2);

			if (times < 1) {
				times = 1;
			}
			
			for (int i = 1; i <= times; i++) {
				makeBranches(savestate, end, iter_depth + 1);
			}	
		}
	}
	
	public void displayBranches() {
		if (!toggle) {
			for (Branch br : branchData) {
				makeCylinder(br.getOrigin(), br.getEnd(), br.getIter(), br.getColor());
			}
			GL11.glEndList();
			toggle = true;
		} else {
			GL11.glCallList(1);
		}
	}
	
	public void makeCylinder(Vector3f a, Vector3f b, int iteration, Color color) {

		float[] colorf = color.getComponents(null);
		
		// Calculate the new ratio based on iteration
		double ratio = Math.pow( FACTORWIDTH, iteration );
		
		// Calculate difference
		float vx = b.x - a.x;
		float vy = b.y - a.y;
		float vz = b.z - a.z;
		
		// Handle the degenerate case of z1 == z2 with an approximation
		if(vz == 0) {
		    vz = (float) 0.0001;
		}
		
		// V is v vector magnitude, finish calculations
		float v = (float) Math.sqrt( vx*vx + vy*vy + vz*vz );
		float angle = (float) (57.2957795*Math.acos( vz/v ));
		if (vz < 0.0)
		    angle = -angle;
		float rx = -vy*vz;
		float ry = vx*vz;
		
		int list = GL11.glGenLists(1);
		GL11.glNewList(list, GL11.GL_COMPILE);
		GL11.glPushMatrix();
		GL11.glColor3f(colorf[0], colorf[1], colorf[2]);
		GL11.glTranslated( a.x, a.y, a.z);
		GL11.glRotated( angle, rx, ry, 0.0);
		Cylinder cyl = new Cylinder();
		cyl.draw(
			(float) (ratio * STRT_RADIUS), 
			(float) (ratio * END_RADIUS), 
			(float) v, 
			SLICES[iteration], 
			STACKS[iteration]
		);
		GL11.glPopMatrix();
	}
	
	public Vector3f nextVector(Vector3f previous, Vector3f current, int iteration) {
		double rx = ((current.x - previous.x) * FACTORH);
		double ry = ((current.y - previous.y) * FACTORH);
		double rz = ((current.z - previous.z) * FACTORH);
		double rad = Math.sqrt((rx*rx) + (ry*ry) + (rz*rz));
		
		double rlat = -1 * Math.abs(rand.nextGaussian());
		if (rlat < -2d) {
			rlat = -2d;
		}

		double lat = (2 + rlat) * 45;
		if (lat > 90d) {
			lat = 90d;
		}

		lat = lat * (Math.PI / 180d);
		double lon = rand.nextFloat() * 2 * Math.PI;
		
		float x = (float) (rad * Math.sin(lat) * Math.sin(lon));
		float z = (float) (rad * Math.sin(lat) * Math.cos(lon));
		float y = (float) (rad * Math.cos(lat));
			    
	    Vector3f nextVect = new Vector3f(x+current.x, y+current.y, z+current.z);
	    return nextVect;
	}
}