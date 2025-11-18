

public class Device {
    
    private int id;  // Unique device ID between 100 and 999 (0 if setup incorrect)
    private String name;  // Name of the device (e.g., MainLight-MasterBedroom)
    private int status;  // 0 = Off, -1 = On, 2 = Standby
    private double maxPowerConsumption;  // Maximum power in watts
    private boolean critical;  // True if device is critical

    // Status Constants
    public static final int StatusOFF = 0;
    public static final int StatusON = -1;
    public static final int StatusSTANDBY = 2;


    //Constructor for device wuthout critical
    public Device(int id, String name, double maxPowerConsumption) {
        this.id = validateId(id);
        this.name = name;
        this.maxPowerConsumption = maxPowerConsumption;
        this.status = StatusOFF;
        this.critical = false;
    }


    //Constructor for Device with critical
    public Device(int id, String name, double maxPowerConsumption, boolean critical) {
        this.id = validateId(id);
        this.name = name;
        this.maxPowerConsumption = maxPowerConsumption;
        this.status = StatusOFF;
        this.critical = critical;
    }

    // this is the valid id, it must be between 100-999
    private int validateId(int id) {
        if (id >= 100 && id <= 999) {
            return id;
        }
        return 0;  // Invalid setup
    }

    // Here is the getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = validateId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if (status == StatusOFF || status == StatusON || status == StatusSTANDBY) {
            this.status = status;
        }
    }

    public double getMaxPowerConsumption() {
        return maxPowerConsumption;
    }

    public void setMaxPowerConsumption(double maxPowerConsumption) {
        this.maxPowerConsumption = maxPowerConsumption;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

   
    //turn on the device
    public void turnOn() {
        this.status = StatusON;
    }
    

    // turn of the device
    public void turnOff() {
        this.status = StatusOFF;
    }
    

    //function to return the power consumption
    public double getCurrentConsumption() {
        if (status == StatusON) {
            return maxPowerConsumption;
        } else {
            return 0;
        }
    }


    
    
    //toString function to represent the device
    @Override
    public String toString() {
        String statusString;

        if (status == StatusON) {
            statusString = "On";
        } else if (status == StatusOFF) {
            statusString = "Off";
        } else {
            statusString = "Standby";
        }

        String criticalString;

        if (critical)
            criticalString = "critical";
        else 
            criticalString = "not critical";
        
        return "id=[" + id + "] , name=[" + name + "], [status: " + statusString 
                + "], maximum power consumption = [" + maxPowerConsumption + "], [" 
                + criticalString + "]";
    }

}
