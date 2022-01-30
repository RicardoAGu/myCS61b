import java.util.Objects;

/** definition of Planets
 * @author RicardoAGu
 */
public class Planet {

    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;

    /** the first constructor
     * @param xP Its current x position
     * @param yP Its current y position
     * @param xV Its current velocity in the x direction
     * @param yV Its current velocity in the y direction
     * @param m Its mass
     * @param img The name of the file that corresponds
     *            to the image that depicts the planet (for example, "jupiter.gif")
     */
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** the first constructor
     * @param p An existed planet instance
     */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /** Calculate the distance between self and input planet
     * @param p An existed planet instance
     */
    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow(p.xxPos - xxPos, 2) + Math.pow(p.yyPos - yyPos, 2));
    }

    /** Calculate the force exerted on this planet by the given planet
     * @param p An existed planet instance
     */
    public double calcForceExertedBy(Planet p){
        return 6.67 * Math.pow(10, -11) * mass * p.mass / calcDistance(p) / calcDistance(p);
    }

    /** Calculate the force exerted on this planet in the X direction by the given planet
     * @param p An existed planet instance
     */
    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    /** Calculate the force exerted on this planet in the Y direction by the given planet
     * @param p An existed planet instance
     */
    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    /** Calculate the net force exerted on this planet in the X direction by all the given planets
     * @param galaxy All existed planet instances
     */
    public double calcNetForceExertedByX(Planet[] galaxy) {
        double netForceX = 0.0;
        for (Planet p : galaxy) {
            if (! Objects.equals(this, p)) {
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    /** Calculate the net force exerted on this planet in the X direction by all the given planets
     * @param galaxy All existed planet instances
     */
    public double calcNetForceExertedByY(Planet[] galaxy) {
        double netForceY = 0.0;
        for (Planet p : galaxy) {
            if (! Objects.equals(this, p)) {
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    /** Calculate the change in the planet’s velocity and position during time t caused by forces
     * @param t A small period of time
     * @param forceX The net force exerted on this planet in the X direction
     * @param forceY The net force exerted on this planet in the Y direction
     */
    public void update(double t, double forceX, double forceY) {
        double aX, aY;
        aX = forceX / mass;
        aY = forceY / mass;
        xxVel += aX * t;
        yyVel += aY * t;
        xxPos += xxVel * t;
        yyPos += yyVel * t;
    }

    /** Draw this planet
     */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
