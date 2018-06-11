/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author kylecieskiewicz
 */
//import com.sg.flooringmaster.app;
import dto.Order;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
 


public class OrdersDaoImpl implements OrdersDao{
   private Map<Integer, Order> orders = new HashMap<>();
   public static String ORDERS_FILE;
   public static final String DELIMITER = ", ";
   
 
   @Override
   public Order addOrder(Integer orderNumber, Order order) throws PersistenceException{
       Order newOrder = orders.put(orderNumber, order);
       try {
           writeNewOrder(order);
       } catch (IOException ex) {
           Logger.getLogger(OrdersDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       return newOrder;
   }
 
   @Override
   public Order getOrder(Integer orderNumber) throws PersistenceException{
//        loadOrders();
       return orders.get(orderNumber);
   }
 
   @Override
   public List<Order> getAllOrders(String file) throws PersistenceException {
       orders.clear();
       loadOrders(file);
        return new ArrayList<Order>(orders.values());
   }
   
   @Override
   public Order editOrder(Integer orderNumber, String file) throws PersistenceException {
       loadOrders(file);
       Order editOrder = orders.remove(orderNumber);
       saveOrder(file);
       return editOrder;
   }
   
   
 
   @Override
   public Order removeOrder(Integer orderNumber, String file) throws PersistenceException{
       orders.clear();
       loadOrders(file);
       orders.remove(orderNumber);
       saveOrder(file);
       return null;
   }
   
   public String getDate(Order order ) throws PersistenceException{
       String formattedDate = "Orders_" + order.getOrderDate() +".txt";
       return formattedDate;
   }
   
   @Override
   public Boolean checkIfFile(String file){
       
       File f = new File(file);
       if(f.exists()){
		  return true;
	  }else{
		  return false;
	  }
   }
   
   public String formatOrder(Order order){
      
     Integer orderNumber = order.getOrderNumber();
     LocalDate date = order.getOrderDate();
     String name =  order.getCustomerName();
     String state = order.getState();
     String taxRate = order.getTaxRate().toString();
     String type = order.getProductType();
     String costLabor = order.getLaborCostPerSquareFoot().toString();
     String area = order.getArea().toString();
     String costFt = order.getCostPerSquareFoot().toString();
     String totalMaterial = order.getMaterialCost().toString();
     String totalLabor = order.getLaborCost().toString();
     String tax = order.getTax().toString();
     String total = order.getTotalCost().toString();
     
     String format = orderNumber +DELIMITER +
//             date + DELIMITER+
             name + DELIMITER +
             state + DELIMITER +
             taxRate + DELIMITER +
             type + DELIMITER +
             area + DELIMITER +
             costFt + DELIMITER +
             costLabor + DELIMITER +
             totalMaterial + DELIMITER +
             totalLabor + DELIMITER +
             tax + DELIMITER +
             total + DELIMITER;
     return format;
   }
   
   private void writeNewOrder(Order order) throws PersistenceException, IOException{
       
           String fileName = getDate(order);
           File file = new File (fileName);
           boolean exists = file.createNewFile();
           
           FileWriter fw = new FileWriter(file, true);
           BufferedWriter bw = new BufferedWriter(fw);
           PrintWriter pw = new PrintWriter(bw);
           
           if(exists){
               pw.println("OrderNumber, Customer Name, State, Tax Rate, Product Type,"
               + " Area, Cost Per Square Foot, Labor Cost Per Square Foot, "
               + "Material Cost, Labor Cost, Tax, Total.");
           
           }
           
           String orderInput = formatOrder(order);
           pw.println(orderInput);
           pw.close();
   }
   
   @Override
   public void saveOrder(String file) throws PersistenceException{
       PrintWriter out;
       ORDERS_FILE = file;
       
       String header = "Order Number, Customer Name, State, Tax Rate, Product Type,"
               + " Area, Cost Per Square Foot, Labor Cost Per Square Foot, "
               + "Material Cost, Labor Cost, Tax, Total.";
 
       try {
           out = new PrintWriter(new FileWriter(ORDERS_FILE));
       } catch (IOException e) {
           throw new PersistenceException("Could not save inventory data.", e);
       }
       out.println(header);
       for (Order currentItem : orders.values()) {
           out.println(
                    currentItem.getOrderNumber()+ DELIMITER
//                   + currentItem.getOrderDate()+ DELIMITER
                   + currentItem.getCustomerName()+ DELIMITER
                   + currentItem.getState()+ DELIMITER
                   + currentItem.getTaxRate() + DELIMITER
                   + currentItem.getProductType() + DELIMITER
                   + currentItem.getArea()+ DELIMITER
                   + currentItem.getCostPerSquareFoot() + DELIMITER
                   + currentItem.getLaborCostPerSquareFoot() + DELIMITER
                   + currentItem.getMaterialCost()+ DELIMITER
                   + currentItem.getLaborCost()+ DELIMITER
                   + currentItem.getTax() + DELIMITER
                   + currentItem.getTotalCost());
           out.flush();
       }
       out.close();
   }
   
       @Override
   public String setLoadOrderDate(LocalDate date) throws PersistenceException {
       ORDERS_FILE = "Orders_"+date+".txt";
       return ORDERS_FILE;
   }
   
   
   private void loadOrders(String ORDERS_FILE) throws PersistenceException {
       Scanner scanner;
       
       try {
           scanner = new Scanner(
                   new BufferedReader(
                           new FileReader(ORDERS_FILE)));
           
       } catch (FileNotFoundException e) {
           throw new PersistenceException(
                   "-_- Could not load orders data into memory.", e);
       }
       if(scanner.hasNextLine()){
       scanner.nextLine();
       }
 
       String currentLine;
       String[] currentTokens;
       while (scanner.hasNextLine()) {
           
           currentLine = scanner.nextLine();
           currentTokens = currentLine.split(DELIMITER);
           Order currentOrder = new Order();
           currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
//           currentOrder.setOrderDate(LocalDate.parse(currentTokens[1]));
           currentOrder.setCustomerName(currentTokens[1]);
           currentOrder.setState((currentTokens[2]));
           currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
           currentOrder.setProductType(currentTokens[4]);
           currentOrder.setArea(new BigDecimal(currentTokens[5]));
           currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
           currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
           currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
           currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
           currentOrder.setTax(new BigDecimal(currentTokens[10]));
           currentOrder.setTotalCost(new BigDecimal(currentTokens[11]));
           
           orders.put(currentOrder.getOrderNumber(), currentOrder);
       }
       scanner.close();
   }
   
   
   
   
 
   
}
