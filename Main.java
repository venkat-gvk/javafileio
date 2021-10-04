import java.util.*;
import java.io.*;  

// driver main
class Main {
    public static void main(String[] args) throws Exception {
        boolean opt = true;
        Scanner sc = new Scanner(System.in);

        System.out.println("\t\t CRUD Operations\n\n");
        
        while (opt) {
            System.out.println("1. Create\n2. Read\n3. Update\n4. Delete\n5. Exit\n");
            System.out.println("Your choice\n");

            int choice = sc.nextInt();

            switch(choice) {
                case 1: 
                    System.out.println("Enter Details");
                    
                    var details = getDetails();
                    CRUD.create(details);

                    break;
                
                case 2: 
                    CRUD.read();
                    break;
                
                case 3: 
                    CRUD.update();
                    break;
                
                case 4: 
                    CRUD.delete();
                    break;
                
                case 5: 
                    opt = false;
                    System.out.println("Exiting...");
                    break;
            }
        } 
    }

    // get user details

    public static Details getDetails() {
        Scanner sc = new Scanner(System.in);
        
        String name;
        String phone_no;
        int age;
        String designation;

        System.out.println();
        
        System.out.println("Enter Name");
        name = sc.nextLine();

        System.out.println("Enter Age");
        age = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter phone no");
        phone_no = sc.nextLine();
        
        System.out.println("Enter designation");
        designation = sc.nextLine();

        return new Details(name, age, phone_no, designation);
    }
}