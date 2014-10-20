/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leoam14
 */
public class GA {
    
    public static Double[] getEquacaoReta(Double x1,Double y1,Double x2,Double y2)
    {
        Double[] abc = new Double[3];
        abc[0] = y1-y2;
        abc[1] = x2-x1;
        abc[2] = (x1*y2) - (x2*y1);
        
        if(y1-y2!=0)
        for(int i=0;i<3;i++){
            abc[i]=abc[i]/(y1-y2);
        }
        return abc;
    }
    
    public static List<Double> getAngleFromPoints(List<Double[]> pointsIn){
          
        List<Double> angulos = new ArrayList<Double>();
        
        for(int i=0; i+2< pointsIn.size(); i++){
            Double points[] = pointsIn.get(i);
            Double points2[] = pointsIn.get(i+1);
            Double points3[] = pointsIn.get(i+2);
            Double retas[] =  GA.getEquacaoReta(points[0], points[1], points2[0], points2[1]);
            Double retas2[] =  GA.getEquacaoReta(points2[0], points2[1], points3[0], points3[1]);
            Double angulo = getAnguloEntreRetasCoef(retas[0],retas[1],retas[2],retas2[0],retas2[1],retas2[2]);
            angulos.add(angulo);
        }
        return angulos;
    }
    
    
    public static Double getAnguloEntreRetas(Double a1, Double b1, Double c1, Double a2, Double b2, Double c2 ){
        return Math.toDegrees(Math.acos(getProdutoVetorial(a1, b1, c1, a2, b2, c2)/(getModulo(a1, b1, c1)*getModulo(a2, b2, c2))));
    }
    
    public static Double getAnguloEntreRetasCoef(Double a1, Double b1, Double c1, Double a2, Double b2, Double c2 ){
        Double m1 = b1!=0 ? -a1/b1 : 999999999;
        Double m2 = b2!=0 ? -a2/b2 : 999999999;
        return Math.toDegrees(Math.atan(Math.abs((m1-m2)/(1+(m1*m2)))));
    }
    
    public static Double getProdutoVetorial(Double a1, Double b1, Double c1, Double a2, Double b2, Double c2){
        System.out.println("Produto Vetorial: "+Math.abs((a1*a2) + (b1*b2) + (c1*c2)));
        return Math.abs((a1*a2) + (b1*b2) + (c1*c2));
    }
    
    public static Double getModulo(Double a1, Double b1, Double c1){
        System.out.println("Modulo : "+Math.sqrt((Math.pow(a1, 2)+Math.pow(b1, 2)+Math.pow(c1, 2)))+"  a1,b1,c1="+a1+" "+b1+" "+c1);
        return Math.sqrt(Math.pow(a1, 2)+Math.pow(b1, 2)+Math.pow(c1, 2));
    }
    
    public static Double[] getCoordenadasRotacionadas(Double x1, Double y1, Double angulo)
    {
        Double[] pontoRotacionado = new Double[2];
        
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
            Double pontos[] = getEquacaoReta(x1, y1, 0.0, 0.0);
            Double anguloEntreRetas = getAnguloEntreRetas(pontos[0], pontos[1], pontos[2], 1.0, 0.0, 0.0);
            angulo = anguloEntreRetas - angulo;
            pontoRotacionado[0] = Math.sqrt(Math.pow(x1, 2)+Math.pow(y1,2))*Math.cos(Math.toRadians(angulo));
            pontoRotacionado[1] = Math.sqrt(Math.pow(x1, 2)+Math.pow(y1,2))*Math.sin(Math.toRadians(angulo));
        }
        
        
     return pontoRotacionado;
    } 
    
    public static Double[] getCoordenadasRotacionadasTransladadas(Double x1, Double y1, Double anguloRotacionado, Double xTransladado, Double yTrasladado){
        x1 -= xTransladado;
        y1 -= yTrasladado;
        return getCoordenadasRotacionadas(x1,y1,anguloRotacionado);
    }
    
    public static void main(String[] args) {
        Double[] ponto1 = getEquacaoReta(1.0,1.0,1.0,2.0);
        Double[] ponto2 = getEquacaoReta(1.0,1.0,2.0,1.0);
        System.out.println("Pontos"+" "+ponto1[0]+" "+ ponto1[1]+" "+ ponto1[2]+" "+ ponto2[0]+" "+ ponto2[1]+" "+ ponto2[2]);
        System.out.println("Angulo:" + getAnguloEntreRetasCoef(ponto1[0], ponto1[1], ponto1[2], ponto2[0], ponto2[1], ponto2[2]));
        Double[] ponto3 = getCoordenadasRotacionadasTransladadas(10.0,10.0,0.0,15.0,15.0);
        System.out.println("Rotacionado: x1 :"+ponto3[0]+" y1: "+ponto3[1]);
    }
    
    
    
}
