/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author kylecieskiewicz
 */
import java.math.BigDecimal;
 



public class Tax{
   private String stateName;
   private BigDecimal taxRate;
   
   public Tax(String state){
       this.stateName = state;
   }
   
   public Tax(){}
 
   public String getStateName() {
       return stateName;
   }
 
   public void setStateName(String stateName) {
       this.stateName = stateName;
   }
 
   public BigDecimal getTaxRate() {
       return taxRate;
   }
 
   public void setTaxRate(BigDecimal taxRate) {
       this.taxRate = taxRate;
   }
   
}
