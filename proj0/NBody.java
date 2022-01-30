/**
 * Simulate a sets of planets
 * @author RicardoAGu
 */
public class NBody {
    /**
     * Read the radius of the system
     * @param fileName the name of the .txt file which contains information of planets
     */
    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    /**
     * Read the information of planets and create corresponding Planet instances
     * @param fileName the name of the .txt file which contains information of planets
     */
    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int num = in.readInt();
        Planet[] galaxy = new Planet[num];
        in.readDouble();
        for (int i = 0; i < num; i++) {
            galaxy[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return galaxy;
    }

    /**
     * Main method. Draw the universe in its starting position
     */
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] galaxy = readPlanets(filename);
        double[] xForces = new double[galaxy.length];
        double[] yForces = new double[galaxy.length];
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0,0, "images/starfield.jpg");
        for (Planet p : galaxy) {
            p.draw();
        }
        StdDraw.enableDoubleBuffering();
        while(T > 0) {
            T = T - dt;
            for (int i = 0; i < galaxy.length; i++) {
                xForces[i] = galaxy[i].calcNetForceExertedByX(galaxy);
                yForces[i] = galaxy[i].calcNetForceExertedByY(galaxy);
            }
            StdDraw.clear();
            StdDraw.picture(0,0, "images/starfield.jpg");
            for (int i = 0; i < galaxy.length; i++) {
                galaxy[i].update(dt, xForces[i], yForces[i]);
                galaxy[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", galaxy.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : galaxy) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }
    }
}
