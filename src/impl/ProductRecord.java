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
    IVendingMachineProduct product;

    public ProductRecord(IVendingMachineProduct product) {
        this.numberOfSales = 0;
        this.numberAvailable = 0;
        this.product = product;
    }

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
        numberAvailable++;
    }

    @Override
    public void buyItem() throws ProductUnavailableException {
        if (numberAvailable < 1) throw new ProductUnavailableException();
        numberOfSales++;
        numberAvailable--;
    }

}
