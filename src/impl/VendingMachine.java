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

    public HashMap<IVendingMachineProduct, IProductRecord> products;
    public HashMap<String, Integer> numberOfItems;
    public HashMap<String, Integer> numberOfSales;

    public VendingMachine(){
        this.products = new HashMap<>();
        this.numberOfItems = new HashMap<>();
        this.numberOfSales = new HashMap<>();
    }

    @Override
    public void registerProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeAlreadyInUseException, NullPointerException {

        if (products.containsKey(vendingMachineProduct)) throw new LaneCodeAlreadyInUseException();
        if(vendingMachineProduct == null) throw new NullPointerException();

        IProductRecord record = new ProductRecord();
        products.put(vendingMachineProduct, record);
        numberOfItems.put(vendingMachineProduct.getLaneCode(), 0);
        numberOfSales.put(vendingMachineProduct.getLaneCode(), 0);
    }

    @Override
    public void unregisterProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeNotRegisteredException {
        if (!products.containsKey(vendingMachineProduct)) throw new LaneCodeNotRegisteredException();


        products.remove(vendingMachineProduct);
    }

    @Override
    public void addItem(String laneCode) throws LaneCodeNotRegisteredException {

        if (!numberOfItems.containsKey(laneCode)) throw new LaneCodeNotRegisteredException();
        int count = numberOfItems.get(laneCode);

        numberOfItems.put(laneCode, ++count);
    }

    @Override
    public void buyItem(String laneCode) throws ProductUnavailableException, LaneCodeNotRegisteredException {
        if (!numberOfItems.containsKey(laneCode)) throw new LaneCodeNotRegisteredException();
        if (numberOfItems.get(laneCode) == 0) throw new ProductUnavailableException();

        int saleCount = numberOfSales.get(laneCode);
        numberOfSales.put(laneCode, ++saleCount);

        int itemCount = numberOfItems.get(laneCode);
        numberOfItems.put(laneCode, --itemCount);

    }

    @Override
    public int getNumberOfProducts() {
        return products.size();
    }

    @Override
    public int getTotalNumberOfItems() {
        int total = 0;
        for (String key: numberOfItems.keySet()){
            total += numberOfItems.get(key);
        }
        return total;
    }

    @Override
    public int getNumberOfItems(String laneCode) throws LaneCodeNotRegisteredException {
        if (!numberOfItems.containsKey(laneCode)) throw new LaneCodeNotRegisteredException();
        return numberOfItems.get(laneCode);
    }

    @Override
    public int getNumberOfSales(String laneCode) throws LaneCodeNotRegisteredException {
        if (!numberOfSales.containsKey(laneCode)) throw new LaneCodeNotRegisteredException();
        return numberOfSales.get(laneCode);
    }

    @Override
    public IVendingMachineProduct getMostPopular() throws LaneCodeNotRegisteredException {

        return null;
    }

}
