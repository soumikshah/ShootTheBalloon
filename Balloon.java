package homework1;

import java.awt.Color;
import java.util.ArrayList;

public class Balloon {

	static int balloonCounts = 0;
	
	private final int id;
	
	private final int R;
	private final int G;
	private final int B;

	private int x;
	private int y;

	private double vx;
	private double vy;
	
	private int radius;

	private boolean exist;

	/* constructors */

	Balloon() {
		R = randomColor();
		G = randomColor();
		B = randomColor();
		x = getRandomPosition();
		y = getRandomPosition();
		vx = randomVelocity();
		vy = randomVelocity();
		radius = randomRadius();
		exist = true;
		id = balloonCounts;
		balloonCounts++;
	}

	/* accessors */
	
	public int getB() {
		return randomColor() ;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		String buffer = "";
		buffer += "id = " + id + " ";
		buffer += "(x,y) = (" + x + "," + y + ") ";
		buffer += "(vx,vy) = (" + vx + "," + vy + ") ";
		buffer += "(R,G,B) = (" + R + "," + G + "," + B + ")";
		return buffer;
	} 
	
	 int randomRadius() {
		// TODO Auto-generated method stub
		return (int)(Math.random()*50);
	}

	public boolean isExist() {
		return exist == true;
	}
	
	/* mutators */
	
	public void drift(int t) {
		double dx = (int) (vx * t);
		double dy = (int) (vy * t);
		x +=dx;
		y +=dy;
		//System.out.println("ok");
	}
	
	public void drift() {
		final int T = 1;
		drift(T);
		//System.out.println("I am fine");
	}
	
	public void drift(ArrayList<Balloon> balloons) {
		// 1. update the velocity based on the distance between 
		// itself and the neighbors... (bonus come up with a complex
		// collision model -- challenge yourself!)
		
		// for each neighbor:
		//    compute distance between my center (x,y) and my
		//    neighbor's center (x,y), if distance <= r1 + r2
		//    then update my velocity: vx = vx * -1, vy = vy * -1
		//    and done checking
		
		for (Balloon neighbor : balloons) {
			if (neighbor == this) {
				continue;
			}
			// do your collision math here
		}
		//System.out.println("Hello");
		// 2. call drift()
		drift();
	}		

	private double randomVelocity() {
		return Math.random() * 10;
	}

	int getRandomPosition(){
		return (int)(Math.random()*200); 
		
	}

	private int randomColor() {
		return (int) (Math.random() * 255);
	}

	/* main method */
	
//	public static void main(String[] args) {
	//	final int N = 7; 
		//Balloon[] balloons = new Balloon[N]; 
		
	//	for (int i = 1; i < N; i++) {
	//		balloons[i] = new Balloon();
	//	}
		
	//	try {
	//		balloons[N].drift();
	//	} catch(Exception ex) {
			// this time: do nothing!
	//		System.out.println("hey dude, can't drift this");
	//		ex.printStackTrace();
	//	}
		
	//	try {
	//		System.out.println(balloons[N]);
	//	} catch(Exception ex) {
	//		System.out.println("hey dude, can't print this");
	//	}
	//}

}
