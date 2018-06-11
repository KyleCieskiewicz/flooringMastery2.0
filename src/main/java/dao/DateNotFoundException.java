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
public class DateNotFoundException extends Exception {
 
   public DateNotFoundException(String message) {
       super(message);
   }
 
   public DateNotFoundException(String message, Throwable cause) {
       super(message, cause);
   }
}
