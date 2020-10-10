package test;

import common.AbstractFactoryClient;
import common.LaneCodeAlreadyInUseException;
import common.LaneCodeNotRegisteredException;
import interfaces.IVendingMachine;
import interfaces.IVendingMachineProduct;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class VendingMachineTests extends AbstractFactoryClient{
    private IVendingMachine vendingMachine;
    private IVendingMachineProduct product;


    @BeforeEach
    public void prepare() {
        vendingMachine = getFactory().makeVendingMachine();
        product = getFactory().makeVendingMachineProduct("A1", "Choccy");
    }

    @AfterEach
    public void tearDown(){
        vendingMachine = null;
    }
    /*
     * Test methods
     */
    @Test
    public void testRegisterAndUnregisterProduct1() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
//      expected performance
        vendingMachine.registerProduct(product);
        assertEquals(1, vendingMachine.getNumberOfProducts());

        vendingMachine.registerProduct(getFactory().makeVendingMachineProduct("A2", "Milk"));
        vendingMachine.registerProduct(getFactory().makeVendingMachineProduct("A3", "Soda"));
        assertEquals(3, vendingMachine.getNumberOfProducts());


        vendingMachine.unregisterProduct(product);
        assertEquals(2, vendingMachine.getNumberOfProducts());

    }


    @Test
    public void testRegisterProduct1() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
//      register new product with same lane code

        vendingMachine.registerProduct(product);
        assertEquals(vendingMachine.getNumberOfProducts(), 1);

        Exception exception = assertThrows(common.LaneCodeAlreadyInUseException.class, () -> {
            vendingMachine.registerProduct(product);
        });

        String expectedMessage = "This lane code is already in use.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testRegisterProduct2() throws NullPointerException, LaneCodeAlreadyInUseException {
//      register new product with same lane code
        product = null;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            vendingMachine.registerProduct(product);
        });

        assertNotNull(exception);
    }

    @Test
    public void testUnregisterProduct1(){
        // unregister a product that doesn't exist
        Exception exception = assertThrows(common.LaneCodeNotRegisteredException.class, () -> {
            vendingMachine.unregisterProduct(product);
        });

        String expectedMessage = "This lane code is not registered";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testUnegisterProduct2() {
//      register new product with same lane code
        product = null;

        Exception exception = assertThrows(common.LaneCodeNotRegisteredException.class, () -> {
            vendingMachine.unregisterProduct(product);
        });

        assertNotNull(exception);
    }

    public void testAddItem1(){

    }
}
