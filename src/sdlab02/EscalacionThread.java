/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdlab02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geank
 */
public class EscalacionThread extends Thread{
    
    private ObjectOutputStream pw;
    private double escala;
    public EscalacionThread(ObjectOutputStream pw,double escala){
        this.pw = pw;
        this.escala = escala;
    }
    @Override
    public void run(){
        try {
            //multiplicarMatrices();
        } catch (Exception ex) {
            Logger.getLogger(EscalacionThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void multiplicarMatrices(){
        FileInputStream fis;
        try {
            fis = new FileInputStream("this.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Objeto3D obj = (Objeto3D)ois.readObject();
            double[][] dd = obj.getPuntosArray();
            double[][] result = new double[1][];
            Punto[] as = new Punto[obj.getPuntosArray().length];
            for(int i = 0; i < dd.length; i++){
                double fila[][] = new double[1][4];
                fila[0] = dd[i];
                result = multiply(fila,crearMatriz(escala));
                as[i] = new Punto();
                as[i].setXYZ(result);
            }
            obj.setPuntos(as);
            FileOutputStream fos = new FileOutputStream("nuevo_obj.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EscalacionThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EscalacionThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EscalacionThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public double[][] crearMatriz(double escala){
        double[][] matriz = {{escala,0,0,0},
            {0,escala,0,0},{0,0,escala,0},{0,0,0,1}};
        return matriz;
    }
    public static double[][] multiply(double[][] a, double[][] b) {
       int rowsInA = a.length;
       int columnsInA = a[0].length;
       int columnsInB = b[0].length;
       double[][] c = new double[rowsInA][columnsInB];
       for (int i = 0; i < rowsInA; i++) {
           for (int j = 0; j < columnsInB; j++) {
               for (int k = 0; k < columnsInA; k++) {
                   c[i][j] = c[i][j] + a[i][k] * b[k][j];
               }
           }
       }
       return c;
   }
}
