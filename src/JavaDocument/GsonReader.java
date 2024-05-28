package JavaDocument;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GsonReader {
    private static final String JSON_FILE_PATH = "students.json";
    private static List<Student> students;

    static {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            students = new Gson().fromJson(reader, new TypeToken<List<Student>>(){}.getType());
        } catch (IOException e) {
            students = new ArrayList<>();
            e.printStackTrace();
        }
    }

    public static void addNewStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    private static void saveToFile() {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            new Gson().toJson(students, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void searchByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println(student);
            }
        }
    }

    public static void searchByEmail(String email) {
        for (Student student : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                System.out.println(student);
            }
        }
    }
}
