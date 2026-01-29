package com.LMS.config;

import com.LMS.entity.Course;
import com.LMS.entity.Role;
import com.LMS.entity.User;
import com.LMS.repository.CourseRepository;
import com.LMS.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            UserRepository userRepository,
            CourseRepository courseRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            System.out.println("=== Initializing LMS Data ===");

            if (userRepository.findByEmail("admin@gamil.com").isEmpty()) {
                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@gamil.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("✓ Created ADMIN user: admin@gamil.com / admin123");
            } else {
                System.out.println(" ADMIN user already exists");
            }


            if (userRepository.findByEmail("instructor@gamil.com").isEmpty()) {
                User instructor = new User();
                instructor.setName("Instructor");
                instructor.setEmail("instructor@gamil.com");
                instructor.setPassword(passwordEncoder.encode("instructor123"));
                instructor.setRole(Role.INSTRUCTOR);
                userRepository.save(instructor);
                System.out.println("✓ Created INSTRUCTOR user: instructor@gamil.com / instructor123");
            } else {
                System.out.println("INSTRUCTOR user already exists");
            }


            if (userRepository.findByEmail("student@gamil.com").isEmpty()) {
                User student = new User();
                student.setName("Student");
                student.setEmail("student@gamil.com");
                student.setPassword(passwordEncoder.encode("student123"));
                student.setRole(Role.STUDENT);
                userRepository.save(student);
                System.out.println("✓ Created STUDENT user: student@gamil.com / student123");
            } else {
                System.out.println(" STUDENT user already exists");
            }


//            if (courseRepository.count() == 0) {
//                Course course1 = new Course("Agile Fundamentals in Tamil",
//                        "Learn Agile methodologies in Tamil language", "instructor@lms.com");
//                course1.setCategory("Project Management");
//                course1.setApproved(true);
//
//                Course course2 = new Course("AWS Account, Navigation & Instances",
//                        "Learn AWS fundamentals and instance management", "instructor@lms.com");
//                course2.setCategory("Cloud Computing");
//                course2.setApproved(true);
//
//                Course course3 = new Course("AWS Fundamentals",
//                        "Basic AWS concepts in Tamil", "instructor@lms.com");
//                course3.setCategory("Cloud Computing");
//                course3.setApproved(true);
//
//                Course course4 = new Course("AWS Infrastructure for Beginners in Tamil",
//                        "Learn AWS infrastructure basics", "instructor@lms.com");
//                course4.setCategory("Cloud Computing");
//                course4.setApproved(true);
//
//                Course course5 = new Course("GitLab",
//                        "Learn GitLab for version control", "instructor@lms.com");
//                course5.setCategory("Development Tools");
//                course5.setApproved(true);
//
//                List<Course> courses = Arrays.asList(course1, course2, course3, course4, course5);
//                courseRepository.saveAll(courses);
//
//                System.out.println("✓ Created 5 sample courses");
//            } else {
//                System.out.println("✓ Courses already exist: " + courseRepository.count());
//            }

            System.out.println("=== Data Initialization Complete ===");
        };
    }
}