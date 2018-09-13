public class NBody{
	public static void main(String[] args){
		StdAudio.play("audio/2001.mid");
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		In x = new In(filename); 
		int N = x.readInt();
		
		double R = x.readDouble();
		
		double Time = 0;
		StdDraw.setCanvasSize();
		StdDraw.setXscale(-R, R);
		StdDraw.setYscale(-R, R);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		
		Planet[] lst = new Planet[N];
		for (int i = 0; i < N; i++){
			lst[i] = getPlanet(x);
		}
		
		for (int i = 0; i < N; i++){
			lst[i].draw();	
		}
		while (Time < T){
			for (int i = 0; i < N; i++){
				lst[i].update(dt);
				lst[i].setNetForce(lst);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (int i = 0; i < N; i++){
				lst[i].draw();	
			}
			StdDraw.show(10);
			Time = Time+dt;
		}
		
		StdOut.printf("%d\n", N);
		StdOut.printf("%.2e\n", R);
		for (int i = 0; i < N; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
    			lst[i].x, lst[i].y, lst[i].xVelocity, lst[i].yVelocity, lst[i].mass, lst[i].img);
		}
	}
	
	public static Planet getPlanet(In s){
		double x = s.readDouble();
		double y = s.readDouble();
		double xVelocity = s.readDouble();
		double yVelocity = s.readDouble();
		double mass = s.readDouble();
		String gif = s.readString();
		return new Planet(x, y, xVelocity, yVelocity, mass, gif);
	}

}