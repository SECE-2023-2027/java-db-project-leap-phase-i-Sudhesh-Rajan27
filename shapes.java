package javaa;
import java.util.*;
public class shapes {
	public double area(double l,double b) 
	{
		return l*b;
	}
	public double area(double r) {
		return 3.14*r*r;
	}
	public double area(double a,char c) {
		return a*a;
	}
	public static void main(String Args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Side of square");
		double a = in.nextDouble();
		shapes all = new shapes();
		System.out.println(all.area(a,'c'));
		System.out.println("Side of rectangle");
		double l = in.nextDouble();
		double b = in.nextDouble();
		System.out.println(all.area(l,b));
		System.out.println("Radius of circle");
		double r = in.nextDouble();
		System.out.println(all.area(r));
		in.close();
	}
}

