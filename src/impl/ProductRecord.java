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

    String laneCode;
    int numberOfSales = 0;
    int numberAvailable = 0;
    IVendingMachineProduct product;


    @Override
    public IVendingMachineProduct getProduct() {
        return product;
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
