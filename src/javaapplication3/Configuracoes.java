/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication3;

import java.io.Serializable;

/**
 *
 * @author leoam14
 */
public class Configuracoes implements Serializable{
    int minVal = 0;
    int maxVal = 5;
    int minArea = 400;
    int maxArea = 100000;
    int minValCoulour2 = 0;
    int maxValCoulour2 = 0;
    int minAreaColour2 = 400;
    int maxAreaColour2 = 100000;

    public Configuracoes(int minVal ,int maxVal ,int minArea,int maxArea ,int minValCoulour2 ,int maxValCoulour2 ,int minAreaColour2 ,int maxAreaColour2 ) {
        this.minVal = minVal ; 
        this.maxVal = maxVal ; 
        this.minArea = minArea ; 
        this.maxArea = maxArea ; 
        this.minValCoulour2 = minValCoulour2 ; 
        this.maxValCoulour2 = maxValCoulour2 ; 
        this.minAreaColour2 = minAreaColour2 ; 
        this.maxAreaColour2 = maxAreaColour2 ; 
        
    }
    public Configuracoes(){
    
    }
    
    
    public int getMinVal() {
        return minVal;
    }

    public void setMinVal(int minVal) {
        this.minVal = minVal;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }

    public int getMinArea() {
        return minArea;
    }

    public void setMinArea(int minArea) {
        this.minArea = minArea;
    }

    public int getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(int maxArea) {
        this.maxArea = maxArea;
    }

    public int getMinValCoulour2() {
        return minValCoulour2;
    }

    public void setMinValCoulour2(int minValCoulour2) {
        this.minValCoulour2 = minValCoulour2;
    }

    public int getMaxValCoulour2() {
        return maxValCoulour2;
    }

    public void setMaxValCoulour2(int maxValCoulour2) {
        this.maxValCoulour2 = maxValCoulour2;
    }

    public int getMinAreaColour2() {
        return minAreaColour2;
    }

    public void setMinAreaColour2(int minAreaColour2) {
        this.minAreaColour2 = minAreaColour2;
    }

    public int getMaxAreaColour2() {
        return maxAreaColour2;
    }

    public void setMaxAreaColour2(int maxAreaColour2) {
        this.maxAreaColour2 = maxAreaColour2;
    }
    
    
}
