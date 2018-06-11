/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DataValidationException;
import dao.OrdersDao;
import dao.OrdersDaoImplTraining;
import dao.PersistenceException;
import dao.ProductDao;
import dao.ProductDaoImpl;
import dao.TaxDao;
import dao.TaxDaoImpl;
import dto.Order;
import dto.Product;
import dto.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kylecieskiewicz
 */
public class ServiceLayerTest {

    private OrdersDao trainingDao = new OrdersDaoImplTraining();
    private ProductDao trainingProductDao = new ProductDaoImpl();
    private TaxDao trainingTaxDao = new TaxDaoImpl();
    ServiceLayerImpl testService;

    String date = "06/01/2018";

    LocalDate newDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    String file = "Orders_" + newDate + ".txt";

    String date2 = "06/05/2018";

    LocalDate newDate2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    String file2 = "Orders_" + newDate + ".txt";

    public ServiceLayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {

//        String file = "test_" + LocalDate.now()+".txt";
//        List<Orders> orderList = trainingDao.getAllOrders(file);
//        for (Order order : orderList) {
//            trainingDao.removeOrder(order.getOrderNumber(), file);
//        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws PersistenceException {

//        String file = "test_" + LocalDate.now() + ".txt";
        List<Order> orderList = trainingDao.getAllOrders(file);
        for (Order order : orderList) {
            trainingDao.removeOrder(order.getOrderNumber(), file);
        }
        
        List<Order> secondOrderList = trainingDao.getAllOrders(file2);
        for (Order order : secondOrderList) {
            trainingDao.removeOrder(order.getOrderNumber(), file2);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class ServiceLayer.
     */
    @Test
    public void testAddOrder() throws Exception {

        BigDecimal number = new BigDecimal("8");
        Order order = new Order();
        order.setOrderNumber(2);
        order.setOrderDate(LocalDate.now());
        order.setCustomerName("AroundBackMiniStorage");
        order.setState("OH");
        order.setTaxRate(number);
        order.setProductType("Tile");
        order.setArea(number);
        order.setCostPerSquareFoot(number);
        order.setLaborCostPerSquareFoot(number);
        order.setMaterialCost(number);
        order.setLaborCost(number);
        order.setTax(number);
        order.setTotalCost(number);

        trainingDao.addOrder(order.getOrderNumber(), order);

        Order storages = trainingDao.getOrder(order.getOrderNumber());

        assertEquals(order, storages);
    }

    /**
     * Test of getOrder method, of class ServiceLayer.
     */
    @Test
    public void testGetOrder() throws Exception {
        BigDecimal number = new BigDecimal("8");
        Order order = new Order();
        order.setOrderNumber(2);
        order.setOrderDate(LocalDate.now());
        order.setCustomerName("AroundBackMiniStorage");
        order.setState("OH");
        order.setTaxRate(number);
        order.setProductType("Tile");
        order.setArea(number);
        order.setCostPerSquareFoot(number);
        order.setLaborCostPerSquareFoot(number);
        order.setMaterialCost(number);
        order.setLaborCost(number);
        order.setTax(number);
        order.setTotalCost(number);

        trainingDao.addOrder(order.getOrderNumber(), order);

        Order storages = trainingDao.getOrder(order.getOrderNumber());

        assertEquals(order, storages);
    }

    /**
     * Test of editOrder method, of class ServiceLayer.
     */
    @Test
    public void testEditOrder() throws Exception {

        BigDecimal number = new BigDecimal("8");
        Order order = new Order();
        order.setOrderNumber(1);
        order.setOrderDate(newDate);
        order.setCustomerName("Rommies");
        order.setState("OH");
        order.setTaxRate(number);
        order.setProductType("Wood");
        order.setArea(number);
        order.setCostPerSquareFoot(number);
        order.setLaborCostPerSquareFoot(number);
        order.setMaterialCost(number);
        order.setLaborCost(number);
        order.setTax(number);
        order.setTotalCost(number);

        trainingDao.addOrder(order.getOrderNumber(), order);
        trainingDao.editOrder(order.getOrderNumber(), file);
        assertNull(trainingDao.getOrder(order.getOrderNumber()));
    }

    /**
     * Test of removeOrder method, of class ServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {

        BigDecimal number = new BigDecimal("4");
        Order order = new Order();
        order.setOrderNumber(1);
        order.setOrderDate(newDate2);
        order.setCustomerName("Brad's");
        order.setState("OH");
        order.setTaxRate(number);
        order.setProductType("Tile");
        order.setArea(number);
        order.setCostPerSquareFoot(number);
        order.setLaborCostPerSquareFoot(number);
        order.setMaterialCost(number);
        order.setLaborCost(number);
        order.setTax(number);
        order.setTotalCost(number);

        trainingDao.addOrder(order.getOrderNumber(), order);

        Order order2 = new Order();
        order2.setOrderNumber(2);
        order2.setOrderDate(newDate2);
        order2.setCustomerName("Tom's");
        order2.setState("MI");
        order2.setTaxRate(number);
        order2.setProductType("Wood");
        order2.setArea(number);
        order2.setCostPerSquareFoot(number);
        order2.setLaborCostPerSquareFoot(number);
        order2.setMaterialCost(number);
        order2.setLaborCost(number);
        order2.setTax(number);
        order2.setTotalCost(number);

        trainingDao.addOrder(order2.getOrderNumber(), order2);
        
        
        assertNotNull(trainingDao.getOrder(order.getOrderNumber()));
//        assertEquals(2, trainingDao.getAllOrders(file2).size());
        trainingDao.removeOrder(order.getOrderNumber(), file2);
//        assertEquals(1, trainingDao.getAllOrders(file2).size());
        assertNull(trainingDao.getOrder(order.getOrderNumber()));
    }

    /**
     * Test of getAllOrders method, of class ServiceLayer.
     */
//    @Test
//    public void testGetAllOrders() throws Exception {
//
//        BigDecimal number = new BigDecimal("4");
//        Order order = new Order();
//        order.setOrderNumber(1);
//        order.setOrderDate(newDate2);
//        order.setCustomerName("Brad's");
//        order.setState("OH");
//        order.setTaxRate(number);
//        order.setProductType("Tile");
//        order.setArea(number);
//        order.setCostPerSquareFoot(number);
//        order.setLaborCostPerSquareFoot(number);
//        order.setMaterialCost(number);
//        order.setLaborCost(number);
//        order.setTax(number);
//        order.setTotalCost(number);
//
//        trainingDao.addOrder(order.getOrderNumber(), order);
//
//        Order order2 = new Order();
//        order2.setOrderNumber(2);
//        order2.setOrderDate(newDate2);
//        order2.setCustomerName("Tom's");
//        order2.setState("MI");
//        order2.setTaxRate(number);
//        order2.setProductType("Wood");
//        order2.setArea(number);
//        order2.setCostPerSquareFoot(number);
//        order2.setLaborCostPerSquareFoot(number);
//        order2.setMaterialCost(number);
//        order2.setLaborCost(number);
//        order2.setTax(number);
//        order2.setTotalCost(number);
//
//        trainingDao.addOrder(order2.getOrderNumber(), order2);
//        List<Orders> theOrderList = trainingDao.getAllOrders(file2);
//        assertEquals(2, trainingDao.getAllOrders(file2).size());
//    }

    /**
     * Test of getProduct method, of class ServiceLayer.
     */
    @Test
    public void testGetProduct() throws Exception {
        
//        String wood = "Wood";
        
        Product theProduct = trainingProductDao.getProduct("Tile");
        BigDecimal costPerFoot = theProduct.getMaterialCostPerSquareFoot();
        BigDecimal temp = new BigDecimal("4.15");

        assertEquals(costPerFoot, temp);
    }

    /**
     * Test of getAllProducts method, of class ServiceLayer.
     */
//    @Test
//    public void testGetAllProducts() throws Exception {
//        
//        List<Product> theProductList = trainingProductDao.getAllProducts();
//        assertEquals(4, theProductList.size());
//    }

    /**
     * Test of getStateTax method, of class ServiceLayer.
     */
    @Test
    public void testGetStateTax() throws Exception {
        
//        MI 5.75
        BigDecimal temp = new BigDecimal("5.75");
        Tax taxObj = trainingTaxDao.getStateTax("MI");
        BigDecimal taxRate = taxObj.getTaxRate();
        assertEquals(taxRate, temp);
    }

    /**
     * Test of getAllStateTax method, of class ServiceLayer.
     */
//    @Test
//    public void testGetAllStateTax() throws Exception {
//        
//        List<Tax> theProductList = trainingTaxDao.getAllStateTax();
//        assertEquals(4, theProductList.size());
//    }

    /**
     * Test of createOrder method, of class ServiceLayer.
     */
    @Test
    public void testCreateOrder() throws Exception {
        
        BigDecimal number = new BigDecimal("8");
        Order order = new Order();
        order.setOrderNumber(2);
        order.setOrderDate(LocalDate.now());
        order.setCustomerName("AroundBackMiniStorage");
        order.setState("OH");
        order.setTaxRate(number);
        order.setProductType("Tile");
        order.setArea(number);
        order.setCostPerSquareFoot(number);
        order.setLaborCostPerSquareFoot(number);
        order.setMaterialCost(number);
        order.setLaborCost(number);
        order.setTax(number);
        order.setTotalCost(number);

        trainingDao.addOrder(order.getOrderNumber(), order);

        Order storages = trainingDao.getOrder(order.getOrderNumber());

        assertEquals(order, storages);
        
        
        
    }

    /**
     * Test of setDao method, of class ServiceLayer.
     */
//    @Test
//    public void testSetDao() {
//    }

    /**
     * Test of save method, of class ServiceLayer.
     */
//    @Test
//    public void testSave() throws Exception {
//    }

    /**
     * Test of setLoadOrderDate method, of class ServiceLayer.
     */
    @Test
    public void testSetLoadOrderDate() throws Exception {
        
        String tempDate = trainingDao.setLoadOrderDate(newDate);
        assertEquals(tempDate, file);
    }

    /**
     * Test of checkIfFile method, of class ServiceLayer.
     */
    @Test
    public void testCheckIfFile() {
       boolean temp = trainingDao.checkIfFile(file);
       
       assertEquals(false, temp);
        
    }

}
