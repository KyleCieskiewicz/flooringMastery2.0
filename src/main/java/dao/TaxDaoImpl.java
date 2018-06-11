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
import static dao.ProductDaoImpl.DELIMITER;
import static dao.ProductDaoImpl.PRODUCTS_FILE;
import dto.Product;
import dto.Tax;
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




public class TaxDaoImpl implements TaxDao {

    private Map<String, Tax> states = new HashMap<>();
    public static final String TAX_FILE = "tax.txt";
    public static final String DELIMITER = ": :";

    @Override
    public Tax addStateTax(String state, Tax tax) throws PersistenceException {
        Tax newStateTax = states.put(state, tax);
        writeTax();
        return newStateTax;
    }

    @Override
    public Tax removeStateTax(String state) throws PersistenceException {
        Tax removedTax = states.remove(state);
        return removedTax;
    }

    @Override
    public List<Tax> getAllStateTax() throws PersistenceException {
        loadTax();
        return new ArrayList<Tax>(states.values());

    }

    private void writeTax() throws PersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(TAX_FILE));
        } catch (IOException e) {
            throw new PersistenceException(
                    "Could not save product data.", e);
        }
        List<Tax> taxList = this.getAllStateTax();
        for (Tax currentTax : taxList) {
            out.println(currentTax.getStateName() + DELIMITER
                    + currentTax.getTaxRate());

            out.flush();
        }
        out.close();
    }

    private void loadTax() throws PersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException(
                    "-_- Could not load tax data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Tax currentTax = new Tax(currentTokens[0]);
            //       currentTax.setStateName(currentTokens[0]);
            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));
            states.put(currentTax.getStateName(), currentTax);
        }
        scanner.close();
    }

    @Override
    public Tax getStateTax(String state) throws PersistenceException {
        loadTax();
        return states.get(state);
    }

}
