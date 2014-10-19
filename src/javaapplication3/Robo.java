/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistematfg;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Jack
 */
public class Robo {
    private OutputStream serialOut;
    private InputStream serialIn;
    private int taxa;
    private String portaCOM;
    
    public Robo(String portaCOM, int taxa){
        this.portaCOM = portaCOM;
        this.taxa = taxa;
        this.inicialize();
    }
    
    private void inicialize(){
        try {
            //Define variável portId do tipo CommPortIdentifier para comunicação
            CommPortIdentifier portId = null;
            try{
                //Tenta verificar se a porta COM existe
                portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);               
            }catch (NoSuchPortException npe){
                //Caso a porta COM não exista
                System.out.println("Porta "+portaCOM +" Não encontrada");
            }
            SerialPort port = (SerialPort) portId.open("Comunicação Serial",this.taxa);
            //if(io){
                serialIn = port.getInputStream();
            //}else{
                serialOut = port.getOutputStream();
            //}
            port.setSerialPortParams(this.taxa, 
                                     SerialPort.DATABITS_8, 
                                     SerialPort.STOPBITS_1, 
                                     SerialPort.PARITY_NONE);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void close(){
        try{
            serialOut.close();
        }catch(IOException e){
            System.out.println("Não foi possível fechar "+portaCOM);
        }
    }
    
    public void escreveSerial(String s){
        try{
            for(int i=0;i<s.length();i++){
                serialOut.write(s.charAt(i));
            }
        }catch (IOException ex){
            System.out.println("Não foi possível Escrever");
        }
    }
    public String leSerial(){
        int aux;
        String s="-1";
        try{
            //System.out.println("Available: "+serialIn.available());
            aux = serialIn.read();
            //c = (char);
            s = ""+(char)aux;
            int cont=1;
            while(aux!=-1){
                aux = serialIn.read();
                if(aux!=-1){
                    s+=(char)aux;
                    cont++;
                }else{
                    break;
                }
            }

            return s;
        }catch(IOException ex){
            System.out.println("Não foi possível Ler");
            return "-1";
        }
        
    }
}
