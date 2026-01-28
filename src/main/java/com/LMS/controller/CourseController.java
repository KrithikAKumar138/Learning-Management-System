package com.LMS.controller;

import com.LMS.entity.Course;
import com.LMS.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/create")
    public String createCourse(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String category,
            Authentication authentication) {

        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setCategory(category);
        course.setCreatedBy(authentication.getName());
        course.setApproved(false);

        courseRepository.save(course);

        return "redirect:/instructor/dashboard";
    }
    @GetMapping("/approved")
    @ResponseBody
    public List<Course> getApprovedCourses() {
        return courseRepository.findByApprovedTrue();
    }

    @PostMapping("/{id}/delete")
    @ResponseBody
    public String deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return "Course deleted successfully";
    }

}