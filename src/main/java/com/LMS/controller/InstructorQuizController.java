package com.LMS.controller;

import com.LMS.entity.Course;
import com.LMS.entity.QuizQuestion;
import com.LMS.repository.CourseRepository;
import com.LMS.repository.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructor/quiz")
public class InstructorQuizController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @GetMapping("/{courseId}")
    public String quizForm(@PathVariable Long courseId, Model model) {

        Course course = courseRepository.findById(courseId).orElseThrow();
        model.addAttribute("course", course);
        model.addAttribute("question", new QuizQuestion());

        return "instructor-quiz";
    }

    @PostMapping("/save")
    public String saveQuizQuestion(@ModelAttribute QuizQuestion question,
                                   @RequestParam Long courseId) {

        Course course = courseRepository.findById(courseId).orElseThrow();
        question.setCourse(course);

        quizQuestionRepository.save(question);

        return "redirect:/instructor/dashboard";
    }
}
