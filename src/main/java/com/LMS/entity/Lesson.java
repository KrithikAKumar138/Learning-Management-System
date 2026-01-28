package com.LMS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lessons")
@Getter
@Setter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_title")
    private String courseTitle;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    @Column(nullable = false)
    private String title;

    @Column(length = 5000)
    private String content;

    @Column(length = 1000)
    private String mediaUrl;

    private String fileType;
    private String fileName;
    private String filePath;

    @Column(name = "lesson_order")
    private Integer orderNumber = 1;

    @Column(name = "duration_minutes")
    private Integer duration = 0;

    @Column(name = "content_type")
    private String contentType = "TEXT";

    public Lesson() {
    }

    public Lesson(String title, String content, Course course, Integer orderNumber) {
        this.title = title;
        this.content = content;
        this.course = course;
        this.orderNumber = orderNumber;
        this.contentType = "TEXT";
    }


    public Lesson(String title, String fileName, String fileType, String filePath, Course course, Integer orderNumber) {
        this.title = title;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.course = course;
        this.orderNumber = orderNumber;

        if (fileType.startsWith("video")) {
            this.contentType = "VIDEO";
        } else if (fileType.equals("application/pdf")) {
            this.contentType = "PDF";
        } else {
            this.contentType = "FILE";
        }
    }
}