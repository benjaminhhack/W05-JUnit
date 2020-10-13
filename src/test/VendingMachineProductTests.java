package test;

import common.AbstractFactoryClient;
import impl.VendingMachineProduct;
import interfaces.IVendingMachineProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class for the Vending Machine Product ADT
 */
public class VendingMachineProductTests extends AbstractFactoryClient {
    private IVendingMachineProduct product;

    @BeforeEach
    public void prepare(){
        product = getFactory().makeVendingMachineProduct("A1", "Choccy");
    }

    @AfterEach
    public void tearDown(){
        product = null;
    }

    /**
     * This checks that a vending machine product can't be created with a null lane code.
     */
    @Test
    public void testNullLaneCode(){

        Exception exception = assertThrows(NullPointerException.class, () -> {
            VendingMachineProduct newProduct = new VendingMachineProduct(null, "descr");
        });
        String expected = "Lane code cannot be null.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    /**
     * This checks that a vending machine product can't be created with a null description
     */
    @Test
    public void testNullDescription(){

        Exception exception = assertThrows(NullPointerException.class, () -> {
            VendingMachineProduct newProduct = new VendingMachineProduct("Y6", null);
        });
        String expected = "Description cannot be null.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    /**
     * This checks that a vending machine product can't be created with a null lane code
     * and description.
     */
    @Test
    public void testNullLaneCodeAndDescription(){

        Exception exception = assertThrows(NullPointerException.class, () -> {
            VendingMachineProduct newProduct = new VendingMachineProduct(null, null);
        });
        String expected = "Lane code and description cannot be null.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    /**
     * This checks that the getLaneCodeMethod works as expected.
     */
    @Test
    public void testGetLaneCode(){
        assertEquals("A1", product.getLaneCode());
    }

    /**
     * This checks that the getLaneDescription works as expected.
     */
    @Test
    public void testGetLaneDescription(){
        assertEquals("Choccy", product.getDescription());
    }


}

