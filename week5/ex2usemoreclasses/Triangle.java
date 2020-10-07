package exercises.ex2usemoreclasses;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.Math.sqrt;

/*
      A class for a Triangle in 3D
      Should use Point class and Herons formula

      NOTE: No IO here, this is just the logical concept.

      To test run Ex2TestPointTriangle

*/
public class Triangle {
    Point p1;
    Point p2;
    Point p3;


    Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public double area() {
        double A = p1.distance(p3),
                B = p3.distance(p2),
                C = p2.distance(p1),
                S = (A + B + C) / 2;

        double area = sqrt(S * (S - A) * (S - B) * (S - C));
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) o;

        return (this.p1 == triangle.p1) &&
                (this.p2 == triangle.p2) &&
                (this.p3 == triangle.p3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2, p3);
    }
}

