/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




package javaapplication3;


import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.Blobs;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import java.awt.GridLayout;
import static javaapplication3.BlobDemo.Highlight;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author leoam14
 */
public class TesteThreshHold {
    public static int x_co;
    public static int y_co; 
    
    public static void main(String[] args) {
        CvCapture camera = cvCreateCameraCapture(1);
        IplImage image;
        final IplImage imagemHsv = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 3);
        IplImage imagemBinaria = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 1);
        IplImage imagemBinariaRGB = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 1);
        IplImage Himage = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 1);
        IplImage Simage = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 1);
        IplImage Vimage = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 1);
        
        CvScalar corBaixa = cvScalar(53, 50, 230,0);
        CvScalar corAlta = cvScalar(100, 255, 255,0);
        CvScalar corBaixaRGB = cvScalar(29, 29, 25,0);
        CvScalar corAltaRGB = cvScalar(117, 183, 192,0);
        
        JPanel controler = new JPanel();
        JLabel label = new JLabel();
        controler.add(label);
        JSlider sliderMin = new JSlider(0, 255);
        JSlider sliderMax = new JSlider(0, 255);
        
        controler.add(sliderMin);
        controler.add(sliderMax);
        
        JFrame frame = new JFrame();
        frame.add(controler);
        frame.setSize(250,250);
        frame.setVisible(true);
        
        int min=0;
        int max=5;  
        
        for (;;) {
                    min = sliderMin.getValue();
                    max = sliderMax.getValue();
                    image = cvQueryFrame(camera);
                    if(image!=null){
                    cvCvtColor(image, imagemHsv, CV_RGB2HSV);
                    
                    cvSplit(imagemHsv, Himage, Simage, Vimage, null);
                    cvShowImage("TesteH", Himage);
                    
                    
                        
                    label.setText("Min: "+min+" -- "+"Max :"+max);
                    cvInRangeS(imagemHsv,cvScalar(min, 0, 0, 0),cvScalar(max, 255,255, 0) , Himage);
                    
                    cvShowImage("TesteH2", Himage);
                    cvSubS(image, cvScalar(255,255,255, 0), image, Himage);
                    cvShowImage("Original", image);
                    
                        
                    
//                    cvDrawRect(imagemHsv, cvPoint(200, 200), cvPoint(250,250), corBaixa, 5, 8, CV_8U);
//                    cvDrawRect(imagemHsv, cvPoint(250, 200), cvPoint(300,250), corAlta, 5, 8, CV_8U);
//                    
//                    cvDrawRect(image, cvPoint(200, 200), cvPoint(250,250), corBaixaRGB, 5, 8, CV_8U);
//                    cvDrawRect(image, cvPoint(250, 200), cvPoint(300,250), corAltaRGB, 5, 8, CV_8U);
//                    
//                    cvInRangeS(imagemHsv, corBaixa, corAlta, imagemBinaria);
//                    cvInRangeS(image, corBaixaRGB, corAltaRGB, imagemBinariaRGB);
//                    
//                    cvShowImage("Teste", image);
//                    cvShowImage("TesteHsv", imagemHsv);
//                    cvShowImage("ThreshHold", imagemBinaria);
//                    cvShowImage("ThreshHoldRGB", imagemBinariaRGB);
                    }
                    
                    
                    
                    char c = (char) cvWaitKey(30);
                        if (c == 27) {
                            break;
                        }
        }
        
    }
    
}
