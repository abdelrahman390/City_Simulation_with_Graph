package service;

import City.City;
import Model.Graph;
import Model.Node;

import static java.lang.Math.sqrt;

public class CalcEdgeWeight {
    public double calcWeight(Node n1, Node n2){
        double weight = 0;

        weight = sqrt(Math.pow(n2.getX() - n1.getX(), 2) + Math.pow(n2.getY() - n1.getY(), 2));

        return weight;
    }
}
