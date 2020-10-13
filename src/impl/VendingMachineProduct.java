package impl;

import interfaces.IVendingMachineProduct;

/**
 * This class represents products that can be stocked and sold in a vending machine in a specific lane.
 * @see VendingMachineProduct for method doc strings
 */
public class VendingMachineProduct implements IVendingMachineProduct {
    String laneCode;
    String description;

    public VendingMachineProduct(String laneCode, String description) {
        if (laneCode == null && description == null) throw new NullPointerException("Lane code and description cannot be null.");
        if (laneCode == null) throw new NullPointerException("Lane code cannot be null.");
        if (description == null) throw new NullPointerException("Description cannot be null.");
        this.laneCode = laneCode;
        this.description = description;
    }

    @Override
    public String getLaneCode() {
        return laneCode;
    }

    @Override
    public String getDescription() { return description; }

}
