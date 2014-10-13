/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdlab02;

import java.io.Serializable;

/**
 *
 * @author geank
 */
public class Punto implements Serializable{
    protected double x;
    protected double y;
    protected double z;
    protected double h;
    public Punto(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.h = 1;
    }

    public Punto(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.h = 1;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
    public String toString(){
        return String.valueOf(x) + ", " + String.valueOf(y) + ", " + String.valueOf(z);
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }
    public void setXYZ(double[][] a){
        if(a.length == 1){
            x = a[0][1];
            y = a[0][2];
            z = a[0][3];
            
        }
        else{
            System.err.println("error");
        }
    }
}
