import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.Sys;

import java.nio.FloatBuffer;
import java.util.Random;


public class CoolTreesGenerator {
	
	public CoolTreesGenerator() {
		
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle("Cool Trees Generator");
			Display.create();
			System.out.println("OpenGL Version: " + GL11.glGetString(GL11.GL_VERSION));
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		// Initialization for openGL, using gluPerspective
		GL11.glMatrixMode(GL11.GL_PROJECTION );
		GL11.glLoadIdentity();
		GLU.gluPerspective(60f, 800f / 600f, 0.1f, 100.0f);
		GLU.gluLookAt(5f, 5f, 5f, 0f, 1.5f, 0f, 0f, 1f, 0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW );
		GL11.glShadeModel(GL11.GL_SMOOTH);

		// Generate tree data to be used in display loop
		Plant plant = new Plant();
		plant.makeBranches( new Vector3f( 0, 0, 0), new Vector3f( 0, 1.5f, 0), 0);
		
		// For rotation
		float speed = 0.0f;
		float rotate = 0.0f;
		
		// Display loop
		while(!Display.isCloseRequested()) {
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
			GL11.glTranslatef( 0, 0, speed);
			GL11.glRotated( rotate, 0, 1, 0);

			//3D Plane
			GL11.glColor3f(0.05f, 0.05f, 0.05f);
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex3f(-10f, -0.01f, -10f);
			GL11.glVertex3f(-10f, -0.01f, 10f);
			GL11.glVertex3f(10f, -0.01f, 10f);
			GL11.glVertex3f(10f, -0.01f, -10f);
			GL11.glEnd();

			//Grid on the plane
			GL11.glBegin(GL11.GL_LINES);
			for(int i=-10;i<=10;i++) {
			    if (i==0) { GL11.glColor3f( 1f, 0.2f, 0.0f); } else { GL11.glColor3f( 0f, 0.2f, 0.4f); };
			    GL11.glVertex3f(i,0,-10);
			    GL11.glVertex3f(i,0,10);
			    if (i==0) { GL11.glColor3f(0.2f, 0.8f, 1f); } else { GL11.glColor3f( 0f, 0.2f, 0.4f); };
			    GL11.glVertex3f(-10,0,i);
			    GL11.glVertex3f(10,0,i);
			}
			GL11.glEnd();
			
			// Display the branches that we created
			plant.displayBranches();
			
			// Section dedicated to key commands
			if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
				speed = speed + 0.01f;
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
				speed = speed - 0.01f;
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				rotate = rotate + 0.01f;
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				rotate = rotate - 0.01f;
			}
			
			while (Keyboard.next()) {
				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
					speed = 0f;
					rotate = 0f;
				}
								
				if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
					//System.out.println();
					GL11.glDeleteLists(1, 1);
					plant = new Plant();
					plant.makeBranches( new Vector3f( 0, 0, 0), new Vector3f( 0, 1.5f, 0), 0);	
				}
				
				if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
					speed = 0f;
					rotate = 0f;
					Plant.ITER_MAX = 6;
					GL11.glLoadIdentity();
				}
				
				if (Keyboard.isKeyDown(Keyboard.KEY_EQUALS)) {
					System.out.println(String.valueOf(Plant.ITER_MAX));
					if(Plant.ITER_MAX < 12) {
						Plant.ITER_MAX = Plant.ITER_MAX + 1;
					}
				}
				
				if (Keyboard.isKeyDown(Keyboard.KEY_MINUS)) {
					System.out.println(String.valueOf(Plant.ITER_MAX));
					Plant.ITER_MAX = Plant.ITER_MAX - 1;
				}
			}	
			
			// Update the display and run at 60 fps
			Display.update();
			Display.sync(60);	
		}
		
		// Release display resources
		Display.destroy();
	}
	
	public static void main(String[] args) {
		System.out.println("Version is " + Sys.getVersion());
		new CoolTreesGenerator();
	}
}