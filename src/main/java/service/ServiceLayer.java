/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author kylecieskiewicz
 */
import dao.DataValidationException;
import dao.PersistenceException;
import dto.Order;
import dto.Product;
import dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import dao.OrdersDao;
 



public interface ServiceLayer {
   
   Order addOrder (Order order)throws PersistenceException;
   
   Order getOrder(Integer ID)throws PersistenceException;
   
   Order editOrder(Integer orderNumber, String file)throws PersistenceException;
   
   Order removeOrder(Integer orderNumber, String file)throws PersistenceException;
   
   List<Order> getAllOrders(String file)throws PersistenceException;
   
   Product getProduct(String type)throws PersistenceException;
   
   List<Product> getAllProducts()throws PersistenceException;
   
   Tax getStateTax(String state)throws PersistenceException;
 
   List<Tax> getAllStateTax()throws PersistenceException;
   
   Order createOrder(Integer orderNumber, LocalDate orderdate, String name, Tax state, Product product, BigDecimal area)throws PersistenceException;
   
   void setDao(OrdersDao orderdao);
   
   void save(String file) throws PersistenceException,DataValidationException;
   
   String setLoadOrderDate(LocalDate date) throws PersistenceException;

//    public Integer getOrderNumber(LocalDate orderDate) throws PersistenceException,DataValidationException;

    public Boolean checkIfFile(String file);
   
}
