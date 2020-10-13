package test;

import common.AbstractFactoryClient;
import common.LaneCodeAlreadyInUseException;
import common.LaneCodeNotRegisteredException;
import common.ProductUnavailableException;
import interfaces.IVendingMachine;
import interfaces.IVendingMachineProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class for the Vending Machine ADT
 */
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

    /**
     * This checks that the register and unregister product methods work as expected.
     */
    @Test
    public void testRegisterAndUnregisterProduct1() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        assertEquals(0, vendingMachine.getNumberOfProducts());
        vendingMachine.registerProduct(product);
        assertEquals(1, vendingMachine.getNumberOfProducts());

        vendingMachine.registerProduct(getFactory().makeVendingMachineProduct("A2", "Milk"));
        vendingMachine.registerProduct(getFactory().makeVendingMachineProduct("A3", "Soda"));
        assertEquals(3, vendingMachine.getNumberOfProducts());


        vendingMachine.unregisterProduct(product);
        assertEquals(2, vendingMachine.getNumberOfProducts());

    }

    /**
     * This checks that the register product method throws the appropriate exception
     * when one registers a new product with same lane code
     */
    @Test
    public void testRegisterProduct1() throws LaneCodeAlreadyInUseException {

        vendingMachine.registerProduct(product);
        assertEquals(vendingMachine.getNumberOfProducts(), 1);

        Exception exception = assertThrows(common.LaneCodeAlreadyInUseException.class, () -> vendingMachine.registerProduct(product));

        String expectedMessage = "This lane code is already in use.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * This checks that the register product method throws the appropriate exception
     * when one registers a null product
     */
    @Test
    public void testRegisterProduct2() throws NullPointerException {
        product = null;

        Exception exception = assertThrows(NullPointerException.class, () -> vendingMachine.registerProduct(product));

        assertNotNull(exception);
    }

    /**
     * This checks that the unregister product method throws the appropriate exception
     * when one unregisters a product that doesn't exist
     */
    @Test
    public void testUnregisterProduct1(){
        Exception exception = assertThrows(LaneCodeNotRegisteredException.class, () -> vendingMachine.unregisterProduct(product));

        String expectedMessage = "This lane code is not registered";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * This checks that the unregister product method throws the appropriate exception
     * when one unregisters a null product
     */
    @Test
    public void testUnregisterProduct2() {

        product = null;

        Exception exception = assertThrows(NullPointerException.class, () -> vendingMachine.unregisterProduct(product));

        assertNotNull(exception);
    }

    /**
     * This checks that the additem method works as expected.
     */
    @Test
    public void testAddItem1() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(product);
        assertEquals(0, vendingMachine.getTotalNumberOfItems());
        vendingMachine.addItem("A1");
        assertEquals(1, vendingMachine.getNumberOfItems("A1"));
        assertEquals(1, vendingMachine.getTotalNumberOfItems());

        vendingMachine.addItem("A1");
        assertEquals(2, vendingMachine.getNumberOfItems("A1"));
        assertEquals(2, vendingMachine.getTotalNumberOfItems());

        vendingMachine.registerProduct(getFactory().makeVendingMachineProduct("A2", "Milk"));
        assertEquals(2, vendingMachine.getTotalNumberOfItems());

        vendingMachine.addItem("A2");
        assertEquals(2, vendingMachine.getNumberOfItems("A1"));
        assertEquals(1, vendingMachine.getNumberOfItems("A2"));
        assertEquals(3, vendingMachine.getTotalNumberOfItems());

        vendingMachine.unregisterProduct(product);
        assertEquals(1, vendingMachine.getNumberOfProducts());

    }

    /**
     * This checks that the addItem product method throws the appropriate exception
     * when one adds an unregistered product
     */
    @Test
    public void testAddItem2() {
        // add unregistered product

        Exception exception = assertThrows(LaneCodeNotRegisteredException.class, () -> vendingMachine.addItem("B12"));
        assertNotNull(exception);
    }

    /**
     * This checks that the buyitem method works as expected.
     */
    @Test
    public void testBuyItem1() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product);
        vendingMachine.registerProduct(getFactory().makeVendingMachineProduct("A2", "Milk"));

        for (int i = 0; i<8; i++){
            vendingMachine.addItem("A1");
            if (i % 2 == 0) vendingMachine.addItem("A2");
        }

        for (int i = 0; i < 3; i++){
            vendingMachine.buyItem("A1");
            if (i % 2 != 0) vendingMachine.buyItem("A2");
        }

        assertEquals(8, vendingMachine.getTotalNumberOfItems());
        assertEquals(5, vendingMachine.getNumberOfItems("A1"));
        assertEquals(3, vendingMachine.getNumberOfItems("A2"));

    }

    /**
     * This checks that the buyItem product method throws the appropriate exception
     * when one buys an unregistered or unavailable product
     */
    @Test
    public void testBuyItem2() throws LaneCodeAlreadyInUseException {
        Exception laneNotRegisteredException = assertThrows(common.LaneCodeNotRegisteredException.class, () -> vendingMachine.buyItem("A1"));

        assertNotNull(laneNotRegisteredException);


        vendingMachine.registerProduct(product);
        Exception productUnvailableException = assertThrows(common.ProductUnavailableException.class, () -> vendingMachine.buyItem("A1"));

        assertNotNull(productUnvailableException);

    }

    /**
     * This checks that the getMostPopular method works as expected.
     */
    @Test
    public void  testGetMostPopular1() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product);
        vendingMachine.registerProduct(getFactory().makeVendingMachineProduct("A2", "Milk"));

        for (int i = 0; i<8; i++){
            vendingMachine.addItem("A1");
            if (i % 2 == 0) vendingMachine.addItem("A2");
        }

        for (int i = 0; i < 3; i++){
            vendingMachine.buyItem("A1");
            if (i % 2 != 0) vendingMachine.buyItem("A2");
        }
        IVendingMachineProduct mostPopular = vendingMachine.getMostPopular();
        assertEquals(product, vendingMachine.getMostPopular());
    }

    /**
     * This checks that the getMostPopular method throws the appropriate exception
     * when no products have been registered
     */
    @Test
    public void testGetMostPopular2(){
        Exception exception = assertThrows(LaneCodeNotRegisteredException.class, () -> vendingMachine.getMostPopular());
        assertNotNull(exception);
    }

    /**
     * This checks that the getNumberofItems method throws the appropriate exception
     * when no products have been registered
     */
    @Test
    public void testExceptionOnGetNumberOfItems() {
        Exception exception = assertThrows(LaneCodeNotRegisteredException.class, () -> vendingMachine.getNumberOfItems("Z6"));
        assertNotNull(exception);
    }

    /**
     * This checks that the getNumberOfSalesmethod throws the appropriate exception
     * when no products have been registered
     */
    @Test
    public void testExceptionOnGetNumberOfSales() {
        Exception exception = assertThrows(LaneCodeNotRegisteredException.class, () -> vendingMachine.getNumberOfSales("Z6"));
        assertNotNull(exception);
    }

}
