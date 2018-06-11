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
import static dao.OrdersDaoImpl.DELIMITER;
import static dao.OrdersDaoImpl.ORDERS_FILE;
import dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;




public class OrdersDaoImplTraining implements OrdersDao {

    private Map<Integer, Order> orders = new HashMap<>();
    public static String ORDERS_FILE;
   public static final String DELIMITER = ", ";

    @Override
    public Order addOrder(Integer orderNumber, Order order) throws PersistenceException {
        Order newOrder = orders.put(orderNumber, order);

        return newOrder;
    }

    @Override
    public Order getOrder(Integer orderNumber) throws PersistenceException {
        return orders.get(orderNumber);
    }

    @Override
    public Order editOrder(Integer orderNumber, String file) throws PersistenceException {
        loadOrders(file);
        Order editOrder = orders.remove(orderNumber);
        return editOrder;
    }

    @Override
    public Order removeOrder(Integer orderNumber, String file) throws PersistenceException {
        orders.clear();
        loadOrders(file);
        orders.remove(orderNumber);
        return null;
    }

    @Override
    public List<Order> getAllOrders(String file) throws PersistenceException {
        orders.clear();
        loadOrders(file);
        return new ArrayList<Order>(orders.values());
    }

    @Override
    public String getDate(Order order) throws PersistenceException {
        String formattedDate = "Orders_" + order.getOrderDate() + ".txt";
        return formattedDate;
    }

    @Override
    public String setLoadOrderDate(LocalDate date) throws PersistenceException {
        ORDERS_FILE = "Orders_" + date + ".txt";
        return ORDERS_FILE;
    }
    
    @Override
    public void saveOrder(String file) throws PersistenceException {
        String trainingMode = file;
    }
    
    @Override
   public Boolean checkIfFile(String file){
       return false;
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
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order();
            currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
//            currentOrder.setOrderDate(LocalDate.parse(currentTokens[1]));
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
