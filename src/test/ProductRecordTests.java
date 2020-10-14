package test;

import common.AbstractFactoryClient;
import common.ProductUnavailableException;
import impl.ProductRecord;
import interfaces.IProductRecord;
import interfaces.IVendingMachineProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class for the Product Record ADT
 */
public class ProductRecordTests extends AbstractFactoryClient {
    private IProductRecord record;
    private IVendingMachineProduct product;

    @BeforeEach
    public void prepare(){
        product = getFactory().makeVendingMachineProduct("A1", "Choccy");
        record = getFactory().makeProductRecord(product);
    }

    @AfterEach
    public void tearDown(){
        product = null;
        record = null;
    }

    /**
     * This checks that a record can't be created with a null product.
     */
    @Test
    public void testNullProduct(){

        Exception exception = assertThrows(NullPointerException.class, () -> {
            ProductRecord newRecord = new ProductRecord(null);
        });
        String expected = "Vending Machine Product cannot be null.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    /**
     * This checks that the getProduct method works.
     */
    @Test
    public void testGetProduct(){
        assertEquals(product, record.getProduct());
    }

    /**
     * This checks the getNumberAvailable method works
     * and that 0 items are available at the start.
     */
    @Test
    public void testNumberAvailable(){
        assertEquals(0, record.getNumberAvailable());
    }

    /**
     * This checks the getNumberofSales method works
     * and that 0 items have been sold at the start.
     */
    @Test
    public void testNumberOfSales(){
        assertEquals(0, record.getNumberOfSales());
    }


    /**
     * This checks that the addItem method works.
     */
    @Test
    public void testAddItem() {
        record.addItem();
        assertEquals(1, record.getNumberAvailable());

        for (int i =0; i < 19; i++){
            record.addItem();
        }
        assertEquals(20, record.getNumberAvailable());
    }

    /**
     * This checks that the buyItem method works.
     */
    @Test
    public void testBuyItem() throws ProductUnavailableException {
        record.addItem();
        assertEquals(1, record.getNumberAvailable());

        record.buyItem();
        assertEquals(1, record.getNumberOfSales());
        assertEquals(0, record.getNumberAvailable());

        for (int i =0; i < 19; i++){
            record.addItem();
            if (i % 3 == 0) record.buyItem();
        }
        assertEquals(12, record.getNumberAvailable());
        assertEquals(8, record.getNumberOfSales());
    }

    /**
     * This checks that the buy item throws the appropriate exception when the product
     * is not available.
     */
    @Test
    public void testBuyItemException() {
        Exception exception = assertThrows(common.ProductUnavailableException.class, () -> record.buyItem());
        assertNotNull(exception);
    }
}
