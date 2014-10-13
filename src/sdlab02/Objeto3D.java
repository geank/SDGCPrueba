/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdlab02;

/**
 *
 * @author geank
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Objeto3D implements Serializable{
    protected Punto[] puntos;
    protected int numPuntos;
    protected int count;
    BufferedReader br;
    public Objeto3D(String nombreFile){
        FileReader fpuntos = null;
        try {
            fpuntos = new FileReader(nombreFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fpuntos.close();
            } catch (IOException ex) {
                Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public Objeto3D(int numPuntos){
        this.numPuntos = numPuntos;
        puntos = new Punto[numPuntos];
        count = 0;
    }
    public Objeto3D(Punto[] puntos){
        this.puntos = puntos ;
        
    }
    public void dibujarLinea(Punto a, Punto b){
        try{
            puntos[count++] = a;
            puntos[count++] = b;
        }catch(Exception e){
            System.out.println("Error al dibujar linea: " + e.toString());
        }
    }
    public void mostraPuntos(){
        for(Punto p: puntos)
            System.out.println(p.toString());
    }
    public static void main(String args[]){
        Punto[] as = {new Punto(12,1,21),new Punto(23,23,42)};
        Punto[] as2 = {new Punto(12,1,21),new Punto(23,23,42)};
        Objeto3D tra = new Objeto3D(as2);
        tra.trasladar(as);
        tra.rotar(23);
        tra.mostraPuntos();
      ;
        
    }
    public InputStream  trasladar (Punto[] npuntos){
        PipedOutputStream pw = null;
        PipedInputStream pr = null;
        String nfile = "this.ser";
        try {
            FileOutputStream fos = new FileOutputStream(nfile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            pw = new PipedOutputStream();
            pr = new PipedInputStream(pw);
            ObjectOutputStream output = new ObjectOutputStream(pw);
            new TraslacionThread(output, npuntos).start();
            
            FileInputStream fis = new FileInputStream("nuevo_obj.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Objeto3D as = (Objeto3D)ois.readObject();
            this.setPuntos(as.getPuntos());
            //
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;
    }
    public InputStream rotar(double angulo){
        PipedOutputStream pw = null;
        PipedInputStream pr = null;
        String nfile = "this.ser";
        try {
            FileOutputStream fos = new FileOutputStream(nfile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            pw = new PipedOutputStream();
            pr = new PipedInputStream(pw);
            ObjectOutputStream output = new ObjectOutputStream(pw);
            new RotacionThread(output,angulo).start();
            
            FileInputStream fis = new FileInputStream("nuevo_obj.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Objeto3D as = (Objeto3D)ois.readObject();
            this.setPuntos(as.getPuntos());
            //
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;
    }
    public InputStream escalar(double escala){
         PipedOutputStream pw = null;
        PipedInputStream pr = null;
        String nfile = "this.ser";
        try {
            FileOutputStream fos = new FileOutputStream(nfile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            pw = new PipedOutputStream();
            pr = new PipedInputStream(pw);
            ObjectOutputStream output = new ObjectOutputStream(pw);
            new EscalacionThread(output,escala).start();
            
            FileInputStream fis = new FileInputStream("nuevo_obj.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Objeto3D as = (Objeto3D)ois.readObject();
            this.setPuntos(as.getPuntos());
            //
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Objeto3D.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;
    }

    public Punto[] getPuntos() {
        return puntos;
    }
    public double[][] getPuntosArray(){
        double puntosa[][] = new double[puntos.length][4];
        for(int i = 0; i < puntos.length; i++){
            puntosa[i][0] = puntos[i].x;
            puntosa[i][1] = puntos[i].y;
            puntosa[i][2] = puntos[i].z;
            puntosa[i][3] = puntos[i].h;
        }
        return puntosa;
    }
    public void setPuntos(Punto[] puntos) {
        this.puntos = puntos;
    }

    public int getNumPuntos() {
        return numPuntos;
    }

    public void setNumPuntos(int numPuntos) {
        this.numPuntos = numPuntos;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }
    
}
