/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication3;

/**
 *
 * @author cafeemymelo
 */
public class CalcularInversa2D {
        /*definindo o tamanho dos elos*/    
        //tamanho do braço    
        public static final double ELO1 = 9.4;
        //tamanho do antebraço
        public static final double ELO2 = 11.8;
        //tamanho do pulso até o começo da garra 
        public static final double ELO3 = 5.2;
        // ângulo do ombro
        private Double ang1;
        // ângulo do cotovelo
        private Double ang2;
        //ângulo do punho
        private Double ang3;
        // ajustados conforme o plano x,y
        private Double ang2ajustado;
        
        private Double ang3ajustado;

    public Double getAng1() {
        return ang1;
    }

    public Double getAng2() {
        return ang2;
    }

    public Double getAng3() {
        return ang3;
    }

    public Double getAng2ajustado() {
        return ang2ajustado;
    }

    public Double getAng3ajustado() {
        return ang3ajustado;
    }

   
        

    

    public void calcularInversa( double posX, double posY, double angGarra){
        /* Ângulo dos elos */
        
        
        
       /*pontos cartesianos das outras juntas */
       double pWx;// ponto x da coordenada do motor do punho
       double pWy;// ponto y da coordenada do motor do punho
       /*double posX2;// ponto x da coordenada do motor do antebraço
       double posY2;// ponto y da coordenada do motor do antebraço
       double posX1;// ponto x da coordenada do motor do braço
       double posY1;// ponto y da coordenada do motor do braço*/
       
       /*calculando o posX3*/
        
       pWx = posX - ELO3*Math.cos(angGarra);
       /*System.out.println("PI="+ Math.PI);
       System.out.println("cosseno do angulo da garra="+ Math.cos(angGarra));*/
       
       /*calculando o posY3*/
        
       pWy = posY - ELO3*Math.sin(angGarra);
       //System.out.println("seno do angulo da garra"+ Math.sin(angGarra));
        
      // System.out.println("posição x do punho = " + pWx + " posição y do punho = " + pWy);
       
       /* calculado o ângulo da junta 2 (ang2)*/
       
       double a = ((pWy*pWy) + (pWx*pWx) - (ELO1*ELO1) - (ELO2*ELO2))/(2*ELO1*ELO2);
        
       ang2 = Math.toDegrees(Math.acos(a));
       //System.out.println("a = "+a+"\nang 2 = "+ang2);
       
       /* calculando o ângulo da junta 1 (ang1)*/
       
       double b = (pWy*(ELO1+ELO2*Math.cos(ang2))-pWx*ELO2*Math.sin(ang2))/(pWx*(ELO1+ELO2*Math.cos(ang2))+pWy*ELO2*Math.sin(ang2));
        
       ang1 = Math.toDegrees(Math.atan(b));
       
       ang3 = angGarra - ang1 - ang2;
       
       System.out.println("ang 1 = "+ang1+"\nang 2 = "+ang2+"\nang 3 = "+ang3);
        
    }//fim da função calcula inversa
    
    public void anguloAjuste(double posX, double posY, double angGarra){
        /* Ângulo dos elos */
        calcularInversa(posX, posY, angGarra);
        
        
        // ajustando angulo 2 visando cotovelo para cima e deixando ele em função do plano (x,y)
        ang2ajustado = ang1 - ang2;
       //ajustado o angulo 3 com o eixo x
       ang3ajustado = Math.toDegrees(angGarra);
       System.out.println("ang 1 = "+ang1+"\nangAjustado 2 = "+ang2ajustado+"\nangAjustado 3 = "+ang3ajustado);    
    }//fim função ajuste
}// fim da classe
