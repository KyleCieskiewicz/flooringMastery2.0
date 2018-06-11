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
import dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;




public class ProductDaoImpl implements ProductDao {

    private Map<String, Product> products = new HashMap<>();
    public static final String PRODUCTS_FILE = "products.txt";
    public static final String DELIMITER = ": :";


    @Override
    public Product getProduct(String type) throws PersistenceException {
        loadProducts();
        return products.get(type);
    }


    @Override
    public List<Product> getAllProducts() throws PersistenceException {
        loadProducts();
        return new ArrayList<Product>(products.values());
    }


    private void loadProducts() throws PersistenceException {
        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException(
                    "-_- Could not load product data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Product currentProduct = new Product(currentTokens[0]);
            currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            currentProduct.setMaterialCostPerSquareFoot(new BigDecimal(currentTokens[2]));
            products.put(currentProduct.getProductType(), currentProduct);
        }
        scanner.close();
    }
}
