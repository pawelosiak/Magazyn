package UiStart;

import database.Connector;
import java.sql.SQLException;
import java.io.*;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author pawel
 */
public class Init {

    /**
     * @param args the command line arguments
     */
    static MainWindow win;
    static boolean testCon = true;
    
    static class Database extends Thread{
    public synchronized void run(){
        
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
            int exitVal = proc.waitFor();
            System.out.println("ExitValue: " + exitVal);
            
            

        } catch (Throwable t) {

            t.printStackTrace();
        }
    
    }
    }
    
    static class Check extends Thread{
    public synchronized void run(){
    
        if (!win.getResultTable().isRowSelected(win.getResultTable().getSelectedRow())) {

            //win.updateBut.setEnabled(false);
            System.out.println("Nie zaznaczono linii.");
        } else if (win.getResultTable().isRowSelected(win.getResultTable().getSelectedRow())) {
            //win.updateBut.setEnabled(true);
            System.out.println("Zaznaczona linia" + win.getResultTable().getSelectedRow());
        }
    }
    }
    static class Ui extends Thread{
    @Override
    public synchronized void run(){

            Connector.result();
            win = new MainWindow();
            win.setVisible(true);
            
           
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

    public static void init() {
        // TODO code application logic here
        
        //Connector.result();
        win = new MainWindow();
        win.setVisible(true);
    }

    
    @SuppressWarnings("static-access")
    public static void main(String[] args) {

        // TODO code application logic here
        
        Database db = new Database();
        Ui app = new Ui();
       
        
        db.start();
        db.isDaemon();
        
        app.checkAccess();
        app.start();
        
        
    }

}
