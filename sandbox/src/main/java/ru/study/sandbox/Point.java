package ru.study.sandbox;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by Андрей on 22.10.2016.
 */
public class Point {
    private double x, y;

    Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    void setX(double x){
        this.x = x;
    }

    void setY(double y){
        this.y = y;
    }

    double getX(){
        return x;
    }

    double getY(){
        return y;
    }

    public double distance(Point p1, Point p2){
        return sqrt(pow(p1.x-p2.x,2)+pow(p1.y-p2.y,2));
    }

}
