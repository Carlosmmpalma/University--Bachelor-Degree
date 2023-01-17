/**
 * Device represents general devices such as SmartPhone or Laptop
 */

public abstract class Device {

    /**
     * data members for brand, weight and memory characteristics
     */
    private String brand;
    private double weight;
    private long memory;

    /**
     * general constructor
     * @param brand
     * @param weight
     * @param memory
     */

    public Device(String brand, double weight, long memory) {
        this.brand = brand;
        this.weight = weight;
        this.memory = memory;
    }

    /**
     * get device weight
     * @return this device weight in grams
     */

    public double getWeight() {
        return this.weight;
    }

    /**
     * get device brand
     * @return this device brand
     */

    public String getBrand() {
        return this.brand;
    }

    /**
     * get device memory capacity
     * @return this device memory in bytes
     */

    public long getMemory() {
        return memory;
    }

    /**
     * test if the device is heavy
     * @return true/false if this device is heavy or not
     */

    public abstract boolean isHeavy();

}