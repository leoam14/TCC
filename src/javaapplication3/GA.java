/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication3;

import com.googlecode.javacv.cpp.opencv_core.*;
import com.sun.jmx.snmp.BerDecoder;

/**
 *
 * @author leoam14
 */
public class GA {
    
    public static double[] getEquacaoReta(double x1,double y1,double x2,double y2)
    {
        double[] abc = new double[3];
        abc[0] = y1-y2;
        abc[1] = x2-x1;
        abc[2] = (x1*y2) - (x2*y1);
        
        if(y1-y2!=0)
        for(int i=0;i<3;i++){
            abc[i]=abc[i]/(y1-y2);
        }
        return abc;
    }
    
    public static double getAnguloEntreRetas(double a1, double b1, double c1, double a2, double b2, double c2 ){
        return Math.toDegrees(Math.acos(getProdutoVetorial(a1, b1, c1, a2, b2, c2)/(getModulo(a1, b1, c1)*getModulo(a2, b2, c2))));
    }
    
    public static double getAnguloEntreRetasCoef(double a1, double b1, double c1, double a2, double b2, double c2 ){
        double m1 = b1!=0 ? -a1/b1 : 999999999;
        double m2 = b2!=0 ? -a2/b2 : 999999999;
        return Math.toDegrees(Math.atan(Math.abs((m1-m2)/(1+(m1*m2)))));
    }
    
    public static double getProdutoVetorial(double a1, double b1, double c1, double a2, double b2, double c2){
        System.out.println("Produto Vetorial: "+Math.abs((a1*a2) + (b1*b2) + (c1*c2)));
        return Math.abs((a1*a2) + (b1*b2) + (c1*c2));
    }
    
    public static double getModulo(double a1, double b1, double c1){
        System.out.println("Modulo : "+Math.sqrt((Math.pow(a1, 2)+Math.pow(b1, 2)+Math.pow(c1, 2)))+"  a1,b1,c1="+a1+" "+b1+" "+c1);
        return Math.sqrt(Math.pow(a1, 2)+Math.pow(b1, 2)+Math.pow(c1, 2));
    }
    
    public static double[] getCoordenadasRotacionadas(double x1, double y1, double angulo)
    {
        double[] pontoRotacionado = new double[2];
        
        if(angulo==0)
        {
            pontoRotacionado[0] = x1;
            pontoRotacionado[1] = y1;
            return  pontoRotacionado;
        }else        
        if(angulo==90)
        {
            pontoRotacionado[0] = y1;
            pontoRotacionado[1] = -x1;
            return  pontoRotacionado;
        }else
        {
            double pontos[] = getEquacaoReta(x1, y1, 0, 0);
            double anguloEntreRetas = getAnguloEntreRetas(pontos[0], pontos[1], pontos[2], 1, 0, 0);
            angulo = anguloEntreRetas - angulo;
            pontoRotacionado[0] = Math.sqrt(Math.pow(x1, 2)+Math.pow(y1,2))*Math.cos(Math.toRadians(angulo));
            pontoRotacionado[1] = Math.sqrt(Math.pow(x1, 2)+Math.pow(y1,2))*Math.sin(Math.toRadians(angulo));
        }
        
        
     return pontoRotacionado;
    } 
    
    public static double[] getCoordenadasRotacionadasTransladadas(double x1, double y1, double anguloRotacionado, double xTransladado, double yTrasladado){
        x1 -= xTransladado;
        y1 -= yTrasladado;
        return getCoordenadasRotacionadas(x1,y1,anguloRotacionado);
    }
    
    public static void main(String[] args) {
        double[] ponto1 = getEquacaoReta(1,1,1,2);
        double[] ponto2 = getEquacaoReta(1,1,2,1);
        System.out.println("Pontos"+" "+ponto1[0]+" "+ ponto1[1]+" "+ ponto1[2]+" "+ ponto2[0]+" "+ ponto2[1]+" "+ ponto2[2]);
        System.out.println("Angulo:" + getAnguloEntreRetasCoef(ponto1[0], ponto1[1], ponto1[2], ponto2[0], ponto2[1], ponto2[2]));
        double[] ponto3 = getCoordenadasRotacionadasTransladadas(10,10,0,15,15);
        System.out.println("Rotacionado: x1 :"+ponto3[0]+" y1: "+ponto3[1]);
    }
    
    
    
}
