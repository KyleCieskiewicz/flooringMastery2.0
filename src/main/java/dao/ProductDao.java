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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 



public interface ProductDao {
   

   
   Product getProduct(String type)throws PersistenceException;
   
   
   List<Product> getAllProducts()throws PersistenceException;
   
}
