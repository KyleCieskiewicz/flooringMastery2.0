/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author kylecieskiewicz
 */
import java.math.BigDecimal;
import dto.Order;
import dto.Product;
import dto.Tax;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;




public class View {

    UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public int displayMenu() {

        io.print("\nMenu:"
                + "\n1. Display Orders"
                + "\n2. Add Order"
                + "\n3. Edit Order"
                + "\n4. Remove Order"
                + "\n5. Save Current Work"
                + "\n6. EXIT.");
        return io.readInt("\nPlease make a seletion", 1, 7);

    }

    public Integer promptUniqueOrderNumber() {
        return io.readInt("Please enter Order Number.");
    }

    public String getInfo() {
        String name = io.readString("What name shall we use for the order?");
        return name;
    }

    public BigDecimal promptArea() {
        BigDecimal area = new BigDecimal(io.readString("What is the area in square feet?"));
        return area;
    }

    public LocalDate promptDate() {
        boolean keepGoing = true;
        String date;
        LocalDate newDate = LocalDate.now();

        while (keepGoing) {
            try {
                date = io.readString("In the following format MM/dd/yyyy format");
                newDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

                keepGoing = false;
            } catch (Exception e) {
                keepGoing = true;
                System.out.println("Please enter the correct format\n");
            }
        }
        return newDate;
    }

    public Tax displayStateTax(List<Tax> taxList) {
        io.print("\nSTATE\t\tTAX"
                + "\n=====================");
        for (int i = 0; i < taxList.size(); i++) {
            io.print("\n" + (i + 1) + " " + taxList.get(i).getStateName() + "\t" + taxList.get(i).getTaxRate());
        }

        int choice = io.readInt("Please make a selection", 1, taxList.size());
        return taxList.get(choice - 1);
    }

    public Product displayProductList(List<Product> productList) {
        io.print("\tItems\tCost per square foot\tcost of labor per square foot");
        for (int i = 0; i < productList.size(); i++) {
            io.print("\n" + (i + 1) + "\t" + productList.get(i).getProductType()
                    + "\t\t\t" + productList.get(i).getMaterialCostPerSquareFoot()
                    + "\t\t\t" + productList.get(i).getLaborCostPerSquareFoot());
        }
        int choice = io.readInt("Please make a selection", 1, productList.size());
        return productList.get(choice - 1);
    }

    public String promptLoadDate() {
        return io.readString("Please enter the date for your order yyyy/MM/dd");
    }

    public void displayOrderList(List<Order> orderList) {
        for (Order order : orderList) {
            io.print("===================================");
            io.print("Order number: " + order.getOrderNumber()
                    + "\nOrder Name: " + order.getCustomerName()
                    + "\nState: " + order.getState()
                    + "\nTax Rate: " + order.getTaxRate()
                    + "\nProduct Type:" + order.getProductType()
                    + "\nArea: " + order.getArea()
                    + "\nCost Per Square Foot: " + order.getCostPerSquareFoot()
                    + "\nLabor Cost Per Square Foot: " + order.getLaborCostPerSquareFoot()
                    + "\nMaterial Cost: " + order.getMaterialCost()
                    + "\nTotal Cost Per Square Foot: " + order.getMaterialCost()
                    + "\nTotal Labor Cost Per Square Foot: " + order.getLaborCost()
                    + "\nTotal Tax: " + order.getTax()
                    + "\nTotal: " + order.getTotalCost()
                    + "\n===================================");

        }
        io.readString("Please hit enter to continue");
    }

    public int printEditMenu(Order order) {

        io.print("Edit Menu:");
        io.print("1. Name");
        io.print("2. State");
        io.print("3. Product Type");
        io.print("4. Area");
        io.print("5. Order Date");

        return io.readInt("Please make a selection", 1, 5);
    }

    public void runEditMenu(Order order, int choice, List<Tax> taxList, List<Product> productList) {
        boolean keepGoing = true;
        while (keepGoing) {

            switch (choice) {
                case 1:
                    String editName = io.readString("What would you like to "
                            + "change the order name to?");
                    order.setCustomerName(editName);
                    keepGoing = false;
                    break;

                case 2:
                    Tax editState = displayStateTax(taxList);
                    order.setState(editState.getStateName());
                    order.setTaxRate(editState.getTaxRate());
                    keepGoing = false;
                    break;
                case 3:
                    Product editProduct = displayProductList(productList);
                    order.setProductType(editProduct.getProductType());
                    order.setCostPerSquareFoot(editProduct.getLaborCostPerSquareFoot());
                    order.setLaborCostPerSquareFoot(editProduct.getLaborCostPerSquareFoot());
                    keepGoing = false;
                    break;
                case 4:
                    BigDecimal areaBD = promptArea();
                    order.setArea(areaBD);
                    keepGoing = false;
                    break;
                case 5:
                    LocalDate newDate = promptDate();
                    order.setOrderDate(newDate);
                    keepGoing = false;
                case 6:
                    keepGoing = false;

            }
        }
    }

    public boolean modeSelection() {

        io.print("1. Production"
                + "\n2. Training");

        int choice = io.readInt("Please select a mode: ", 1, 2);

        if (choice == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void displayOrdersBanner() {
        io.print("=====ORDER DISPLAY=====");
    }

    public void displayAddBanner() {
        io.print("=====ADD ORDER=====");
    }
    
    public void displayDateForAddOrderBanner() {

        io.print("=====ENTER DATE YOU WOULD LIKE TO ADD ORDER=====");
        io.print("");
    }
    
    public void displayDateForRemoveOrderBanner() {

        io.print("=====ENTER DATE YOU WOULD LIKE TO REMOVE ORDER=====");
        io.print("");
    }

    public void displayEditBanner() {
        io.print("=====EDIT ORDER=====");
    }

    public void displayRemoveBanner() {
        io.print("=====REMOVE ORDER=====");
    }
    
    public void displaySaveBanner() {
        io.print("=====ENTER DATE YOU WOULD LIKE TO SAVE PROGRESS=====");
        io.print("");
    }

    public void displayModeBanner() {
        io.print("=====SELECT MODE=====");
    }

    public void displaySuccessBanner() {
        io.print("=====SUCCESS=====");
        io.readString("Please hit enter to continue");
    }

    public void error() {
        io.print("AN ERROR HAS OCCURED");
        io.readString("PLEASE HIT ENTER TO CONTINUE");
    }
}
