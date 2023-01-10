package UiStart;

import database.Connector;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author pawel
 */
public abstract class Init implements Runnable{

    /**
     * @param args the command line arguments
     */
    
    static boolean testCon = true;

    static class Database extends Thread{
        @Override
    public synchronized void run(){
        this.setPriority(1);
        System.out.println("Jestem wątek bazy danych i działam");
        this.isAlive();
        Connector.result();
        
    }
    }
    
    /*static class Check extends Thread{
    public synchronized void run(){
    
        if (!win.getResultTable().isRowSelected(win.getResultTable().getSelectedRow())) {

            //win.updateBut.setEnabled(false);
            System.out.println("Nie zaznaczono linii.");
        } else if (win.getResultTable().isRowSelected(win.getResultTable().getSelectedRow())) {
            //win.updateBut.setEnabled(true);
            System.out.println("Zaznaczona linia" + win.getResultTable().getSelectedRow());
        }
    }
    }*/
    static class Ui extends Thread{
    
       
    public synchronized void run(){

        this.setPriority(2);
        try {
            this.wait(1500);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Jestem wątek okna i działam");
                    Connector.result();
                    MainWindow win = new MainWindow();
                    win.setVisible(true);
                    win.repaint();
                
               
                
            /*
            Check chk = new Check();
            chk.start();
            chk.setDaemon(chk.isAlive());
            */
    }
    }

    static class StreamGobbler extends Thread {

        InputStream is;
        String type;
        OutputStream os;

        StreamGobbler(InputStream is, String type) {
            this(is, type, null);
        }

        StreamGobbler(InputStream is, String type, OutputStream redirect) {
            this.is = is;
            this.type = type;
            this.os = redirect;
        }
    };

    public static int upDatabase(){
        int exitVal=2;
    try {

            String cmd = "powershell.exe Start-Process -FilePath ./START.bat -verb RunAs";
            String [] com = {"net start postgresql-x64-14"};
            Runtime rt = Runtime.getRuntime();
            //Process proc1 = Runtime.getRuntime().exec("powershell.exe Start-Process -FilePath java.exe -Argument '-jar Magazyn.jar' -verb RunAs");
            Process proc = rt.exec(cmd);
            

            // any error message?
            StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERR");

            // any output?
            StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUT");

            // kick them off
            errorGobbler.start();
            outputGobbler.start();
            
            // any error???
            exitVal = proc.waitFor();
            
            System.out.println("ExitValue: " + exitVal);
            
 
        } catch (Throwable t) {

            t.printStackTrace();
        }
        return exitVal;
    }
    public static synchronized void runApp(int procValue){
        JFrame accessFrame = new JFrame();
            if(procValue==1){
            JOptionPane.showMessageDialog( accessFrame, JOptionPane.WARNING_MESSAGE+" Błąd bazy danych.");
            System.exit(0);
            }else if(procValue==0){
            init();
            }
    }
    public static synchronized void init(){
       
         
        Database db = new Database();
        Ui app = new Ui();

           db.run();
        if(!db.isAlive()){
            app.run();
        }

    }

    
    @SuppressWarnings("static-access")
    public static synchronized void main(String[] args) {

        runApp(upDatabase());
    }

}
