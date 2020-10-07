package exercises.ex2usemoreclasses;

import exercises.ex1useaclass.Rational;
import jdk.jshell.spi.ExecutionControl;

import java.util.Objects;
import java.lang.Exception;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/*

  A class for points in 3D. Should be immutable

  NOTE: No IO here, this is just the logical concept.

  To test run Ex2TestPointTriangle

*/
public class Point {
    final int a;
    final int b;
    final int c;


    Point (int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    Point (Point o) {
            this.a = o.a;
            this.b = o.b;
            this.c = o.c;
    }

    double distance(Point o) {
        double x2 = this.a,
               x1 = o.a,
               y2 = this.b,
               y1 = o.b,
               z2 = this.c,
               z1 = o.c;



        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2-y1, 2) + Math.pow(z2-z1, 2));
    }


    @Override
    public boolean equals(Object o) {
       if(this == o)
           return true;

       if (o == null || getClass() != o.getClass()) {
            return false;
       }
       Point point = (Point) o;

       return (this.a == point.a) &&
              (this.b == point.b) &&
              (this.c == point.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}


