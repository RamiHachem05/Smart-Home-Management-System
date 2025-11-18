
//light is a subclass of device so light extends device
public class Light extends Device {
    
    private boolean adjustable;  // True if the light is adjustable
    private int level;  // Intensity level between 0 ad 100 , default is 100

    // Constructor for non-adjustable light
    public Light(int id, String name, double maxPowerConsumption) {
        super(id, name, maxPowerConsumption);
        this.adjustable = false;
        this.level = 100;
    }

    // Constructor for adjustable option
    public Light(int id, String name, double maxPowerConsumption, boolean adjustable) {
        super(id, name, maxPowerConsumption);
        this.adjustable = adjustable;
        this.level = 100;
    }

    // Constructor with critical and adjustable options
    public Light(int id, String name, double maxPowerConsumption, boolean critical, boolean adjustable) {
        super(id, name, maxPowerConsumption, critical);
        this.adjustable = adjustable;
        this.level = 100;
    }

    // Getters and Setters
    public boolean isAdjustable() {
        return adjustable;
    }

    public void setAdjustable(boolean adjustable) {
        this.adjustable = adjustable;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level >= 0 && level <= 100) {
            this.level = level;
        }
    }

    //function turnOn to set light at 100% intensity
    @Override
    public void turnOn() {
        super.setStatus(StatusON);
        this.level = 100;
    }

    // turnOn to turn on at specific intensity
    public void turnOn(int level) {
        if (adjustable) {
            setLevel(level);
        } else {
            this.level = 100;  // Non-adjustable lights always full
        }
        super.setStatus(StatusON);
    }

    // getCurrentConsumption based on intensity
    @Override
    public double getCurrentConsumption() {
        if (getStatus() == StatusON) {
            if (adjustable) {
                return (getMaxPowerConsumption() * level) / 100.0;
            } else {
                return getMaxPowerConsumption();
            }
        } else {
            return 0;
        }
    }

    // toString to display
    @Override
    public String toString() {
        String statusString;

        if (getStatus() == StatusON) {
            statusString = "On";
        } else if (getStatus() == StatusOFF) {
            statusString = "Off";
        } else {
            statusString = "Standby";
        }

        String criticalString;
        if (isCritical())
            criticalString = "critical";
        else 
            criticalString = "not critical";
        

        String adjustableString;
        if (adjustable)
            adjustableString = "adjustable";
        else
            adjustableString = "not adjustable";
        


        return "Light{ id=[" + getId() + "] , name=[" + getName() + "], [status: " + statusString
                + "], maximum power consumption = [" + getMaxPowerConsumption() + "], [" 
                + criticalString + "], [" + adjustableString + "], level = [" + level + "] }";
    }
}
