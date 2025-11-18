
//importing arraylist to use arraylist
import java.util.ArrayList;

public class Room {

    private String code;  // Unique code for the room for ecample "K1F"
    private String description;  // Description of the room
    private ArrayList<Device> devicesList;  // arraylist of devices in the room

    // this is the constructor
    public Room(String code, String description) {
        this.code = code;
        this.description = description;
        this.devicesList = new ArrayList<Device>();
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Device> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(ArrayList<Device> devicesList) {
        this.devicesList = devicesList;
    }

    // Returns the number of Light devices in the room
    public int getNbLights() {
        int count = 0;
      //for every device d in the arraylist devicelist
      //if d belong to the subclass light, count should be incremented
      //we use instanceof to know if an elemt belong to a subclass or class
        for (Device d : devicesList) {
            if (d instanceof Light) {
                count++;
            }
        }
        return count;
    }

    // Returns the number of Appliance devices in the room
    public int getNbAppliances() {
        int count = 0;
      //for every device d in the arraylist devicelist
      //if d belong to the subclass appliance, count should be incr	emented
      //we use instanceof to know if an elemt belong to a subclass or class
        for (Device d : devicesList) {
            if (d instanceof Appliance) {
                count++;
            }
        }
        return count;
    }

    // Returns total current power consumption of all devices in the room
    public double getCurrentComsuption() {
        double total = 0;
        for (Device d : devicesList) {
            total += d.getCurrentConsumption();
        }
        return total;
    }

    // function to add a device to the room
    public void addDevice(Device d) {
        devicesList.add(d);
    }

    // function to remove a device from the room
    public void removeDevice(Device d) {
        devicesList.remove(d);
    }

    // Searches for a device by its id
    public Device searchDeviceById(int id) {
        for (Device d : devicesList) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;  // then the device is not found
    }
    
 // Removes a device by its ID
    public boolean removeDeviceById(int id) {
        for (Device d : devicesList) {
            if (d.getId() == id) {
                devicesList.remove(d);
                return true;   // Device found and removed
            }
        }
        return false;  // Device not found
    }


 // Returns detailed information about the room and all devices
    @Override
    public String toString() {
        String result = "Room Code: [" + code + "], Description: [" + description + "]\n";
        result += "Devices in Room:\n";
        for (Device d : devicesList) {
            result += d.toString() + "\n";
        }
        return result;
    }

    // Returns a brief summary of the room
    public String toBreifString() {
        int totalDevices = devicesList.size();
        int lights = getNbLights();
        int appliances = getNbAppliances();

        return "Room Code: [" + code + "], Total Devices: [" + totalDevices + "], Lights: [" 
                + lights + "], Appliances: [" + appliances + "]";
    }
    
}


