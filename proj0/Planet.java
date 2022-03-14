import java.net.DatagramPacket;

/**
 * @author Kinvy
 * @version 1.0.0
 * @className Planet
 * @description
 * @date 2022-03-13
 */
public class Planet {
    public double xxPos;       // Its current x position
    public double yyPos;       // Its current y position
    public double xxVel;       // Its current velocity in the x direction
    public double yyVel;       // Its current velocity in the y direction
    public double mass;       //Its mass
    public String imgFileName;

    public final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double mass, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = mass;
        this.imgFileName = img;
    }

    public Planet(Planet p) {

    }

    /**
     * cal r
     * @param p
     * @return r
     */
    public double calcDistance(Planet p) {
        double r = 0;
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        r = Math.sqrt(dx*dx + dy*dy);
        return r;
    }

    /**
     * cal F
     * @param p
     * @return F
     */
    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        double f = (G * this.mass * p.mass) / (r * r);
        return  f;
    }

    /**
     * cal Fx
     * @param p
     * @return Fx
     */
    public double calcForceExertedByX(Planet p) {
        double fX = 0;
        double r = this.calcDistance(p);
        double f = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        fX = (f * dx) / r;
        return fX;
    }

    /**
     * cal Fy
     * @param p
     * @return fY
     */
    public double calcForceExertedByY(Planet p) {
        double fY = 0;
        double r = this.calcDistance(p);
        double f = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        fY = (f * dy) / r;
        return fY;
    }

    // cal Net Fx, Fy
    public double calcNetForceExertedByX(Planet[] planets) {
        double netFX = 0;
        for (Planet planet : planets) {
            if (this.equals(planet)) {
                continue;
            }
            netFX += this.calcForceExertedByX(planet);
        }
        return netFX;
    }
    public double calcNetForceExertedByY(Planet[] planets) {
        double netFY = 0;
        for (Planet planet : planets) {
            if (this.equals(planet)) {
                continue;
            }
            netFY += this.calcForceExertedByY(planet);
        }
        return netFY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos +  dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" +  this.imgFileName);
        //StdDraw.show();
    }

}
