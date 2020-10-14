package test;

import impl.ProductRecord;
import impl.VendingMachineProduct;
import org.junit.jupiter.api.Test;

import common.AbstractFactoryClient;
import interfaces.IVendingMachineProduct;
import interfaces.IVendingMachine;
import interfaces.IProductRecord;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class for the Factory ADT
 */
public class FactoryTests extends AbstractFactoryClient {

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

    /**
     * This checks that the factory throws an exception when creating an instance of IProductRecord
     * with a null product.
     */
    @Test
    public void productRecordNull(){
        IVendingMachineProduct product = null;
        Exception exception = assertThrows(NullPointerException.class, () -> {
            IProductRecord record = getFactory().makeProductRecord(product);
        });
        String expected = "Vending Machine Product cannot be null.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    /**
     * This checks that the factory throws an exception when creating an instance of IVendingMachineProduct
     * with a null lane code.
     */
    @Test
    public void vendingMachineProductNullLaneCode(){

        Exception exception = assertThrows(NullPointerException.class, () -> {
            IVendingMachineProduct product = getFactory().makeVendingMachineProduct(null, "descr");
        });
        String expected = "Lane code cannot be null.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    /**
     * This checks that the factory throws an exception when creating an instance of IVendingMachineProduct
     * with a null description.
     */
    @Test
    public void vendingMachineProductNullDescription(){

        Exception exception = assertThrows(NullPointerException.class, () -> {
            IVendingMachineProduct product = getFactory().makeVendingMachineProduct("H8", null);
        });
        String expected = "Description cannot be null.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    /**
     * This checks that the factory throws an exception when creating an instance of IVendingMachineProduct
     * with a null lane code and description.
     */
    @Test
    public void testVendingMachineProductNullLaneCodeAndDescription(){

        Exception exception = assertThrows(NullPointerException.class, () -> {
            IVendingMachineProduct product = getFactory().makeVendingMachineProduct(null, null);
        });
        String expected = "Lane code and description cannot be null.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

}
