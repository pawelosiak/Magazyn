/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import UiStart.MainWindow;
import UiStart.SearchWindow;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Vector;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.postgresql.ds.PGSimpleDataSource;


/**
 *
 * @author pawel
 */


public class Connector {

        public static int rows = 0;
        public static DefaultTableModel tab = new DefaultTableModel(new String[]{"OEM", "CZĘŚĆ", "PRODUCENT", "POJAZD", "ILOŚĆ"}, 0);
        public static int partid;
        public static boolean iHave = false;


     public static DataSource connect() {

        String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=superuser";
        String urlHost = "";
        PGSimpleDataSource conn = new PGSimpleDataSource();

        conn.setUrl(url);
        System.out.println("Connected to the PostgreSQL server successfully.");

        return conn;
    }

     public static Statement statement (Connection conn){

        Statement state = null;
            try {
                state = conn.createStatement();
            } catch (SQLException ex) {
                ex.getMessage();
            }

       return state;

     }

     public static void insert(String oem, String part, String prod, String veh, int quant)throws SQLException {

         Connection con = connect().getConnection();
         String sql = "INSERT INTO warehouse.parts(oem, part, producer, vehicle, quantity) VALUES (?, ?, ?, ?, ?);";
         PreparedStatement stateins = con.prepareStatement(sql);
         stateins.setString(1, oem);
         stateins.setString(2, part);
         stateins.setString(3, prod);
         stateins.setString(4, veh);
         stateins.setInt(5,quant);
         stateins.executeUpdate();
         stateins.close();
         con.close();
         System.out.println("Successfully added row.");

     }

     public static void result(){

         String val1 = "oem";
         String val2 = "part";
         String val3 = "producer";
         String val4 = "vehicle";
         String val5 = "quantity";
         
         String sql = "SELECT oem, part, producer, vehicle, quantity FROM warehouse.s_out() ORDER BY partid ASC";
         
         try{
         Connection con = connect().getConnection();
         PreparedStatement state = con.prepareStatement(sql);
         ResultSet result = state.executeQuery();
         System.out.println("Connection we have");
         while(result.next()){
             Vector <String> vecRes = new Vector<String>();
             vecRes.add(result.getString(val1));
             vecRes.add(result.getString(val2));
             vecRes.add(result.getString(val3));
             vecRes.add(result.getString(val4));
             vecRes.add(result.getString(val5));
             tab.addRow(vecRes);

             rows = rows+1;
                
         }

         System.out.println(rows);
         result.close();
         state.close();
         con.close();
         rows=0;
        
         }catch(SQLException ex){
         ex.getMessage();
         }
         MainWindow.setTableModel(tab);
     }

    public static void update(){

         DefaultTableModel tabUp = new DefaultTableModel(new String[]{"OEM", "CZĘŚĆ", "PRODUCENT", "POJAZD", "ILOŚĆ"}, 0);
      String val1 = "oem";
         String val2 = "part";
         String val3 = "producer";
         String val4 = "vehicle";
         String val5 = "quantity";
         
         String sql = "SELECT oem, part, producer, vehicle, quantity FROM warehouse.s_out() ORDER BY partid ASC";
         
         try{
         Connection con = connect().getConnection();
         PreparedStatement state = con.prepareStatement(sql);
         ResultSet result = state.executeQuery();
         System.out.println("Connection we have");
         while(result.next()){
             Vector <String> vecRes = new Vector<String>();
             vecRes.add(result.getString(val1));
             vecRes.add(result.getString(val2));
             vecRes.add(result.getString(val3));
             vecRes.add(result.getString(val4));
             vecRes.add(result.getString(val5));
             tabUp.addRow(vecRes);

             rows = rows+1;
                
         }

         System.out.println(rows);
         result.close();
         state.close();
         con.close();
         rows=0;
        
         }catch(SQLException ex){
         ex.getMessage();
         }
         MainWindow.setTableModel(tabUp);
     }

     public static void search(String oemORpart, String vehicle){

         String sql= "SELECT * FROM warehouse.parts WHERE warehouse.parts.oem LIKE ? AND warehouse.parts.vehicle LIKE ? OR warehouse.parts.part LIKE ? AND warehouse.parts.vehicle LIKE ?";
         
         Connection con;

        try{
         con = connect().getConnection();
         PreparedStatement stt1 = con.prepareStatement(sql);
         stt1.setString(1, oemORpart);
         stt1.setString(2, vehicle);
         stt1.setString(3, oemORpart);
         stt1.setString(4, vehicle);

         ResultSet rs = stt1.executeQuery();
          System.out.println("Connection we have");

         while(rs.next()){

             getId(rs);

             System.out.println(partid);

             //getData(rs);

             //System.out.println(vec.toString());
             //SearchWindow.resultArea.cut();
             
             //SearchWindow.resultArea.repaint();
             SearchWindow.resultArea.setText(getData(rs).toString());
             System.out.println(getData(rs).toString());
             iHave = true;

         }

            rs.close();
            stt1.close();
            con.close();

         }catch (SQLException ex){

            SearchWindow.resultArea.setText(ex.getMessage());

        }
        
     }

     public static void updateQuerry(int id, String oem, String part, String prod, String veh, String quant){


         String sql = "UPDATE warehouse.parts SET oem=?, part=?, producer=?, vehicle=?, quantity=? WHERE partid=?";


         Connection con;
         try{
         con = connect().getConnection();
         PreparedStatement stt = con.prepareStatement(sql);
         stt.setString(1, oem);
         stt.setString(2, part);
         stt.setString(3, prod);
         stt.setString(4, veh);
         stt.setInt(5, Integer.parseInt(quant));
         stt.setInt(6, id);

         ResultSet rs = stt.executeQuery();

         rs.close();
         stt.close();

         }catch(SQLException ex){
         System.out.println(ex.getMessage());
         }

         }

     public static int getId(ResultSet get) throws SQLException{

         partid=get.getInt("partid");

         return partid;
     }

     public static Vector<String> getData(ResultSet get) throws SQLException{

         Vector <String> vec = new Vector<String>();
             vec.add("NR OEM: "+get.getString("oem"));
             vec.add("CZĘŚĆ: "+get.getString("part"));
             vec.add("PRODUCENT: "+get.getString("producer"));
             vec.add("POJAZD: "+get.getString("vehicle"));
             vec.add("ILOŚĆ: "+get.getString("quantity"));

         return vec;
     }

     public static String[] fillTable(){


         System.out.println("Mamy takie id z bazy: "+partid);
         String [] partsTab={null,null,null,null,null};
         String sql= "SELECT * FROM warehouse.parts WHERE warehouse.parts.partid=? ";
         Connection con;


        try{
         con = connect().getConnection();
         PreparedStatement stt1 = con.prepareStatement(sql);
         stt1.setInt(1, partid);


         ResultSet rs = stt1.executeQuery();
          System.out.println("Connection we have");

         while(rs.next()){


             partsTab[0]=rs.getString("oem");
             partsTab[1]=rs.getString("part");
             partsTab[2]=rs.getString("producer");
             partsTab[3]=rs.getString("vehicle");
             partsTab[4]=rs.getString("quantity");


             System.out.println(partsTab[2]);

         }

            rs.close();
            stt1.close();
            con.close();

         }catch (SQLException ex){

             System.out.println(ex.getMessage());
        }


         return partsTab;
     }
     
     
     }

