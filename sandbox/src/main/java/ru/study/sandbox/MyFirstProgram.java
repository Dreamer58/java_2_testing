package ru.study.sandbox;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MyFirstProgram {
	public static void main (String[] args)	{
		Point p1 = new Point(-1,100);
		Point p2 = new Point(2, -1);
		Point p3 = new Point(5, -10);

		System.out.println("Расстояние между двумя точками, вычисленное при помощи функции distance: " + distance(p1, p2));

		System.out.println("Расстояние между двумя точками, вычисленное при помощи метода класса Point: "
				+ p1.distance(p1, p2));
		System.out.println("Расстояние между двумя точками, вычисленное при помощи метода класса Point: "
				+ p1.distance(p2, p3));
		System.out.println("Расстояние между двумя точками, вычисленное при помощи метода класса Point: "
				+ p3.distance(new Point(0,0), new Point(0, 5)));
	}

	public static double distance(Point p1, Point p2){
		return sqrt(pow(p1.getX()-p2.getX(),2)+pow(p1.getY()-p2.getY(),2));
	}
}
