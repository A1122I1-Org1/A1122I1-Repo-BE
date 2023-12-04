package com.example.be.repository;

import com.example.be.dto.ITeacherDto;
import com.example.be.dto.ITeacherUpdateDTO;
import com.example.be.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query(value = "SELECT  t.teacher_id As teacherId, t.name AS teacherName, t.email, t.phone, t.avatar, f.name AS facultyName \n" +
            "FROM teacher t\n" +
            "JOIN faculty f ON t.faculty_id = f.faculty_id\n" +
            "where concat('MGV-',t.teacher_id,\n" +
            "  ifnull(t.phone,''),\n" +
            "  ifnull(t.email,''),\n" +
            "  ifnull(t.name,''),\n" +
            "  f.name\n) " +
            "like concat('%', :find, '%')\n" +
            "and t.delete_flag = 1" ,nativeQuery = true)
    Page<ITeacherDto> getAllTeacher(String find, Pageable pageable);

    @Modifying
    @Query(value = "update teacher set teacher.delete_flag = 0 where teacher.teacher_id = ?1",nativeQuery = true)
    void deleteTeacher(Integer id);
}
