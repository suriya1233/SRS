package com.student.management.config;

import com.student.management.model.Student;
import com.student.management.model.UserAccount;
import com.student.management.model.UserRole;
import com.student.management.repository.StudentRepository;
import com.student.management.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedData(UserAccountRepository userRepo,
            StudentRepository studentRepo,
            PasswordEncoder encoder) {
        return args -> {
            try {
                // Initialize students first
                if (studentRepo.count() == 0) {
                    Student s1 = new Student();
                    s1.setStudentId("S001");
                    s1.setName("John Doe");
                    s1.setClassName("10-A");
                    s1.setAttendancePercent(92.0);
                    s1.setGrade("A");

                    Student s2 = new Student();
                    s2.setStudentId("S002");
                    s2.setName("Jane Smith");
                    s2.setClassName("10-A");
                    s2.setAttendancePercent(88.0);
                    s2.setGrade("B+");

                    studentRepo.save(s1);
                    studentRepo.save(s2);
                    System.out.println("Sample students initialized successfully");
                }

                // Initialize users
                if (userRepo.count() == 0) {
                    // Create admin user
                    UserAccount admin = new UserAccount();
                    admin.setUsername("admin");
                    admin.setPassword(encoder.encode("admin123"));
                    admin.setRole(UserRole.ADMIN);
                    admin.setStudentId(null); // Admin has no student ID
                    userRepo.save(admin);
                    System.out.println("Admin user initialized successfully");

                    // Create student users - must check if student exists
                    Student studentS001 = studentRepo.findByStudentId("S001").orElse(null);
                    if (studentS001 != null) {
                        UserAccount studentUser = new UserAccount();
                        studentUser.setUsername("S001");
                        studentUser.setPassword(encoder.encode("password"));
                        studentUser.setRole(UserRole.STUDENT);
                        studentUser.setStudentId(studentS001.getStudentId());
                        userRepo.save(studentUser);
                        System.out.println("Student user S001 initialized successfully");
                    } else {
                        System.err.println("WARNING: Student S001 not found, skipping user creation");
                    }

                    Student studentS002 = studentRepo.findByStudentId("S002").orElse(null);
                    if (studentS002 != null) {
                        UserAccount studentUser2 = new UserAccount();
                        studentUser2.setUsername("S002");
                        studentUser2.setPassword(encoder.encode("password"));
                        studentUser2.setRole(UserRole.STUDENT);
                        studentUser2.setStudentId(studentS002.getStudentId());
                        userRepo.save(studentUser2);
                        System.out.println("Student user S002 initialized successfully");
                    }
                }
            } catch (Exception e) {
                System.err.println("ERROR during data initialization: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
