public class Planet{
	public double x;
	public double y;
	public double xVelocity;
	public double yVelocity;
	public double mass;
	public String img;
	public double xNetForce;
	public double yNetForce;
	public double xAccel;
	public double yAccel;
	public Planet(double x1, double y1, double xVelocity1, double yVelocity1, double mass1, String img1){
		x = x1;
		y = y1;
		xVelocity = xVelocity1;
		yVelocity = yVelocity1;
		mass = mass1;
		img = img1;	
	}
	public double calcDistance(Planet p) {
		return Math.sqrt(((x-p.x)*(x-p.x))+((y-p.y)*(y-p.y)));
	}
	public double calcPairwiseForce(Planet p) {
		return 6.67e-11*mass*p.mass/(calcDistance(p)*calcDistance(p));
	}
	public double calcPairwiseForceX(Planet p) {
		return calcPairwiseForce(p)*(p.x - x)/calcDistance(p);
	}
	public double calcPairwiseForceY(Planet p) {
		return calcPairwiseForce(p)*(p.y - y)/calcDistance(p);
	}
	public void setNetForce(Planet[] args){
		
		xNetForce = 0;
		yNetForce = 0;
		for(int i = 0; i < args.length; i++){
			if (args[i] == this){
			} else {
				xNetForce = xNetForce + calcPairwiseForceX(args[i]);
				yNetForce = yNetForce + calcPairwiseForceY(args[i]);
			}
		}
	}
	public void update(double dt){
		xAccel = xNetForce/mass;
		yAccel = yNetForce/mass;
		xVelocity = xVelocity + dt*xAccel;
		yVelocity = yVelocity + dt*yAccel;
		x = x + dt*xVelocity;
		y = y + dt*yVelocity;
	}
	public void draw(){
		String img1 = "images/"+img;
		StdDraw.picture(x, y, img1);
	}
}

