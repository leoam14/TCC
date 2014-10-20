/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.Blobs;
import static com.googlecode.javacv.cpp.opencv_core.*;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication3.BlobDemo.Highlight;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author leonardo
 */
public class NewJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    //CanvasFrame path = new CanvasFrame("Draw");
    public NewJPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jButton2.setText("Webcam");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("JAVA CV - BLOB AND OBJECT DETECTION");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public void GravarConfiguracaoArquivo(Configuracoes objeto){
        try {
                 FileOutputStream saveFile = new FileOutputStream("G:\\config.txt");
                 ObjectOutputStream stream = new ObjectOutputStream(saveFile);

                  // salva o objeto
                 stream.writeObject(objeto);

                 stream.close();
            } catch (Exception exc) {
                 exc.printStackTrace();
            }
    }
    
    public Configuracoes RestaurarConfiguracoesArquivo(){
                Object objeto = null;
                   
                    try {
                           FileInputStream restFile = new FileInputStream("G:\\config.txt");
                           ObjectInputStream stream = new ObjectInputStream(restFile);
 
                           // recupera o objeto
                           objeto = stream.readObject();
 
                           stream.close();
                    } catch (Exception e) {
                           e.printStackTrace();
                           return null;
                    }

                    return (Configuracoes)objeto;
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println(System.getProperty("user.dir"));
        Thread t;

        t = new Thread() {

            //JPanel jp = new JPanel();
            int oldx = 0, oldy = 0;

            private void paint(IplImage img, int posX, int posY) {
//        Graphics g = jp.getGraphics();
//        path.setSize(img.width(), img.height());
//        // g.clearRect(0, 0, img.width(), img.height());
//        g.setColor(Color.RED);
//        // g.fillOval(posX, posY, 20, 20);
//        g.drawLine(oldx, oldy, posY, posY);
//        oldx=posX;
//        oldy=posY;

                //System.out.println(posX + " , " + posY);
            }

            Boolean calcular = false;
            Configuracoes configuracoes =null;
            
            @Override
            public void run() {

                //variaveis de controle
                
                
                //Set The Panel Properties 
                JPanel controler = new JPanel();

                //Slider Colour 1
                int minVal = 0;
                int maxVal = 5;
                int minArea = 400;
                int maxArea = 100000;
                
                JLabel label = new JLabel();
                label.setText("Cor do Plano:" + minVal + "-" + maxVal);
                label.setSize(controler.getWidth(),30);
                controler.add(label);
                JSlider sliderMin = new JSlider(0, 255);
                JSlider sliderMax = new JSlider(0, 255);
                JSlider sliderAreaMin = new JSlider(0, 20000);
                JSlider sliderAreaMax = new JSlider(0, 20000);
                sliderAreaMax.setSize(controler.getWidth(),30);
                sliderAreaMin.setSize(controler.getWidth(),30);
                sliderMax.setSize(controler.getWidth(),30);
                sliderMin.setSize(controler.getWidth(),30);
                
                
                
                controler.add(sliderMin);
                controler.add(sliderMax);
                JLabel labelArea = new JLabel("MIN-MAX AREA");
                controler.add(labelArea);
                controler.add(sliderAreaMin);
                controler.add(sliderAreaMax);
                controler.add(new JLabel("______________"));

                //Slider Colour 2
                int minValCoulour2 = 0;
                int maxValCoulour2 = 0;
                int minAreaColour2 = 400;
                int maxAreaColour2 = 100000;
                JLabel labelColour2 = new JLabel();
                labelColour2.setText("Cor do bloco:" + minValCoulour2 + "-" + maxValCoulour2);
                labelColour2.setSize(controler.getWidth(),30);
                controler.add(labelColour2);
                JSlider sliderMinColour2 = new JSlider(0, 255);
                JSlider sliderMaxColour2 = new JSlider(0, 255);
                sliderMaxColour2.setSize(controler.getWidth(),30);
                sliderMinColour2.setSize(controler.getWidth(),30);
                controler.add(sliderMinColour2);
                controler.add(sliderMaxColour2);
                JSlider sliderAreaMin2 = new JSlider(0, 20000);
                JSlider sliderAreaMax2 = new JSlider(0, 20000);
                JLabel minMaxAreaColour2 = new JLabel("MIN-MAX AREA: ");
                controler.add(minMaxAreaColour2);
                controler.add(sliderAreaMin2);
                controler.add(sliderAreaMax2);
                
                //Botao calcular
                JButton botaoCalcular = new JButton("Calcular");
                botaoCalcular.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        calcular = !calcular;
                    }
                });
                controler.add(botaoCalcular);
                
                //Time For Blobs
                JLabel tempoLabel = new JLabel();
                tempoLabel.setSize(controler.getWidth(),30);
                controler.add(tempoLabel);
                
                //Frame
                JFrame frame = new JFrame();
                frame.add(controler);
                frame.setSize(250, 500);
                frame.setVisible(true);

                //Set camera device
                CvCapture camera = cvCreateCameraCapture(1);

                //Define Used Images 
                IplImage image;
                IplImage imgSatured = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 3);

                //Define Sequences
                CvSeq contour1 = new CvSeq(), contour2;
                CvSeq contour3 = new CvSeq();

                //Define Memory Manager
                CvMemStorage storage = CvMemStorage.create();
                
                //Define Used Binary Images
                IplImage bin = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 1);
                IplImage bin_copy = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 1);

                IplImage bin2 = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 1);
                IplImage bin2_copy = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 1);

                //Colour 1
                CvScalar min;
                CvScalar max;

                //Colour 2
                CvScalar min2;
                CvScalar max2;

                Double anguloAtual=0.0;
                Double anguloInicial=-1.0;
                String direcao = "direita";
                
                configuracoes = RestaurarConfiguracoesArquivo();
                if(configuracoes!=null){
                maxArea = configuracoes.maxArea;
                maxAreaColour2 = configuracoes.maxAreaColour2;
                maxVal = configuracoes.maxVal;
                maxValCoulour2 = configuracoes.maxValCoulour2;
                minArea = configuracoes.minArea;
                minAreaColour2 = configuracoes.minAreaColour2;
                minVal = configuracoes.minVal;
                minValCoulour2 = configuracoes.minValCoulour2;
                sliderMin.setValue(minVal);
                sliderMax.setValue(maxVal);
                sliderMinColour2.setValue(minValCoulour2);
                sliderMaxColour2.setValue(maxValCoulour2);
                sliderAreaMin.setValue(minArea);
                sliderAreaMax.setValue(maxArea);
                sliderAreaMin2.setValue(minAreaColour2);
                sliderAreaMax2.setValue(maxAreaColour2);
                }else
                {
                configuracoes = new Configuracoes();
                }
                
                
                
                Robo robo = new Robo("COM3",9600);
                
                //Loop for each Frame                
                for (;;) {
                    //Get Values From Slider
                    minVal = sliderMin.getValue();
                    maxVal = sliderMax.getValue();
                    minValCoulour2 = sliderMinColour2.getValue();
                    maxValCoulour2 = sliderMaxColour2.getValue();
                    labelColour2.setText("Cor do bloco:" + minValCoulour2 + "-" + maxValCoulour2);
                    label.setText("Cor do Plano:" + minVal + "-" + maxVal);
                    minArea = sliderAreaMin.getValue();
                    maxArea = sliderAreaMax.getValue();
                    minAreaColour2 = sliderAreaMin2.getValue();
                    maxAreaColour2 = sliderAreaMax2.getValue();
                    labelArea.setText("MIN-MAX AREA: "+ minArea+" : "+maxArea);
                    minMaxAreaColour2.setText("MIN-MAX AREA: "+ minAreaColour2+" : "+maxAreaColour2);
                    
                    

                    //Generic Colour 1
                    min = VisionControl.getMinColourHSV(minVal);
                    max = VisionControl.getMaxColourHSV(maxVal);

                    //Generic Colour 2
                    min2 = VisionControl.getMinColourHSV(minValCoulour2);
                    max2 = VisionControl.getMaxColourHSV(maxValCoulour2);
                    
                    
                    
                    configuracoes.maxArea = maxArea;
                    configuracoes.maxAreaColour2 = maxAreaColour2;
                    configuracoes.maxVal = maxVal;
                    configuracoes.maxValCoulour2 = maxValCoulour2;
                    configuracoes.minArea = minArea;
                    configuracoes.minAreaColour2 = minAreaColour2;
                    configuracoes.minVal = minVal;
                    configuracoes.minValCoulour2 = minValCoulour2;
                            

                    //Get Image From camera
                    image = cvQueryFrame(camera);

                    if (image != null) {
                        
                        imgSatured = VisionControl.convertImageToHSV(image);
                        cvSmooth(imgSatured, imgSatured, CV_GAUSSIAN, 5);

                        contour1 = new CvSeq();
                        contour3 = new CvSeq();
                        

                        cvInRangeS(imgSatured, min, max, bin);
                        cvInRangeS(imgSatured, min2, max2, bin2);
                        
                        //Flip Images to fit the real aspect
                        cvFlip(image, image, 1);
                        cvFlip(imgSatured, imgSatured, 1);
                        cvFlip(bin, bin, 1);
                        cvFlip(bin2, bin2, 1);
                        
                        //Eroding and Dilating Image To treat Noise
                        int ERODE_DILATE = 5;
                        cvErode(bin, bin, null, ERODE_DILATE);
                        //cvDilate(bin, bin, null, ERODE_DILATE);
                        cvErode(bin2, bin2, null, ERODE_DILATE);
                        //cvDilate(bin2, bin2, null, ERODE_DILATE);

                        bin_copy = bin;
                        bin2_copy = bin2;


                        long startTime = System.currentTimeMillis();    
                        
                            //Blob that identify Colour 1
                            List<BlobImage> listBlob1 = VisionControl.getBlob(bin_copy, imgSatured,new Double(minArea),new Double(maxArea));
                            List<BlobImage> listBlob2 = VisionControl.getBlob(bin2_copy, imgSatured,new Double(minAreaColour2),new Double(maxAreaColour2));
                            
                            System.out.println("List1: "+ listBlob1.size());
                            System.out.println("List2: "+ listBlob2.size());
                            
                            
                            for (BlobImage bi : listBlob1){
                                VisionControl.HighlightElement(imgSatured,bi);
                                VisionControl.setXYLabel(imgSatured, bi.getCentro());
                            }
                            
                            for (BlobImage bi : listBlob2){
                                VisionControl.HighlightElement(imgSatured,bi);
                                VisionControl.setXYLabel(imgSatured, bi.getCentro());
                            }
                            
                            List<Double[]> listaCentros = VisionControl.getArrayOfCentros(listBlob1);
                            
                            if(VisionControl.getArrayOfCentros(listBlob2).size()>=1)
                            listaCentros.add(VisionControl.getArrayOfCentros(listBlob2).get(0));
                            
                            VisionControl.drawlineFromList(imgSatured,listaCentros);
                            VisionControl.drawAngle(imgSatured, listaCentros);
                            
                            if(GA.getAngleFromPoints(listaCentros).size()>=1)
                            anguloAtual = GA.getAngleFromPoints(listaCentros).get(0);
                            
                            if(robo!=null)
                            {
                                if(calcular == true){
                                    if(anguloInicial==-1.0)
                                        anguloInicial = anguloAtual;

                                    if(anguloAtual < 5){
                                        System.out.println("Fica Paradinha!!!");
                                        direcao = "";
                                        robo.escreveSerial("o0o");
                                        anguloInicial = -1.0;
                                        anguloAtual = 0.0;
                                    }else
                                    if(anguloAtual > anguloInicial+5)
                                    {
                                        direcao = "Esquerda";
                                    }
                                    else
                                    {

                                        System.out.println("Mover para "+ direcao);
                                        if(direcao.equalsIgnoreCase("Direita"))
                                        robo.escreveSerial("o80o");
                                        if(direcao.equalsIgnoreCase("Esquerda"))
                                        robo.escreveSerial("o-80o");   

                                        try {
                                            sleep(200);
                                            robo.escreveSerial("o0o");
                                        } catch (InterruptedException ex) {
                                            robo.escreveSerial("o0o");
                                        }
                                    }
                                }else
                                {
                                    direcao = "Direita";
                                    robo.escreveSerial("o0o");
                                    anguloInicial = -1.0;
                                    anguloAtual = 0.0;
                                }
                            }else{
                            anguloAtual = 0.0;
                            }
                            
                            
                        long stopTime = System.currentTimeMillis();
                        
                        tempoLabel.setText("Tempo Blobs :"+(stopTime - startTime)+" Ang: "+anguloInicial+" | "+anguloAtual);

                        cvShowImage("Imagem Saturada", imgSatured);
                        cvShowImage("Colour 1", bin_copy);
                        cvShowImage("Colour 2", bin2_copy);
                        //cvShowImage("Imagem Natural", image);

                        char c = (char) cvWaitKey(30);
                        if (c == 27) {
                            GravarConfiguracaoArquivo(configuracoes);
                            break;
                        }
                    }
                }
                cvReleaseCapture(camera);
            }
        };
        t.start();

    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}


//-----------  STASHED CODE - WAITING TO BE USED OR NOT -----------
//if (bin != null) {
//    cvFindContours(bin, storage, contour1, Loader.sizeof(CvContour.class), CV_RETR_LIST, CV_LINK_RUNS, cvPoint(0, 0));//CV_LINK_RUNS
//}
//contour2 = contour1;
//
//if (bin2 != null) {
//    cvFindContours(bin2, storage, contour3, Loader.sizeof(CvContour.class), CV_RETR_LIST, CV_LINK_RUNS, cvPoint(0, 0));
//}
//
//  while(contour1!=null && !contour1.isNull())
//  {
//  areaC = cvContourArea(contour1, CV_WHOLE_SEQ, 1);
//  if(areaC>areaM )
//      areaM = areaC;
//  contour1 = contour1.h_next();
//  }
//
//  while(contour2!=null && !contour2.isNull())
//  {
//      areaC = cvContourArea(contour2, CV_WHOLE_SEQ, 1);
//
//  if(areaC<areaM)
//  {
//      cvDrawContours(bin, contour2, CV_RGB(0, 0, 0), CV_RGB(0, 0, 0), 0, CV_FILLED, 8,cvPoint(0, 0));
//      System.out.println(areaC+"");
//  }
//  contour2 = contour2.h_next();
//  }
//  while(contour1!=null && !contour1.isNull())
//  {
//  CvSeq points = cvApproxPoly(contour1, Loader.sizeof(CvContour.class),storage, CV_POLY_APPROX_DP,3 , 0);//cvContourPerimeter(contour1)*0.02
//  areaC = cvContourArea(contour2, CV_WHOLE_SEQ, 1);
//
//  if(points.total() > 3 && points.total() < 6 )
//  {
//
//  }
//  contour1 = contour1.h_next();
//  }
//cvDrawContours(       bin, contour2, CV_RGB(255, 0,   0), CV_RGB(0, 0, 0), 0, 2, 8,cvPoint(0, 0));
//                        while (contour3 != null && !contour3.isNull()) {
//
//                            Double area = cvContourArea(contour3, CV_WHOLE_SEQ, 0); //slice CV_WHOLE_SEQ is the Whole all the slices
//                            if (area < 3000) {
//                                cvDrawContours(imgSatured, contour3, CV_RGB(0, 0, 255), CV_RGB(0, 0, 0), 0, 2, 8, cvPoint(0, 0));
//                                //System.out.println("Area Blue: "+area);
//                            }
//                            contour3 = contour3.h_next();
//                        }
//
//                        while (contour1 != null && !contour1.isNull()) {
//
//                            Double area = cvContourArea(contour1, CV_WHOLE_SEQ, 0); //slice CV_WHOLE_SEQ is the Whole all the slices
//                            if (area < 5000) {
//                                cvDrawContours(imgSatured, contour1, CV_RGB(255, 255, 0), CV_RGB(0, 0, 0), 0, 2, 8, cvPoint(0, 0));
//                            }
//                            // if(area<20000)
//                            //  System.out.println("Area Yellow "+count+": "+area);
//
//                            contour1 = contour1.h_next();
//                            // count ++;
//                        }
//
//                    int posX=0,posY=0;
//                    CvMoments moments = new CvMoments();
//                    cvMoments(bin, moments, 1);
//                    Double mom10 = cvGetSpatialMoment(moments, 1, 0);
//                    Double mom01 = cvGetSpatialMoment(moments, 0, 1);
//                    Double area = cvGetCentralMoment(moments, 0, 0);
//                    posX = (int) (mom10 / area);
//                    posY = (int) (mom01 / area);
//                    // only if its a valid position
//                    if (posX > 0 && posY > 0) {
//                        paint(bin, posX, posY);
//                    }
//cvShowImage("Video", image);
//cvShowImage("Video2", imgGray);
//