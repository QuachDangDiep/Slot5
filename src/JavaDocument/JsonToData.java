package JavaDocument;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.List;
import java.io.IOException;

public class JsonToData {
    private static final String JSON_FILE_PATH = "students.json";
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        List<Student> students = readDataFromJson();

        saveDataToDatabase(students);
    }

    private static List<Student> readDataFromJson() {
        List<Student> students = null;
        try (FileReader reader = new FileReader(JSON_FILE_PATH )) {
            students = gson.fromJson(reader, new TypeToken<List<Student>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    private static void saveDataToDatabase(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("No student data to save.");
            return;
        }

        for (Student student : students) {
            try {
                if (!DBConnection.studentExists(student.getId())) {
                    DBConnection.insertStudent(student);
                    System.out.println("Student with ID " + student.getId() + " inserted into the database.");
                } else {
                    System.out.println("Student with ID " + student.getId() + " already exists in the database.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
