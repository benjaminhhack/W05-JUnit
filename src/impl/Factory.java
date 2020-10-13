package impl;

import interfaces.IFactory;
import interfaces.IVendingMachineProduct;
import interfaces.IVendingMachine;
import interfaces.IProductRecord;


/**
 * This class implements a singleton factory. Find method doc strings at
 * @see IFactory
 */

public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    @Override
    public IVendingMachineProduct makeVendingMachineProduct(String laneCode, String description) {
        // checks if null parameters then creates if not
        if (laneCode == null && description == null) throw new NullPointerException("Lane code and description cannot be null.");
        if (laneCode == null) throw new NullPointerException("Lane code cannot be null.");
        if (description == null) throw new NullPointerException("Description cannot be null.");
        return new VendingMachineProduct(laneCode, description);
    }

    @Override
    public IProductRecord makeProductRecord(IVendingMachineProduct vendingMachineProduct) {
        // checks if null parameter then creates if not
        if (vendingMachineProduct == null) throw new NullPointerException("Vending Machine Product cannot be null.");
        return new ProductRecord(vendingMachineProduct);
    }

    @Override
    public IVendingMachine makeVendingMachine() {
        return new VendingMachine();
    }

}
