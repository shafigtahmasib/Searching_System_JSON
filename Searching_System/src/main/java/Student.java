import java.util.UUID;

public class Student {
     String id;
     String name;
     String surname;
     String fatherName;
     String email;
     String phoneNum;
     static long idGenerator = 0;

    public Student() {
        this.id = UUID.randomUUID().toString();
    }

    public Student(String name, String surname, String fatherName, String email, String phoneNum) {
        this.name = name;
        this.surname = surname;
        this.fatherName = fatherName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return
                "Student id: " + id +
                "Name: " + name + '\t' +
                "Surname: " + surname + '\t' +
                "Father Name: " + fatherName + '\t' +
                "Email: " + email + '\t' +
                "Phone number: " + phoneNum + '\t' +
                '}';
    }
}
