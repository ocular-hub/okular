package com.okular.jserv.orders.control;

import com.okular.jserv.orders.boundary.CoffeeShop;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

// FIXME activate
//@Singleton
//@Startup
public class OrderProcessTimer {

    @Inject
    CoffeeShop coffeeShop;

//    @Schedule(second = "*/20", minute = "*", hour = "*", persistent = false)
    public void processOrder() {
        coffeeShop.processUnfinishedOrders();
    }

}
