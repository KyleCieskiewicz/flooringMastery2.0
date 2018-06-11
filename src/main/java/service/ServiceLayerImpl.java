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
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import dao.AuditDao;
import dao.OrdersDao;
import dao.ProductDao;
import dao.TaxDao;




public class ServiceLayerImpl implements ServiceLayer {

    OrdersDao ordersDao;
    ProductDao productDao;
    TaxDao taxDao;
//    AuditDao auditDao;

    public ServiceLayerImpl(OrdersDao ordersDao, ProductDao productDao, TaxDao taxDao, AuditDao auditDao) {
        this.ordersDao = ordersDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
//        this.auditDao = auditDao;
    }

    @Override
    public Order addOrder(Order order) throws PersistenceException {
        return ordersDao.addOrder(order.getOrderNumber(), order);
    }

    @Override
    public Order getOrder(Integer ID) throws PersistenceException {
        return ordersDao.getOrder(ID);
    }

    @Override
    public Order editOrder(Integer orderNumber, String file) throws PersistenceException {
        return ordersDao.editOrder(orderNumber, file);
    }

    @Override
    public Order removeOrder(Integer orderNumber, String file) throws PersistenceException {
        return ordersDao.removeOrder(orderNumber, file);
    }

    @Override
    public List<Order> getAllOrders(String file) throws PersistenceException {
        return ordersDao.getAllOrders(file);
    }
    

    @Override
    public Product getProduct(String type) throws PersistenceException {
        return productDao.getProduct(type);
    }

    @Override
    public List<Product> getAllProducts() throws PersistenceException {
        return productDao.getAllProducts();
    }

    @Override
    public Tax getStateTax(String state) throws PersistenceException {
        return taxDao.getStateTax(state);
    }

    @Override
    public List<Tax> getAllStateTax() throws PersistenceException {
        return taxDao.getAllStateTax();
    }

    @Override
    public void setDao(OrdersDao orderdao) {
        ordersDao = orderdao;
    }
    
    @Override
    public void save(String file) throws PersistenceException {
        ordersDao.saveOrder(file);
    }

    @Override
    public String setLoadOrderDate(LocalDate date) throws PersistenceException {
        return ordersDao.setLoadOrderDate(date);
    }
    
    @Override
    public Boolean checkIfFile(String file){
        return ordersDao.checkIfFile(file);
    };

    @Override
    public Order createOrder(Integer orderNumber, LocalDate orderdate, String name, Tax state, Product product, BigDecimal area) throws PersistenceException {
        Order order = new Order();
        order.setOrderNumber(orderNumber);
        order.setOrderDate(orderdate);
        order.setCustomerName(name);
        order.setState(state.getStateName());
        order.setTaxRate(state.getTaxRate());
        order.setProductType(product.getProductType());
        order.setArea(area);
        order.setCostPerSquareFoot(product.getMaterialCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(product.getLaborCostPerSquareFoot());
        order.setMaterialCost(area.multiply(product.getMaterialCostPerSquareFoot()));
        order.setLaborCost(area.multiply(product.getLaborCostPerSquareFoot()));

        BigDecimal BD100 = new BigDecimal("100");
        BigDecimal tax2 = (order.getTaxRate().divide(BD100, 2, RoundingMode.HALF_UP));

        order.setTax(order.getMaterialCost().add(order.getLaborCost()).multiply(tax2));
        order.setTotalCost(order.getLaborCost().add(order.getMaterialCost()).add(order.getTax()));
        return order;

    }
    
    

}
