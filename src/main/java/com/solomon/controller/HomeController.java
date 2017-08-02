package com.solomon.controller;

/**
 * Created by Antonella on 7/31/17.
 */


import com.test.models.CustomersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class HomeController {


    @RequestMapping("/test")
    // String methods are used to show a view
    public String test(Model model) {

        model.addAttribute("string1", "Hello World");
        model.addAttribute("string2", "Hello World 2");
        model.addAttribute("string3", "Hello World 3");


        return "testing";
    }

    @RequestMapping("/") // modified this from the original demo it was originally mapped to "/listCustomers"
    public ModelAndView listCustomer() {
        ArrayList<CustomersEntity> customerList = getAllCustomers();


        return new ModelAndView("welcome", "cList", customerList);
    }


    // this method was extracted to be used again
    // this is a regular method not a Request Mapping/Controller method
    private ArrayList<CustomersEntity> getAllCustomers() {
        Session selectCustomers = getSession();


        // Criteria is used to create the query
        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);

        // results are returned as list and cast to an ArrayList
        return (ArrayList<CustomersEntity>) c.list();
    }


    // I created this method to be used in multiple places, so we wouldn't have to
    // rewrite the same code fragments over and over again
    private Session getSession() {
        // Configuration allows app to specify properties & mapping documents
        // to use when creating the SessionFactory
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory sessionFact = cfg.buildSessionFactory();

        Session selectAll = sessionFact.openSession();

        selectAll.beginTransaction();
        return selectAll;
    }

    @RequestMapping("/searchByCity")
    public ModelAndView searchCity(@RequestParam("city") String cityName) {

        Session selectCustomers = getSession();

        // Criteria is used to create the query
        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);

        // adding additional search criteria to the query
        c.add(Restrictions.like("city", "%" + cityName + "%"));


        ArrayList<CustomersEntity> customerList = (ArrayList<CustomersEntity>) c.list();


        return new ModelAndView("welcome", "cList", customerList);
    }


    @RequestMapping("/delete")
    public ModelAndView deleteCustomer(@RequestParam("id") String id) {

        // temp Object will store info for the object we want to delete
        CustomersEntity temp = new CustomersEntity();
        temp.setCustomerId(id);

        Session customers = getSession();

        customers.delete(temp); // delete the object from the list

        customers.getTransaction().commit(); // delete the row from the database table


        ArrayList<CustomersEntity> customerList = getAllCustomers();


        return new ModelAndView("welcome", "cList", customerList);
    }

    @RequestMapping("/getNewCust")
    public String newCustomer() {
        return "addcustomerform";
    }

    @RequestMapping("/addNewCustomer")
    public String addNewCustomer(@RequestParam("custID") String custid,
                                 @RequestParam("compName") String compName,
                                 @RequestParam("contName") String contName,
                                 @RequestParam("contTitle") String contTitle,
                                 @RequestParam("address") String address, Model model) {


        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory sessionFact = cfg.buildSessionFactory();

        Session session = sessionFact.openSession();

        Transaction tx = session.beginTransaction();

        CustomersEntity newCustomer = new CustomersEntity();

        newCustomer.setCustomerId(custid);
        newCustomer.setCompanyName(compName);
        newCustomer.setContactName(contName);
        newCustomer.setContactTitle(contTitle);
        newCustomer.setAddress(address);

        session.save(newCustomer);
        tx.commit();
        session.close();

        model.addAttribute("newStuff", newCustomer);
        return "addcustsuccess";
    }

    // this is my update option
    // add section to html for the edit button
    // button link will include the id as a param
    // pass it to the new controller here to be used in a form
    // on a new page, then add the id as a hidden field and use it to
    // update the method. the actual method will be update() instead of save
    // you'll need a new method named updateform for the update to happen
    // I should just need the code from the add option plus the
    // update() method
    @RequestMapping("/update")
    public ModelAndView updateCustomer(@RequestParam("id") String id) {


        return new ModelAndView("updatecustomerform", "id", id);
    }

    @RequestMapping("/updateform")
    public ModelAndView updateForm(@RequestParam("custID") String custid,
                                   @RequestParam("compName") String compName,
                                   @RequestParam("contName") String contName,
                                   @RequestParam("contTitle") String contTitle,
                                   @RequestParam("address") String address, Model model) {

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory sessionFact = cfg.buildSessionFactory();

        Session session = sessionFact.openSession();

        Transaction tx = session.beginTransaction();

        CustomersEntity updatedCustomer = new CustomersEntity();

        updatedCustomer.setCustomerId(custid);
        updatedCustomer.setCompanyName(compName);
        updatedCustomer.setContactName(contName);
        updatedCustomer.setContactTitle(contTitle);
        updatedCustomer.setAddress(address);

        // the only modification needed here that differs from the add is
        // the update method
        session.update(updatedCustomer);
        tx.commit();
        session.close();

        return new ModelAndView("addcustsuccess", "updatedStuff", "Updated customer: " + updatedCustomer);
    }
}


