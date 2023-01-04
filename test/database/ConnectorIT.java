/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author pawel
 */
public class ConnectorIT {
    
    public ConnectorIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of connect method, of class Connector.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        DataSource expResult = null;
        DataSource result = Connector.connect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of statement method, of class Connector.
     */
    @Test
    public void testStatement() {
        System.out.println("statement");
        Connection conn = null;
        Statement expResult = null;
        Statement result = Connector.statement(conn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class Connector.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        String oem = "";
        String part = "";
        String prod = "";
        String veh = "";
        int quant = 0;
        Connector.insert(oem, part, prod, veh, quant);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of result method, of class Connector.
     */
    @Test
    public void testResult() {
        System.out.println("result");
        Connector.result();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearModel method, of class Connector.
     */
    @Test
    public void testClearModel() {
        System.out.println("clearModel");
        //Connector.clearModel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Connector.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        //Connector.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class Connector.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        String oemORpart = "";
        String vehicle = "";
        Connector.search(oemORpart, vehicle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateQuerry method, of class Connector.
     */
    @Test
    public void testUpdateQuerry() {
        System.out.println("updateQuerry");
        int id = 0;
        String oem = "";
        String part = "";
        String prod = "";
        String veh = "";
        String quant = "";
        Connector.updateQuerry(id, oem, part, prod, veh, quant);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Connector.
     */
    @Test
    public void testGetId() throws Exception {
        System.out.println("getId");
        ResultSet get = null;
        int expResult = 0;
        int result = Connector.getId(get);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class Connector.
     */
    @Test
    public void testGetData() throws Exception {
        System.out.println("getData");
        ResultSet get = null;
        Vector<String> expResult = null;
        Vector<String> result = Connector.getData(get);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillTable method, of class Connector.
     */
    @Test
    public void testFillTable() {
        System.out.println("fillTable");
        String[] expResult = null;
        String[] result = Connector.fillTable();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
