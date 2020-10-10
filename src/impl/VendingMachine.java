package impl;


import common.AbstractFactoryClient;
import common.LaneCodeAlreadyInUseException;
import common.LaneCodeNotRegisteredException;
import common.ProductUnavailableException;
import interfaces.IVendingMachineProduct;
import interfaces.IVendingMachine;
import interfaces.IProductRecord;

import java.util.HashMap;

/**
 * This class represents a simple vending machine which can stock and sell products.
 *
 */
public class VendingMachine extends AbstractFactoryClient implements IVendingMachine {

    public HashMap<IVendingMachineProduct, IProductRecord> products = new HashMap<IVendingMachineProduct, IProductRecord>();


    @Override
    public void registerProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeAlreadyInUseException, NullPointerException {

        if (products.containsKey(vendingMachineProduct)) throw new LaneCodeAlreadyInUseException();
        if(vendingMachineProduct == null) throw new NullPointerException();

        IProductRecord record = new ProductRecord();
        products.put(vendingMachineProduct, record);
    }

    @Override
    public void unregisterProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeNotRegisteredException {
        if (!products.containsKey(vendingMachineProduct)) throw new LaneCodeNotRegisteredException();


        products.remove(vendingMachineProduct);
    }

    @Override
    public void addItem(String laneCode) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub

    }

    @Override
    public void buyItem(String laneCode) throws ProductUnavailableException, LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getNumberOfProducts() {
        return products.size();
    }

    @Override
    public int getTotalNumberOfItems() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumberOfItems(String laneCode) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumberOfSales(String laneCode) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public IVendingMachineProduct getMostPopular() throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        return null;
    }

}
