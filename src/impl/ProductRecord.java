package impl;

import common.ProductUnavailableException;
import interfaces.IFactory;
import interfaces.IVendingMachine;
import interfaces.IVendingMachineProduct;
import interfaces.IProductRecord;

/**
 * This class represents a ProductRecord, recording information relating to a product sold in a vending machine.
 *
 */
public class ProductRecord implements IProductRecord {

    int numberOfSales;
    int numberAvailable;

    @Override
    public IVendingMachineProduct getProduct() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNumberOfSales() {
        return numberOfSales;
    }

    @Override
    public int getNumberAvailable() {
        return numberAvailable;
    }

    @Override
    public void addItem() {
        // TODO Auto-generated method stub

    }

    @Override
    public void buyItem() throws ProductUnavailableException {
        // TODO Auto-generated method stub

    }

}
