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
import org.springframework.context.ApplicationContext;
import dao.OrdersDao;
 



public interface Mode {
   
   OrdersDao changeMode(boolean productionMode, ApplicationContext theMode);
   
   
}
