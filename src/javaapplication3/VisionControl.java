/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication3;

import com.googlecode.javacv.Blobs;
import com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_core.CV_AA;
import static com.googlecode.javacv.cpp.opencv_core.CV_FONT_HERSHEY_SCRIPT_SIMPLEX;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvDrawLine;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvPutText;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_RGB2HSV;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static javaapplication3.BlobDemo.Highlight;
import java.util.*;


/**
 *
 * @author leoam14
 */
public class VisionControl {
    
    public static IplImage convertImageToHSV(IplImage imagemEntrada){
            IplImage imagemHsv = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 3);
            cvCvtColor(imagemEntrada, imagemHsv, CV_RGB2HSV);
            return imagemHsv;
    }
    
    public static CvScalar getMinColourHSV( int colour ){
         return new CvScalar(colour,0,0,0);
    }
    
    
    public static CvScalar getMaxColourHSV( int colour ){
         return new CvScalar(colour,255,255,0);
    }
    
    public static void HighlightElement(IplImage srcImage,BlobImage blobImage){
        Highlight(srcImage, blobImage.minX, blobImage.minY, blobImage.maxX, blobImage.maxY, 2);
    }
    
    
    
    public static void setXYLabel (IplImage src, Double[] xy){
        CvFont font = new CvFont(CV_FONT_HERSHEY_SCRIPT_SIMPLEX, 0.4, 1);
        if(xy.length==2)
        cvPutText(src, "x:" + xy[0]+ " y:" + xy[1], cvPoint(xy[0].intValue(), xy[1].intValue()), font, new CvScalar(10));
    }
    
    public static void setLabel (IplImage src, Double x, Double y, String text){
        CvFont font = new CvFont(CV_FONT_HERSHEY_SCRIPT_SIMPLEX, 0.8, 2);
        cvPutText(src, text, cvPoint(x.intValue(), y.intValue()), font, new CvScalar(10));
    }
    
    public static void drawlineFromList(IplImage src, List<Double[]> pointsIn){
        
        for(int i=0; i+1< pointsIn.size(); i++){
            Double points[] = pointsIn.get(i);
            Double points2[] = pointsIn.get(i+1);
            cvDrawLine(src, new CvPoint(points[0].intValue(), points[1].intValue()), new CvPoint(points2[0].intValue(), points2[1].intValue()), CvScalar.GREEN, 3, CV_AA, 0);
        }
    }
    
    public static void drawlineFromVector(IplImage src, Double[] points){
        
        for(int i=0; i+3< points.length; i+=2)
        cvDrawLine(src, new CvPoint(points[i].intValue(), points[i+1].intValue()), new CvPoint(points[i+2].intValue(), points[i+3].intValue()), CvScalar.GREEN, 3, CV_AA, 0);
    }
    
    public static List<Double[]> getArrayOfCentros(List<BlobImage> bis){
        List<Double[]> centros = new ArrayList<Double[]>();
        for(BlobImage bi : bis){
        centros.add(bi.getCentro());
        }
        return centros;
    }
    
    public static void drawAngle(IplImage src, List<Double[]> pointsIn){
        for(int i=0; i+2< pointsIn.size(); i++){
            Double points[] = pointsIn.get(i);
            Double points2[] = pointsIn.get(i+1);
            Double points3[] = pointsIn.get(i+2);
            Double retas[] =  GA.getEquacaoReta(points[0], points[1], points2[0], points2[1]);
            Double retas2[] =  GA.getEquacaoReta(points2[0], points2[1], points3[0], points3[1]);
            Double angulo = GA.getAnguloEntreRetasCoef(retas[0],retas[1],retas[2],retas2[0],retas2[1],retas2[2]);
            setLabel(src,points2[0],points2[1], "#"+angulo+"#");
        }
    }
    
    public static List<BlobImage> getBlob(IplImage bin_copy,IplImage imgSaturated){
        return  getBlob(bin_copy, imgSaturated, 0.0 , 1000000.0);
    }
    
    public static List<BlobImage> getBlob(IplImage bin_copy,IplImage imgSaturated,Double minArea,Double maxArea){
        Blobs Regions = new Blobs();
                            Regions.BlobAnalysis(
                                    bin_copy, // image
                                    -1, -1, // ROI start col, row
                                    -1, -1, // ROI cols, rows
                                    1, // border (0 = black; 1 = white)
                                    minArea.intValue()); // minarea
                            //Regions.PrintRegionData();

                            int Xaux = 0, Yaux = 0;
                            int xinit = 0, yinit = 0;

                            List<BlobImage> blobElementList = new ArrayList<BlobImage>();
                            for (int i=0; i<=Regions.MaxLabel ; i++){
                                double[] Region = Regions.RegionData[i];
                                
                                int MinX = (int) Region[Blobs.BLOBMINX];
                                int MaxX = (int) Region[Blobs.BLOBMAXX];
                                int MinY = (int) Region[Blobs.BLOBMINY];
                                int MaxY = (int) Region[Blobs.BLOBMAXY];
                                int area = (int) Region[Blobs.BLOBAREA];
                                int label = (int) Region[Blobs.BLOBLABEL];
                                int parent = (int) Region[Blobs.BLOBPARENT];
                                int colour = (int) Region[Blobs.BLOBCOLOR];
                                int perimeter = (int) Region[Blobs.BLOBPERIMETER];

                                if(area > minArea && area < maxArea && colour==1 && parent == 1)
                                {
                                BlobImage blobElement = new BlobImage();
                                blobElement.setArea(area);
                                blobElement.setMaxX(MaxX);
                                blobElement.setMaxY(MaxY);
                                blobElement.setMinX(MinX);
                                blobElement.setMinY(MinY);
                                blobElement.setColour(colour);
                                blobElement.setParent(parent);
                                blobElement.setPerimeter(perimeter);
                                blobElement.setLabel(label);
                                blobElementList.add(blobElement);
                                
                                }
    //                            Highlight(imgSaturated, MinX, MinY, MaxX, MaxY, 2);
    //
    //                            int x = (MinX + MaxX) / 2;
    //                            int y = (MinY + MaxY) / 2;
    //
    //                            
    //
    //                            if (i == 2) {
    //                                xinit = x;
    //                                yinit = y;
    //
    //                            }
    //                            if (Xaux != 0 && Yaux != 0 && x != 0 && y != 0 && i != 1) {
    //                                cvDrawLine(imgSaturated, new CvPoint(Xaux, Yaux), new CvPoint(x, y), CvScalar.GREEN, 3, CV_AA, 0);
    //                            }
    //
    //                            if (i == Blobs.MaxLabel && i != 1) {
    //                                cvDrawLine(imgSaturated, new CvPoint(xinit, yinit), new CvPoint(x, y), CvScalar.GREEN, 3, CV_AA, 0);
    //                            }
    //                            Xaux = x;
    //                            Yaux = y;
                            }
                        return blobElementList;
    }
    
    
}
