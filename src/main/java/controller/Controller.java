/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author kylecieskiewicz
 */
import dao.DataValidationException;
import dao.DateNotFoundException;
import dao.PersistenceException;
import dto.Order;
import dto.Product;
import dto.Tax;
import service.ServiceLayer;
import ui.View;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.context.ApplicationContext;
import dao.OrdersDao;
import service.Mode;

public class Controller {

    View view;
    ServiceLayer service;
//    Mode mode;

    public Controller(View view, ServiceLayer service) {

        this.view = view;
        this.service = service;
//        this.mode = mode;
    }

    public void run() {
        boolean keepGoing = true;
        boolean productionMode = true;
        int menuSelection = 0;
        while (keepGoing) {
            try {

                menuSelection = view.displayMenu();

                if (menuSelection == 6) {
                    menuSelection = 7;
                } else if (menuSelection == 7) {
                    menuSelection = 6;
                }

                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        save();
                        break;
//                    case 6:
//                        productionMode = view.modeSelection();
//                        changeMode(productionMode, theMode);
//
//                        break;
                    case 7:
                        keepGoing = false;
                }
            } catch (Exception e) {
                view.error();
            }
        }
    }

    private void addOrder() throws PersistenceException {
        view.displayDateForAddOrderBanner();
        LocalDate orderDate = view.promptDate();
//        String orderNumber = View.promptUniqueOrderNumber();

        Integer orderNumber = 1;
        String file = service.setLoadOrderDate(orderDate);
        Boolean ifFile = service.checkIfFile(file);
        if (ifFile) {
            List<Order> orderList = service.getAllOrders(file);
            orderNumber = orderList.size();
            orderNumber++;
        }
        String name = view.getInfo();
        List<Tax> taxList = service.getAllStateTax();
        Tax tax = view.displayStateTax(taxList);
        BigDecimal area = view.promptArea();
        List<Product> productList = service.getAllProducts();
        Product product = view.displayProductList(productList);
        Order order = service.createOrder(orderNumber, orderDate, name, tax, product, area);
        service.addOrder(order);
        view.displaySuccessBanner();
    }

    private void removeOrder() throws PersistenceException, DataValidationException {
        view.displayRemoveBanner();
        Integer order2 = view.promptUniqueOrderNumber();
        view.displayDateForRemoveOrderBanner();
        LocalDate date = view.promptDate();
        String file = service.setLoadOrderDate(date);
        service.removeOrder(order2, file);
        view.displaySuccessBanner();
    }

    private void displayOrders() throws PersistenceException, DataValidationException {
        view.displayOrdersBanner();
        LocalDate date = view.promptDate();
        String file = service.setLoadOrderDate(date);
        List<Order> orderList = service.getAllOrders(file);
        view.displayOrderList(orderList);
    }

    private void editOrder() throws PersistenceException, DataValidationException {
        view.displayEditBanner();
        List<Tax> taxList = service.getAllStateTax();
        List<Product> productList = service.getAllProducts();
        Integer order2 = view.promptUniqueOrderNumber();
        LocalDate date = view.promptDate();
        String file = service.setLoadOrderDate(date);
        Order editOrder = service.editOrder(order2, file);
        int choice = view.printEditMenu(editOrder);
        view.runEditMenu(editOrder, choice, taxList, productList);
        Order updateOrder = service.createOrder(order2, date, editOrder.getCustomerName(), service.getStateTax(editOrder.getState()), service.getProduct(editOrder.getProductType()), editOrder.getArea());
        service.addOrder(updateOrder);
        view.displaySuccessBanner();
    }

    private void save() throws PersistenceException, DataValidationException {
        view.displaySaveBanner();
        LocalDate date = view.promptDate();
        String file = service.setLoadOrderDate(date);
        List<Order> orderList = service.getAllOrders(file);
        service.save(file);
        view.displaySuccessBanner();
    }

//    private void changeMode(boolean productionMode, ApplicationContext theMode) {
//        OrdersDao ordersDao = mode.changeMode(productionMode, theMode);
//        service.setDao(ordersDao);
//    }

//    public void run() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
