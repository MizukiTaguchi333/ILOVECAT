package com.example.slstudiomini.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.slstudiomini.exception.MyLessonNotFoundException;
import com.example.slstudiomini.model.Course;
import com.example.slstudiomini.model.Lesson;
import com.example.slstudiomini.repository.LessonRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CourseService courseService;

    public List<Lesson> findAllLessons() {
        // return lessonRepository.findAll();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lesson> cq = cb.createQuery(Lesson.class);
        Root<Lesson> lesson = cq.from(Lesson.class);
        //ここからクエリをビルドする
        cq.select(lesson);
        cq.where(
            cb.isNull(lesson.get("deletedAt"))
        );
        //じっこうする
        return entityManager.createQuery(cq).getResultList();
    }

    public Lesson findLessonById(Long id){
        //自作エラー呼び出す
        Lesson uniqueLesson = lessonRepository.findById(id).orElseThrow(() -> new MyLessonNotFoundException("this lesson doesnt exist. lesson ID = " + id));

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lesson> cq = cb.createQuery(Lesson.class);
        Root<Lesson> lesson = cq.from(Lesson.class);
        cq.select(lesson);
        //where id = id;
        cq.where(
            cb.equal(lesson.get("id"), id)
        );
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Transactional
    public Lesson save(Lesson lesson) {
        lesson.setCreatedAt(LocalDateTime.now());
        return lessonRepository.save(lesson);
    }

    @Transactional
    public Lesson update(Lesson updateLesson) {
        Lesson lesson = findLessonById(updateLesson.getId());

        lesson.setUpdatedAt(LocalDateTime.now());
        lesson.setName(updateLesson.getName());
        lesson.setContent(updateLesson.getContent());
        lesson.setDescription(updateLesson.getDescription());
        lesson.setUpdatedAt(LocalDateTime.now());

        Course course = courseService.findCourseById(updateLesson.getCourse().getId());
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }
}