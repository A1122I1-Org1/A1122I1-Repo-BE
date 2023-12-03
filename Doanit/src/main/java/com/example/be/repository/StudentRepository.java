package com.example.be.repository;

import com.example.be.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {

    /**
     * Get All Student For Admin
     */
    @Query(value = "SELECT * FROM student " +
            "JOIN grade ON student.grade_id = grade.grade_id " +
            "JOIN faculty ON grade.faculty_id = faculty.faculty_id " +
            "WHERE CONCAT('MSV', student.student_id, ' ', IFNULL(student.name, '')) " +
            "LIKE CONCAT('%', ?1, '%') AND student.delete_flag = 1 " +
            "ORDER BY student.student_id",
            nativeQuery = true)
    Page<Student> getAllStudent(String find,  Pageable pageable);
    /**
     * Get All Student For Teacher
     */
    @Query(value = "SELECT *  FROM student " +
            "JOIN grade ON student.grade_id = grade.grade_id " +
            "JOIN faculty ON grade.faculty_id = faculty.faculty_id " +
            "WHERE CONCAT('MSV" +
            "', student.student_id, IFNULL(student.name, '')) " +
            "LIKE CONCAT('%', ?1, '%') AND student.delete_flag = 1 " +
            "AND student.student_id IN (SELECT student.student_id FROM student " +
            "JOIN group_account ON student.group_account_id = group_account.group_account_id " +
            "JOIN info_topic_register ON group_account.group_account_id = info_topic_register.group_account_id " +
            "JOIN teacher ON teacher.teacher_id= info_topic_register.teacher_id " +
            "WHERE teacher.teacher_id = ?2) " +
            "ORDER BY student.student_id"
            , nativeQuery = true)
    Page<Student> getAllStudent(String find, Integer teacherId ,Pageable pageable);
}