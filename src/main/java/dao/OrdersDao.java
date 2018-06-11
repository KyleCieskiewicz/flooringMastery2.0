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
import dto.Order;
import java.time.LocalDate;
import java.util.List;


public interface OrdersDao {

    Order addOrder(Integer orderNumber, Order order) throws PersistenceException;

    Order getOrder(Integer orderNumber) throws PersistenceException;

    Order editOrder(Integer orderNumber, String file) throws PersistenceException;

    Order removeOrder(Integer orderNumber, String file) throws PersistenceException;

    List<Order> getAllOrders(String file) throws PersistenceException;

    String getDate(Order order) throws PersistenceException;

    String setLoadOrderDate(LocalDate date) throws PersistenceException;

    public void saveOrder(String file) throws PersistenceException;

    public Boolean checkIfFile(String file);
}
