package com.okular.jserv.orders.control;

import com.okular.jserv.orders.boundary.CoffeeShop;
import com.okular.jserv.orders.entity.CoffeeType;
import com.okular.jserv.orders.entity.Origin;
import com.okular.jserv.orders.entity.ValidOrder;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrderValidator implements ConstraintValidator<ValidOrder, JsonObject> {

    @Inject
    CoffeeShop coffeeShop;

    public void initialize(ValidOrder constraint) {
        // nothing to do
    }

    public boolean isValid(JsonObject json, ConstraintValidatorContext context) {
        final String type = json.getString("type", null);
        final String origin = json.getString("origin", null);

        final CoffeeType coffeeType = coffeeShop.getCoffeeTypes().stream()
                .filter(t -> t.name().equalsIgnoreCase(type))
                .findAny().orElse(null);

        final Origin coffeeOrigin = coffeeShop.getOrigin(origin);

        return coffeeOrigin != null && coffeeType != null;
    }

}
