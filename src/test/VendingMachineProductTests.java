package test;

import common.AbstractFactoryClient;
import interfaces.IVendingMachineProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testGetLaneCode(){
        assertEquals("A1", product.getLaneCode());
    }

    @Test
    public void testGetLaneDescription(){
        assertEquals("Choccy", product.getDescription());
    }


}

