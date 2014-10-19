/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistematfg;

import java.util.Scanner;

/**
 *
 * @author Jack
 */
public class SistemaTFG {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        Robo robo = new Robo("COM5",9600);
        CalcularInversa2D c = new CalcularInversa2D();
        c.anguloAjuste(8.00, 1.50, -Math.toRadians(90));// angulo máxima do punho é 60 graus
        
        //robo.escreveSerial("b45b");
        robo.escreveSerial("b"+c.getAng1().intValue()+"b");
        robo.escreveSerial("a"+c.getAng2ajustado().intValue()+"a");
        robo.escreveSerial("p"+c.getAng3ajustado().intValue()+"p");
        //System.out.println("ang 1= "+c.getAng1()+"\nang 2= "+c.getAng2ajustado()+"\nang 3= "+c.getAng3ajustado());
        
//        while(true){
//            System.out.print("Enviar: ");
//            robo.escreveSerial(entrada.next());
//            //robo.escreveSerial("1");
//            //System.out.println(robo.leSerial());
//            System.out.print("Recebido: ");
//            System.out.println(robo.leSerial());
//            //robo.escreveSerial("0");
//        }
    }
    
}
