package javaa;
import java.util.*;
class shapess {
	public double area(double l,double b) 
	{
		return l*b;
	}
	public double area(double r,char c) {
		return 3.14*r*r;
	}
	public double areas(double a) {
		return a*a;
	}
}
class square extends shapess{
	@Override
	public double areas(double a) {
		return a*a;
	}
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Sides of square");
		double a = in.nextDouble();
		square all = new square();
		System.out.println(all.areas(a));
		System.out.print(all.area(4.0,'c'));
		in.close();
	}
}