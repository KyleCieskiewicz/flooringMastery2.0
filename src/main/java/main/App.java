/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author kylecieskiewicz
 */
import controller.Controller;
import dao.DateNotFoundException;
import dao.DataValidationException;
import dao.PersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

//        ApplicationContext ac
//                = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Controller ct
//                = ac.getBean("control", Controller.class);
//        ct.run(ac);
        Scanner sc;
        ApplicationContext ctx;
        Controller controller;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("applicationContextMode.txt")));
            ctx = new ClassPathXmlApplicationContext(sc.nextLine().trim());
            controller = ctx.getBean("control", Controller.class);
            controller.run();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

}
