package com.okular.jserv.orders.boundary;

import com.okular.jserv.orders.control.OrderProcessor;
import com.okular.jserv.orders.entity.CoffeeType;
import com.okular.jserv.orders.entity.Order;
import com.okular.jserv.orders.entity.Origin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Stateless
public class CoffeeShop {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    OrderProcessor orderProcessor;

    public Set<CoffeeType> getCoffeeTypes() {
        return EnumSet.of(CoffeeType.ESPRESSO, CoffeeType.LATTE, CoffeeType.POUR_OVER);
    }

    public Set<Origin> getOrigins() {
        return new HashSet<>(entityManager.createNamedQuery(Origin.FIND_ALL, Origin.class).getResultList());
    }

    public Origin getOrigin(String name) {
        return entityManager.find(Origin.class, name);
    }

    public void createOrder(Order order) {
        entityManager.merge(order);
        entityManager.flush();
    }

    public Order getOrder(UUID id) {
        return entityManager.find(Order.class, id.toString());
    }

    public List<Order> getOrders() {
        return entityManager.createNamedQuery(Order.FIND_ALL, Order.class)
                .getResultList();
    }

    public void processUnfinishedOrders() {
        entityManager.createNamedQuery(Order.FIND_UNFINISHED, Order.class)
                .getResultList()
                .forEach(orderProcessor::processOrder);
    }

    public void updateOrder(UUID id, Order order) {
        Order managedOrder = entityManager.find(Order.class, id.toString());
        managedOrder.setType(order.getType());
        managedOrder.setOrigin(order.getOrigin());
    }

}
