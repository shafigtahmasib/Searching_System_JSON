import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main implements ICRUD {

    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.listInitializer();
        main.menu();
    }

    /***
     * Program run olanda json file'indaki datalari liste yighir.
     * @throws IOException
     */

    void listInitializer() throws IOException {

        String jsonString = new String(Files.readAllBytes(Paths.get("jj.json")));
        JSONArray jsonArr = new JSONArray(jsonString);
        students.clear();
        for (int i = 0; i < jsonArr.length(); i++) {

            JSONObject jsonObj = jsonArr.getJSONObject(i);
            Student student = new Student();

            student.id = jsonObj.getString("id");
            student.name = jsonObj.getString("name");
            student.surname = jsonObj.getString("surname");
            student.fatherName = jsonObj.getString("fatherName");
            student.email = jsonObj.getString("email");
            student.phoneNum = jsonObj.getString("phoneNum");

            students.add(student);
        }
    }

    void emailChecker(String email){
        Scanner scan =  new Scanner(System.in);
        while (!email.matches("^\\S+@\\S+$")) {
            System.out.println("Enter correct email: ");
            email = scan.nextLine();
        }
    }

    /***
     * Search function'larda hansi field'e gore search olunursa evvelce hemin field'e gore sort olunur ki,
     * search daha suretli ishlesin.
     */

    void searchByName() {
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("Enter name to search: ");
        Scanner scan = new Scanner(System.in);
        String search = scan.nextLine();
        for (Student x : students) {
            if (x.getName().startsWith(search))
                System.out.println(x);
        }
        menu();
    }

    void searchBySurname() {
        students.sort(Comparator.comparing(Student::getSurname));
        System.out.println("Enter surname to search: ");
        Scanner scan = new Scanner(System.in);
        String search = scan.nextLine();
        for (Student x : students) {
            if (x.getSurname().startsWith(search))
                System.out.println(x);
        }
        menu();
    }

    void searchByFatherName() {
        students.sort(Comparator.comparing(Student::getFatherName));
        System.out.println("Enter father name to search: ");
        Scanner scan = new Scanner(System.in);
        String search = scan.nextLine();
        for (Student x : students) {
            if (x.getFatherName().startsWith(search))
                System.out.println(x);
        }
        menu();
    }

    void menu() {
        Main main = new Main();
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose the operation: 1 for Students, 2 for Search, 3 to exit  \n");
        try {
            int operation = scan.nextInt();
            while (operation != 1 && operation != 2 && operation != 3) {
                System.out.println("Not such an operation, enter valid number: \n");
                operation = scan.nextInt();
            }

            if (operation == 1) {
                System.out.println("Choose one of the operations: \n 1: Add new student \n 2: Edit student \n 3: Remove student \n 4: Show all students \n 5: Exit \n");
                int productOperation = scan.nextInt();
                while (productOperation < 1 || productOperation > 5) {
                    System.out.println("Not such an operation, enter valid number: \n");
                    productOperation = scan.nextInt();
                }
                switch (productOperation) {
                    case 1:
                        main.add();
                        break;
                    case 2:
                        main.update();
                        break;
                    case 3:
                        main.delete();
                        break;
                    case 4:
                        main.get();
                        break;
                    case 5:
                        System.exit(0);

                }
            }
            if (operation == 2) {
                System.out.println("Choose search operation: \n 1: Search by name \n 2: Search by surname \n 3: Search by father's name \n 4: Exit \n");
                int saleOperation = scan.nextInt();
                while (saleOperation < 1 || saleOperation > 9) {
                    System.out.println("Not such an operation, enter valid number: \n");
                    saleOperation = scan.nextInt();
                }
                switch (saleOperation) {
                    case 1:
                        main.searchByName();
                        break;
                    case 2:
                        main.searchBySurname();
                        break;
                    case 3:
                        main.searchByFatherName();
                        break;
                    case 4:
                        System.exit(0);
                }
            }

            if (operation == 3) {
                System.exit(0);
            }
        }catch (Exception e){
            System.out.println("Input type is not correct \n");
        }

    }

    @Override
    public void add() throws IOException {
        Student student = new Student();
        Scanner scan = new Scanner(System.in);
        System.out.println("Name of student: ");
        String name = scan.nextLine();
        student.setName(name);
        System.out.println("Surname of student: ");
        String surName = scan.nextLine();
        student.setSurname(surName);
        System.out.println("Father name of student: ");
        String fatherName = scan.nextLine();
        student.setFatherName(fatherName);
        System.out.println("Email of student: ");
        String email = scan.nextLine();
        emailChecker(email);
        for(Student x: students){
            if(x.getEmail().equals(email)){
                System.out.println("Email exits...");
                System.exit(0);
            }
            student.setEmail(email);
        }

        System.out.println("Phone number of student: ");
        String phoneNum = scan.nextLine();

        while (!phoneNum.matches("\\+994(50|55|51|70|77)[0-9]{7}")) {
            System.out.println("Enter correct phone number: ");
            phoneNum = scan.nextLine();
        }
        for(Student x: students){
            if(x.getPhoneNum().equals(phoneNum)){
                System.out.println("Phone number exits...");
                System.exit(0);
            }
            student.setPhoneNum(phoneNum);
        }

        students.add(student);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("jj.json"), students);
        //String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(students);
        //JSONArray jsonArr = new JSONArray(jsonString);
        menu();
    }

    @Override
    public void get() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(new File("jj.json"), students);
        for (Student x : students) {
            System.out.println(x);
        }
        menu();
    }

    @Override
    public void update() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter email of student: ");
        String searchEmail = scan.nextLine();
        System.out.println("Select field to update: \n 1 for name \n 2 for surname \n 3 for father name \n 4 for email \n 5 for phone number \n");
        int operation = scan.nextInt();
        while (operation < 1 || operation > 5) {
            System.out.println("Not such an operation, select valid one: \n");
            operation = scan.nextInt();
        }
        Scanner scan1 = new Scanner(System.in);
        System.out.println("Enter new value: ");
        String newValue = scan1.nextLine();
        if(operation==1){
            for(Student x: students){
                if(x.email.equals(searchEmail)){
                    x.setName(newValue);
                }
            }
        }

        if(operation==2){
            for(Student x: students){
                if(x.email.equals(searchEmail)){
                    x.setSurname(newValue);
                }
            }
        }

        if(operation==3){
            for(Student x: students){
                if(x.email.equals(searchEmail)){
                    x.setFatherName(newValue);
                }
            }
        }

        if(operation==4){
            for(Student x: students){
                emailChecker(newValue);
                if(x.email.equals(searchEmail)){
                    x.setEmail(newValue);
                }
            }
        }

        if(operation==5){
            for(Student x: students){
                while (!newValue.matches("\\+994(50|55|51|70|77)[0-9]{7}")) {
                    System.out.println("Enter correct phone number: ");
                    newValue = scan.nextLine();
                }
                if(x.email.equals(searchEmail)){
                    x.setEmail(newValue);
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("jj.json"), students);
//        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(students);
//        JSONArray jsonArr = new JSONArray(jsonString);
        menu();
    }

    @Override
    public void delete() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter email of student: ");
        String searchEmail = scan.nextLine();
        emailChecker(searchEmail);
        int count = 0;
        for(Student x: students){
            if(x.email.equals(searchEmail)){
                count++;
            }
        }
        if(count==0) System.out.println("There is no such a student \n");
        students.removeIf(x -> x.email.equals(searchEmail));
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("jj.json"), students);
//        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(students);
//        JSONArray jsonArr = new JSONArray(jsonString);
        menu();

    }
}