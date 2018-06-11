/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advice;

/**
 *
 * @author kylecieskiewicz
 */
import dao.PersistenceException;
import org.aspectj.lang.JoinPoint;
import dao.AuditDao;
 

public class LoggingAdvice {
 
   AuditDao aDao;
 
   public LoggingAdvice(AuditDao aDao) {
       this.aDao = aDao;
   }
 
   public void createAuditEntry(JoinPoint jp) {
       Object[] args = jp.getArgs();
       String auditEntry = jp.getSignature().getName() + ": ";
       for (Object currentArg : args) {
           auditEntry += currentArg;
       }
       try {
           aDao.writeAuditEntry(auditEntry);
       } catch (PersistenceException e) {
           System.err.println(
              "ERROR: Could not create audit entry in LoggingAdvice.");
       }
   }
}
