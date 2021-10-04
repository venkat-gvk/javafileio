import java.io.*;
import java.util.*;

class CRUD {

    // flags for whether the user chooses id or phone_no

    static boolean del_flag = true;
    static boolean update_flag = true;

    // create Record

    public static void create(Details details) {
        try {
            File file = new File("details.csv");
            
            if (!file.exists())
                file.createNewFile();
            
            // create writer 

            FileWriter writer = new FileWriter(file, true);
            writer.append(String.valueOf(details.getID()));
            writer.append(", ");

            writer.append(details.getName());
            writer.append(", ");

            writer.append(String.valueOf(details.getAge()));
            writer.append(", ");

            writer.append(details.getPhoneNo());
            writer.append(", ");

            writer.append(details.getDesignation());
            writer.append("\n");

            writer.close();

            System.out.println(details.getName() + " added successfully with generated ID: " + details.getID());
            System.out.println();
        } 
        
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("Something wrong with our end. Please try again sometime");
        }
    }

    public static void read() {
        try {
            File file = new File("details.csv");
            
            if (!file.exists() || file.length() == 0) {
                System.out.println("No details found\n");
                return;
            }
            
            String line = "";

            var bf = new BufferedReader(new FileReader(file));
            
            while ((line = bf.readLine()) != null) {
                String[] employee = line.split(", "); 
                System.out.println(employee[0] + " " + employee[1] + " " + employee[2] + " " + employee[3] + " " + employee[4] + "\n");
            } 

            bf.close();
        } 
        
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("Something wrong with our end. Please try again sometime");
        }
    }

    // update method to change something in record

    public static void update() {

        var id = getDetails();
        boolean record;

        if (update_flag)
            record = modify(id, 0);
        else
            record = modify(id, 3);
        
        update_flag = true;
        
        if (record == false) {
            System.out.println("Not Found\n");
            return;
        }
        
        if (record == true)
            System.out.println("Updated successfully\n");
    }

    private static boolean modify(String id, int idx) {

        boolean found = true;

        try {
            File file = new File("details.csv");
            File temp = new File("details_temp.csv");

            if (!file.exists() || file.length() == 0) return false;
            
            FileWriter writer = new FileWriter(temp, true);
            
            String line = "";

            var bf = new BufferedReader(new FileReader(file));
            
            while ((line = bf.readLine()) != null) {
                String[] arr = line.split(", ");

                // if the record doesn't exist, simply copy it 
                if (!arr[idx].equals(id)) {
                    writer.append(line); 
                    writer.append("\n");
                    found = false;
                
                // if it does exist, patch the record
                } else {
                    String str;

                    System.out.println("Simply press no and enter to skip the fields");
                    System.out.println("For age alone press 0 to skip the field");

                    Details details = Main.getDetails();
                    String name = details.getName();
                    String phone_no = details.getPhoneNo();
                    String designation = details.getDesignation();
                    String age = String.valueOf(details.getAge());
                    
                    arr[1] = name;
                    arr[2] = age;                
                    arr[3] = phone_no;                
                    arr[4] = designation;

                    str = String.join(", ", arr[0], arr[1], arr[2], arr[3], arr[4]);
                    writer.append(str);
                    writer.append("\n");

                    found = true;
                }
            } 

            writer.close();
            bf.close();

            file.delete();
            
            temp.renameTo(file);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return found;
    }

    // Get id or phone_no to search

    private static String getDetails() {
        Scanner sc = new Scanner(System.in);
        
        String val;

        System.out.println("Id/phone_no");
        System.out.println("1. Id\n2. Phone no\n");
        
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.println("Enter id");
            val = sc.nextLine();

        } else {
            System.out.println("Enter phone_no");
            val = sc.nextLine();
            del_flag = false;
            update_flag = false;
        }
        return val;
    }

    private static boolean copy(String id, int idx) {

        boolean found = true;

        try {
            File file = new File("details.csv");
            File temp = new File("details_temp.csv");

            if (!file.exists() || file.length() == 0) return false;
            
            FileWriter writer = new FileWriter(temp, true);
            
            String line = "";

            var bf = new BufferedReader(new FileReader(file));
            
            while ((line = bf.readLine()) != null) {
                String f_id = line.split(", ")[idx];

                // if the record doesn't exist, simple copy the contents.
                if (!f_id.equals(id)) {
                    writer.append(line); 
                    writer.append("\n");
                    found = false;
                } 
            } 

            writer.close();
            bf.close();

            file.delete();
            
            temp.renameTo(file);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return found;
    }

    // delete record
    public static void delete() {   

        var id = getDetails();
        boolean record;

        if (del_flag)
            record = copy(id, 0);
        else
            record = copy(id, 3);
        
        del_flag = true;
        
        if (record == false) {
            System.out.println("Not Found\n");
            return;
        }
        
        if (record == true)
            System.out.println("Record Deleted\n");

    }
}