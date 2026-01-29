package com.LMS.controller;

import com.LMS.repository.CourseRepository;
import com.LMS.repository.LessonRepository;
import com.LMS.service.FirebaseStorageService;
import com.LMS.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/instructor")
public class InstructorLessonController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private InstructorService instructorService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;


    @PostMapping("/course/lesson")
    public String uploadLesson(
            @RequestParam Long courseId,
            @RequestParam String title,
            @RequestParam(required = false) String content,
            @RequestParam MultipartFile file) {

        instructorService.addLesson(courseId, title, content, file);

        return "redirect:/instructor/dashboard";
    }
    @GetMapping("/lesson/file/{courseId}")
    public void viewLessonFile(
            @PathVariable Long courseId,
            HttpServletResponse response) throws IOException {

        String filePath = "uploads/lessons/" + courseId + ".pdf";

        File file = new File(filePath);

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "inline; filename=\"" + file.getName() + "\"");

        Files.copy(file.toPath(), response.getOutputStream());
    }

}
