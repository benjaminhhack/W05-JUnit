package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import common.AbstractFactoryClient;
import interfaces.IVendingMachineProduct;
import interfaces.IVendingMachine;
import interfaces.IProductRecord;

/**
 * This is a JUnit test class for the Vending Machine ADT.
 */
public class Tests extends AbstractFactoryClient {

    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of IVendingMachineProduct.
     */
   @Test
    public void vendingMachineProductNotNull() {
        IVendingMachineProduct vendingMachineProduct = getFactory().makeVendingMachineProduct("A1", "Haggis Crisps");
        assertNotNull(vendingMachineProduct);
    }

    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of IVendingMachine.
     */
    @Test
    public void vendingMachineNotNull(){
       IVendingMachine vendingMachine = getFactory().makeVendingMachine();
       assertNotNull(vendingMachine);
    }

    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of IProductRecord.
     */
    @Test
    public void productRecordNotNull(){
        IProductRecord productRecord = getFactory().makeProductRecord(getFactory().makeVendingMachineProduct("A2", "Kit-Kat"));
        assertNotNull(productRecord);
    }
}
