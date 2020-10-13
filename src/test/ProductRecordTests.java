package test;

import common.AbstractFactoryClient;
import common.ProductUnavailableException;
import interfaces.IProductRecord;
import interfaces.IVendingMachineProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testGetProduct(){
        assertEquals(product, record.getProduct());
    }

    @Test
    public void testAddItem() {
        record.addItem();
        assertEquals(1, record.getNumberAvailable());

        for (int i =0; i < 19; i++){
            record.addItem();
        }
        assertEquals(20, record.getNumberAvailable());
    }

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

    @Test
    public void testBuyItemException() {
        Exception exception = assertThrows(common.ProductUnavailableException.class, () -> record.buyItem());
        assertNotNull(exception);
    }
}
