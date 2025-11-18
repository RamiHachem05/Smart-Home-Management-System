//instead of using case 1 : 
//						system.out.print(something)
//						break
//i used case 1 -> System.out.println("something");
//The arrow is used to make switch cases cleaner and no need to break every time

import java.util.*;

public class ManagementSystem {

	//adding attributes
    private String adminPassword;
    private String userPassword;
    private ArrayList<Room> rooms;
    private double maxAllowedPower;
    private boolean day;
    private ArrayList<Device> waitingListDay;
    private ArrayList<Device> waitingListPower;

    public static final double LOW = 1000;
    public static final double NORMAL = 4000;
    public static final double HIGH = 10000;

    //constructor
    public ManagementSystem(String adminPassword, String userPassword) {
        this.adminPassword = isValidPassword(adminPassword) ? adminPassword : "Admin123";
        this.userPassword = isValidPassword(userPassword) ? userPassword : "User123";
        this.rooms = new ArrayList<>();
        this.maxAllowedPower = NORMAL;
        this.day = true;
        this.waitingListDay = new ArrayList<>();
        this.waitingListPower = new ArrayList<>();
    }

    //function to check if password is valid
    private boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        boolean hasUpper = false, hasLower = false, hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        return hasUpper && hasLower && hasDigit;
    }

    //getter and setter
    public void setAdminPassword(String newPass) {
        if (isValidPassword(newPass)) this.adminPassword = newPass;
        else System.out.println("Invalid admin password format.");
    }

    public void setUserPassword(String newPass) {
        if (isValidPassword(newPass)) this.userPassword = newPass;
        else System.out.println("Invalid user password format.");
    }

    //function to change admin password
    public void changeAdminPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter current admin password: ");
        if (sc.nextLine().equals(adminPassword)) {
            System.out.print("Enter new admin password: ");
            setAdminPassword(sc.nextLine());
        } else {
            System.out.println("Incorrect password.");
        }
    }

    
    //function to change user password
    public void changeUserPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter current user password: ");
        if (sc.nextLine().equals(userPassword)) {
            System.out.print("Enter new user password: ");
            setUserPassword(sc.nextLine());
        } else {
            System.out.println("Incorrect password.");
        }
    }

    //function to search room by code
    public Room searchRoomByCode(String code) {
        for (Room r : rooms) {
            if (r.getCode().equalsIgnoreCase(code)) return r;
        }
        return null;
    }

    //function to add room
    public void addRoom(Room room) {
        rooms.add(room);
    }

    //function to remove room
    public void removeRoom(String code) {
        Room room = searchRoomByCode(code);
        if (room != null) rooms.remove(room);
        else System.out.println("Room not found.");
    }

    //function to set time to day;
    public void setDayTime() {
        this.day = true;
        System.out.println("System set to Day Mode.");
    }

    //function to time to night
    public void setNightTime() {
        this.day = false;
        System.out.println("System set to Night Mode.");
    }

    //function to change power mode
    public void changePowerMode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Power Mode: 1. LOW 2. NORMAL 3. HIGH");
        int choice = sc.nextInt();
        maxAllowedPower = (choice == 1) ? LOW : (choice == 3) ? HIGH : NORMAL;
        System.out.println("Power mode set.");
    }

    //function to display info
    public void displayInfo() {
        System.out.println("Power Mode: " + maxAllowedPower);
        System.out.println("Time Mode: " + (day ? "Day" : "Night"));
        System.out.println("Number of Rooms: " + rooms.size());
    }

    //function to display the summary of room
    public void displaySummaryAllRooms() {
        for (Room r : rooms) System.out.println(r.toBreifString());
    }

    //function to display details of one room
    public void displayDetailsOneRoom(String code) {
        Room r = searchRoomByCode(code);
        System.out.println((r != null) ? r.toString() : "Room not found.");
    }

    //function to display the main menu
    public void displayMainMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Admin Mode\n2. User Mode\n3. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> enterAdminMode();
                case 2 -> displayUserMenu();
                case 3 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    //function when we enter admin mode
    private void enterAdminMode() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Admin Password: ");
        if (!sc.nextLine().equals(adminPassword)) {
            System.out.println("Incorrect password.");
            return;
        }
        int choice;
        do {
            System.out.println("1.Change Admin\n2.Change User\n3.Power Mode\n4.Day/Night\n5.Add Room\n6.Remove Room\n7.Summary\n8.Exit");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> changeAdminPassword();
                case 2 -> changeUserPassword();
                case 3 -> changePowerMode();
                case 4 -> {
                    System.out.print("Enter (day/night): ");
                    if (sc.nextLine().equalsIgnoreCase("day")) setDayTime();
                    else setNightTime();
                }
                case 5 -> {
                    System.out.print("Code: "); String code = sc.nextLine();
                    System.out.print("Description: "); String desc = sc.nextLine();
                    addRoom(new Room(code, desc));
                }
                case 6 -> {
                    System.out.print("Enter code: "); removeRoom(sc.nextLine());
                }
                case 7 -> displaySummaryAllRooms();
                case 8 -> System.out.println("Exiting admin mode.");
                default -> System.out.println("Invalid.");
            }
        } while (choice != 8);
    }

    //function to display user menuu
    private void displayUserMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1.Rooms Info\n2.Devices Info\n3.Power Status\n4.Back");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> displaySummaryAllRooms();
                case 2 -> {
                    for (Room r : rooms) {
                        for (Device d : r.getDevicesList()) System.out.println(d);
                    }
                }
                case 3 -> checkCurrentPowerConsumption();
                case 4 -> System.out.println("Exiting user mode.");
                default -> System.out.println("Invalid.");
            }
        } while (choice != 4);
    }

    //function to check the current power consumption
    public void checkCurrentPowerConsumption() {
        double total = 0;
        for (Room r : rooms) total += r.getCurrentComsuption();
        System.out.println("Current total consumption: " + total + " watts");
    }
    
    public void addDeviceToRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Room Code: ");
        Room room = searchRoomByCode(sc.nextLine());

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.print("Enter Device ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Device Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Max Power Consumption: ");
        double power = sc.nextDouble();
        System.out.print("Is Critical? (true/false): ");
        boolean critical = sc.nextBoolean();
        sc.nextLine();
        System.out.print("Type (L for Light / A for Appliance): ");
        String type = sc.nextLine();

        if (type.equalsIgnoreCase("L")) {
            System.out.print("Is Adjustable? (true/false): ");
            boolean adjustable = sc.nextBoolean();
            room.addDevice(new Light(id, name, power, critical, adjustable));
        } else if (type.equalsIgnoreCase("A")) {
            System.out.print("Number of Power Levels: ");
            int n = sc.nextInt();
            int[] levels = new int[n];
            System.out.println("Enter power levels (%): ");
            for (int i = 0; i < n; i++) levels[i] = sc.nextInt();
            System.out.print("Is Noisy? (true/false): ");
            boolean noisy = sc.nextBoolean();
            room.addDevice(new Appliance(id, name, power, critical, levels, noisy));
        } else {
            System.out.println("Invalid device type.");
            return;
        }

        System.out.println("Device added successfully.");
    }

    
    public void deleteDeviceFromRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Room Code: ");
        Room room = searchRoomByCode(sc.nextLine());

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.print("Enter Device ID to remove: ");
        int id = sc.nextInt();
        boolean removed = room.removeDeviceById(id);

        if (removed) {
            System.out.println("Device removed successfully.");
        } else {
            System.out.println("Device not found.");
        }
    }

    public void searchDeviceInRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Room Code: ");
        Room room = searchRoomByCode(sc.nextLine());

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.print("Enter Device ID to search: ");
        int id = sc.nextInt();
        Device d = room.searchDeviceById(id);

        if (d != null) {
            System.out.println(d);
        } else {
            System.out.println("Device not found in this room.");
        }
    }
    
    
    
    //now function for control mode
    public void checkAllRoomsInfo() {
        displaySummaryAllRooms();
    }

    
    
    //function to check the information of the devices 
    public void checkAllDevicesInfo() {
        for (Room r : rooms) {
            for (Device d : r.getDevicesList()) {
                System.out.println(d);
            }
        }
    }

    
    //function to check the running devices
    public void checkRunningDevices() {
        for (Room r : rooms) {
            for (Device d : r.getDevicesList()) {
                if (d.getStatus() == Device.StatusON) {
                    System.out.println(d);
                }
            }
        }
    }

    //function to check devices in waiting list
    public void checkStandbyDayList() {
        if (waitingListDay.isEmpty()) {
            System.out.println("No devices in Day Waiting List.");
        } else {
            for (Device d : waitingListDay) {
                System.out.println(d);
            }
        }
    }
    
    //function to check devices in the power waiting list
    public void checkStandbyPowerList() {
        if (waitingListPower.isEmpty()) {
            System.out.println("No devices in Power Waiting List.");
        } else {
            for (Device d : waitingListPower) {
                System.out.println(d);
            }
        }
    }

    
    //function to search for a room
    public void searchForRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Room Code to search: ");
        Room room = searchRoomByCode(sc.nextLine());

        if (room != null) {
            System.out.println(room);
        } else {
            System.out.println("Room not found.");
        }
    }

    //function to search for a device
    public void searchForDevice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Room Code: ");
        Room room = searchRoomByCode(sc.nextLine());

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.print("Enter Device ID: ");
        int id = sc.nextInt();
        Device device = room.searchDeviceById(id);

        if (device != null) {
            System.out.println(device);
        } else {
            System.out.println("Device not found in this room.");
        }
    }


    //function to turn on a device
    public void turnOnDevice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Room Code: ");
        Room room = searchRoomByCode(sc.nextLine());

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.print("Enter Device ID to turn on: ");
        int id = sc.nextInt();
        Device device = room.searchDeviceById(id);

        if (device == null) {
            System.out.println("Device not found.");
            return;
        }

        // Check if device is already on
        if (device.getStatus() == Device.StatusON) {
            System.out.println("Device is already ON.");
            return;
        }

        double currentPower = getTotalPowerConsumption();
        double devicePower = device.getMaxPowerConsumption();

        // If it's a Light and adjustable, ask for level
        if (device instanceof Light) {
            Light light = (Light) device;
            if (light.isAdjustable()) {
                System.out.print("Enter light intensity (0-100): ");
                int level = sc.nextInt();
                light.turnOn(level);
                devicePower = light.getCurrentConsumption();
            } else {
                light.turnOn();
            }
        }
        // If it's an Appliance, ask for level
        else if (device instanceof Appliance) {
            Appliance app = (Appliance) device;
            System.out.print("Enter power level (0 to " + (app.getPowerLevels().length - 1) + "): ");
            int level = sc.nextInt();
            app.turnOn(level);
            devicePower = app.getCurrentConsumption();

            // Handle noisy appliance at night
            if (!day && app.isNoisy()) {
                System.out.println("Warning: This is a noisy device and it's night mode.");
                System.out.println("1. Turn on anyway\n2. Put in Day Waiting List\n3. Cancel");
                int option = sc.nextInt();
                if (option == 2) {
                    app.setStatus(Device.StatusSTANDBY);
                    waitingListDay.add(app);
                    System.out.println("Device added to Day Waiting List.");
                    return;
                } else if (option == 3) {
                    System.out.println("Operation cancelled.");
                    return;
                }
            }
        } else {
            device.turnOn(); //if everything is fine just turn on the device
        }

        // Check Power Constraint
        if ((currentPower + devicePower) > maxAllowedPower) {
            System.out.println("Not enough power available to turn on this device."); //checking if there is enough power
            System.out.println("1. Add to Power Waiting List\n2. Cancel");
            int option = sc.nextInt();
            if (option == 1) {
                device.setStatus(Device.StatusSTANDBY);
                waitingListPower.add(device);
                System.out.println("Device added to Power Waiting List.");
            } else {
                System.out.println("Operation cancelled.");
            }
        } else {
            device.setStatus(Device.StatusON);
            System.out.println("Device turned ON successfully.");
        }
    }

    // function to get the total power consumption
    private double getTotalPowerConsumption() {
        double total = 0;
        for (Room r : rooms) {
            total += r.getCurrentComsuption();
        }
        return total;
    }

    //function to turn off a device
    public void turnOffDevice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Room Code: ");
        Room room = searchRoomByCode(sc.nextLine());

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.print("Enter Device ID to turn off: ");
        int id = sc.nextInt();
        Device device = room.searchDeviceById(id);

        if (device == null) {
            System.out.println("Device not found.");
            return;
        }

        if (device.isCritical()) {
            System.out.println("This is a critical device! Please enter Admin Password to confirm:");
            sc.nextLine();  // consume newline
            String pass = sc.nextLine();
            if (!pass.equals(adminPassword)) {
                System.out.println("Incorrect password. Operation cancelled.");
                return;
            }
        }

        device.turnOff();
        System.out.println("Device turned OFF.");
        checkPowerWaitingList();  // See if we can now turn on waiting devices
    }

    // function to turn off all devices in a room
    public void turnOffAllDevicesInRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Room Code: ");
        Room room = searchRoomByCode(sc.nextLine());

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        for (Device d : room.getDevicesList()) {
            d.turnOff();
        }
        System.out.println("All devices in room turned OFF.");
        checkPowerWaitingList();
    }

    // function to turn off all devices in the whole house
    public void turnOffAllDevicesInHouse() {
        for (Room r : rooms) {
            for (Device d : r.getDevicesList()) {
                d.turnOff();
            }
        }
        System.out.println("All devices in the house turned OFF.");
        checkPowerWaitingList();
    }

    // function to check the current power consumption in watts
    public void checkCurrentPowerConsumption1() {
        System.out.println("Current total consumption: " + getTotalPowerConsumption() + " watts");
    }

    // function to set day or night mode
    public void setDayOrNightMode() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter mode (day/night): ");
        String mode = sc.nextLine();
        //usage of .equalsIgnoreCase in case the input was in upper cases.
        if (mode.equalsIgnoreCase("day")) {
            setDayTime();
            handleDayWaitingList();
        } else if (mode.equalsIgnoreCase("night")) {
            setNightTime();
            handleNoisyDevicesAtNight();
        } else {
            System.out.println("Invalid input.");
        }
    }

    // function to check power waiting list
    // Using Iterator to safely remove devices from the list while looping.
    // Regular loops cause errors if you remove items during iteration.
    private void checkPowerWaitingList() {
        Iterator<Device> iterator = waitingListPower.iterator();
        while (iterator.hasNext()) {
            Device d = iterator.next();
            if ((getTotalPowerConsumption() + d.getCurrentConsumption()) <= maxAllowedPower) {
                d.setStatus(Device.StatusON);
                System.out.println("Device from Power Waiting List turned ON: " + d.getName());
                iterator.remove();
            }
        }
    }

    
    private void handleDayWaitingList() {
        Iterator<Device> iterator = waitingListDay.iterator();
        while (iterator.hasNext()) {
            Device d = iterator.next();
            if ((getTotalPowerConsumption() + d.getCurrentConsumption()) <= maxAllowedPower) {
                d.setStatus(Device.StatusON);
                System.out.println("Device from Day Waiting List turned ON: " + d.getName());
                iterator.remove();
            } else {
                waitingListPower.add(d);
                iterator.remove();
            }
        }
    }

    
    private void handleNoisyDevicesAtNight() {
        for (Room r : rooms) {
            for (Device d : r.getDevicesList()) {
                if (d instanceof Appliance && ((Appliance) d).isNoisy() && d.getStatus() == Device.StatusON) {
                    System.out.println("Noisy device running: " + d.getName());
                    System.out.println("1. Turn Off\n2. Standby (Day Waiting List)\n3. Keep On");
                    Scanner sc = new Scanner(System.in);
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        d.turnOff();
                    } else if (choice == 2) {
                        d.setStatus(Device.StatusSTANDBY);
                        waitingListDay.add(d);
                    }
                }
            }
        }
    }

    // the main function when the output is enter control mode
    //the user need to input the number related to the operation
    public void enterControlMode() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== CONTROL MODE ======");
            System.out.println("1. Check All Rooms Info");
            System.out.println("2. Check All Devices Info");
            System.out.println("3. Check Running Devices");
            System.out.println("4. Check Standby Devices (Day Waiting List)");
            System.out.println("5. Check Standby Devices (Power Waiting List)");
            System.out.println("6. Search for a Room");
            System.out.println("7. Search for a Device");
            System.out.println("8. Turn On a Device");
            System.out.println("9. Turn Off a Device");
            System.out.println("10. Turn Off All Devices in a Room");
            System.out.println("11. Turn Off All Devices in the House");
            System.out.println("12. Check Current Power Consumption");
            System.out.println("13. Set Day/Night Mode");
            System.out.println("14. Exit Control Mode");
            System.out.print("Select an option: ");

            choice = sc.nextInt();
            sc.nextLine();
            //relating every  number to its necessary function
            switch (choice) {
                case 1 -> checkAllRoomsInfo();
                case 2 -> checkAllDevicesInfo();
                case 3 -> checkRunningDevices();
                case 4 -> checkStandbyDayList();
                case 5 -> checkStandbyPowerList();
                case 6 -> searchForRoom();
                case 7 -> searchForDevice();
                case 8 -> turnOnDevice();
                case 9 -> turnOffDevice();
                case 10 -> turnOffAllDevicesInRoom();
                case 11 -> turnOffAllDevicesInHouse();
                case 12 -> checkCurrentPowerConsumption();
                case 13 -> setDayOrNightMode();
                case 14 -> System.out.println("Exiting Control Mode...");
                default -> System.out.println("Invalid option.");
            }

        } while (choice != 14);
    }

    // the main function when the output is enter admin mode
    //the user need to input the number related to the operation
    public void enterAdminMode1() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Admin Password: ");
        String inputPass = sc.nextLine();
        //checking if the input is equal to the password so we can open the admin mode
        if (!inputPass.equals(adminPassword)) {
            System.out.println("Incorrect password. Returning to main menu...");
            return;
        }

        int choice;
        do {
            System.out.println("\n====== ADMIN MODE ======");
            System.out.println("1. Change Admin Password");
            System.out.println("2. Change User Password");
            System.out.println("3. Change Power Mode");
            System.out.println("4. Set Day/Night Mode");
            System.out.println("5. Add/Delete/Search a Room");
            System.out.println("6. Add/Delete/Search a Device");
            System.out.println("7. Exit Admin Mode");
            System.out.print("Select an option: ");

            choice = sc.nextInt();
            sc.nextLine();
            //relating every number to its necessary function
            switch (choice) {
                case 1 -> changeAdminPassword();
                case 2 -> changeUserPassword();
                case 3 -> changePowerMode();
                case 4 -> setDayOrNightMode();
                case 5 -> handleRoomOperations();
                case 6 -> handleDeviceOperations();
                case 7 -> System.out.println("Exiting Admin Mode...");
                default -> System.out.println("Invalid option.");
            }

        } while (choice != 7);
    }

    //function to add delete or search for a room
    private void handleRoomOperations() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add Room\n2. Delete Room\n3. Search Room");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1 -> {
                System.out.print("Enter Room Code: ");
                String code = sc.nextLine();
                System.out.print("Enter Description: ");
                String desc = sc.nextLine();
                addRoom(new Room(code, desc));
                System.out.println("Room added.");
            }
            case 2 -> {
                System.out.print("Enter Room Code to delete: ");
                removeRoom(sc.nextLine());
            }
            case 3 -> searchForRoom();
            default -> System.out.println("Invalid option.");
        }
    }

    // function to add delete and search for a device
    private void handleDeviceOperations() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add Device\n2. Delete Device\n3. Search Device");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1 -> addDeviceToRoom();
            case 2 -> deleteDeviceFromRoom();
            case 3 -> searchForDevice();
            default -> System.out.println("Invalid option.");
        }
    }

    
    
}
