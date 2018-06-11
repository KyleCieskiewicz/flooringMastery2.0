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
import dto.Tax;
import java.lang.Thread.State;
import java.util.List;
 


public interface TaxDao {
   
   Tax addStateTax(String state, Tax tax)throws PersistenceException;
   
   Tax getStateTax(String state)throws PersistenceException;
   
   Tax removeStateTax(String state)throws PersistenceException;
   
   List<Tax> getAllStateTax()throws PersistenceException;
   
}
