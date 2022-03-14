/**
 * @author Kinvy
 * @version 1.0.0
 * @className NBody
 * @description
 * @date 2022-03-13
 */
public class NBody {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("pls input 3 numbers");
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        double time = 0;
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];


        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-2.50e+11, 2.50e+11);
        /* Clears the drawing window. */
        while (time < T) {
            StdDraw.clear();

            for (int i = 0; i < planets.length; i++) {
                xForces[i] =  planets[i].calcNetForceExertedByX(planets);
                yForces[i] =  planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0,"./images/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }


    }

    public static double readRadius(String filename) {
        In in = new In(filename);

        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        //String thirdItemInFile = in.readString();
        //String fourthItemInFile = in.readString();
        //double fifthItemInFile = in.readDouble();

        return  secondItemInFile;
    }

    public static Planet[] readPlanets(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        int size = in.readInt();
        Planet[] planets = new Planet[size];
        double secondItemInFile = in.readDouble();

        for (int i = 0; i < size; i++) {
            Planet planet = new Planet(in.readDouble(),     //xPosition
                    in.readDouble(),    //yPosition
                    in.readDouble(),    //xVelocity
                    in.readDouble(),    //yVelocity
                    in.readDouble(),    //mass
                    in.readString());   //name
            planets[i] = planet;
        }
        return planets;
    }
}
