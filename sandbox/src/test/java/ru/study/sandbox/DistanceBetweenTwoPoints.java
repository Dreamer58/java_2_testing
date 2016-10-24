package ru.study.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Андрей on 24.10.2016.
 */
public class DistanceBetweenTwoPoints {
    @Test
    public void distanceBetweenTwoSamePoints() {
        Point p1 = new Point(-5, 0);
        Point p2 = new Point(-5, 0);
        Assert.assertEquals(p1.distance(p1, p2), 0.0);
    }

    @Test
    public void distanceBetweenTwoAnotherPoints() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 100);
        Assert.assertEquals(p1.distance(p1, p2), 100.0);
    }

}
