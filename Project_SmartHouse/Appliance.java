
//importing array so we can use the array
import java.util.Arrays;

//appliance is a subclass of device so appliance extends device
public class Appliance extends Device {

    private int[] powerLevels;  // array of power consumption percentages
    private int currentLevel;   // current power level,default is 0;
    private boolean noisy;      // true if the appliance is noisy

    // Constructor without critical
    public Appliance(int id, String name, double maxPowerConsumption, int[] powerLevels, boolean noisy) {
        super(id, name, maxPowerConsumption);
        this.powerLevels = sortPowerLevels(powerLevels);
        this.noisy = noisy;
        this.currentLevel = 0;
    }

    // Constructor with critical 
    public Appliance(int id, String name, double maxPowerConsumption, boolean critical, int[] powerLevels, boolean noisy) {
        super(id, name, maxPowerConsumption, critical);
        this.powerLevels = sortPowerLevels(powerLevels);
        this.noisy = noisy;
        this.currentLevel = 0;
    }

    // array must e sorted in an ascending order so here is a function to sort it.
    private int[] sortPowerLevels(int[] levels) {
        Arrays.sort(levels); 
        return levels;
    }

    // Getters and Setters
    public int[] getPowerLevels() {
        return powerLevels;
    }

    public void setPowerLevels(int[] powerLevels) {
        this.powerLevels = sortPowerLevels(powerLevels);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        if (currentLevel >= 0 && currentLevel < powerLevels.length) {
            this.currentLevel = currentLevel;
        }
    }

    public boolean isNoisy() {
        return noisy;
    }

    public void setNoisy(boolean noisy) {
        this.noisy = noisy;
    }

    // turnOn to turn on at level 0
    @Override
    public void turnOn() {
        super.setStatus(StatusON);
        this.currentLevel = 0;
    }

    // Overload turnOn to turn on at a specific power level
    public void turnOn(int level) {
        if (level >= 0 && level < powerLevels.length) {
            this.currentLevel = level;
            super.setStatus(StatusON);
        }
    }

    // Override getCurrentConsumption
    @Override
    public double getCurrentConsumption() {
        if (getStatus() == StatusON) {
            return (getMaxPowerConsumption() * powerLevels[currentLevel]) / 100.0;
        } else {
            return 0;
        }
    }

    // toString method following the required format
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
        if (isCritical()) {
            criticalString = "critical";
        } else {
            criticalString = "not critical";
        }

        String noisyString;
        if (noisy) {
            noisyString = "noisy";
        } else {
            noisyString = "not noisy";
        }

        return "Appliance{ id=[" + getId() + "] , name=[" + getName() + "], [status: " + statusString
                + "], maximum power consumption = [" + getMaxPowerConsumption() + "], [" + criticalString
                + "], power Levels = " + Arrays.toString(powerLevels)
                + " , level = [" + currentLevel + "], [" + noisyString + "] }";
    }
}
