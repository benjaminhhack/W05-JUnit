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
 * @see IVendingMachine for method doc strings
 */
public class VendingMachine extends AbstractFactoryClient implements IVendingMachine {

    public HashMap<String, IProductRecord> products; // hashmap for storing lane code and product record
    IProductRecord record;

    public VendingMachine(){
        this.products = new HashMap<>();
    }

    @Override
    public void registerProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeAlreadyInUseException, NullPointerException {

        if (products.containsKey(vendingMachineProduct.getLaneCode())) throw new LaneCodeAlreadyInUseException();

        // creates record for product
        record = new ProductRecord(vendingMachineProduct);
        products.put(vendingMachineProduct.getLaneCode(), record);
    }

    @Override
    public void unregisterProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeNotRegisteredException {
        if (!products.containsKey(vendingMachineProduct.getLaneCode())) throw new LaneCodeNotRegisteredException();

        products.remove(vendingMachineProduct.getLaneCode());
    }

    @Override
    public void addItem(String laneCode) throws LaneCodeNotRegisteredException {

        if (!products.containsKey(laneCode)) throw new LaneCodeNotRegisteredException();

        record = products.get(laneCode);
        record.addItem();
    }

    @Override
    public void buyItem(String laneCode) throws ProductUnavailableException, LaneCodeNotRegisteredException {
        if (!products.containsKey(laneCode)) throw new LaneCodeNotRegisteredException();
        if (products.get(laneCode).getNumberAvailable() == 0) throw new ProductUnavailableException();

        record = products.get(laneCode);
        record.buyItem();
    }

    @Override
    public int getNumberOfProducts() {
        return products.size();
    }

    @Override
    public int getTotalNumberOfItems() {
        int total = 0;
        for (IProductRecord record: products.values()){
            total += record.getNumberAvailable();
        }
        return total;
    }

    @Override
    public int getNumberOfItems(String laneCode) throws LaneCodeNotRegisteredException {
        if (!products.containsKey(laneCode)) throw new LaneCodeNotRegisteredException();
        return products.get(laneCode).getNumberAvailable();
    }

    @Override
    public int getNumberOfSales(String laneCode) throws LaneCodeNotRegisteredException {
        if (!products.containsKey(laneCode)) throw new LaneCodeNotRegisteredException();
        return products.get(laneCode).getNumberOfSales();
    }

    @Override
    public IVendingMachineProduct getMostPopular() throws LaneCodeNotRegisteredException {

        if (products.size() < 1) throw new LaneCodeNotRegisteredException();

        int maxSales = 0;

        String firstKey = products.keySet().stream().findFirst().get(); //https://stackoverflow.com/a/45705138/12693912
        IVendingMachineProduct mostPopular = products.get(firstKey).getProduct(); //sets the first registered product to the most

        for (IProductRecord record: products.values()){

            if (record.getNumberOfSales() > maxSales) {
                mostPopular = record.getProduct();
                maxSales = record.getNumberOfSales();
            }
        }
        return mostPopular;
    }

}
