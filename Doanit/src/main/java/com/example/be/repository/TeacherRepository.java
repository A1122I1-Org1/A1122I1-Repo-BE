package com.example.be.repository;

import com.example.be.dto.ITeacherUpdateDTO;
import com.example.be.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Modifying
    @Query(value = "insert  into teacher(teacher.address, teacher.avatar, teacher.date_of_birth, teacher.email, teacher.name, teacher.phone," +
            "teacher.degree_id, teacher.faculty_id, teacher.gender,teacher.account_id, teacher.delete_flag) value (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10, true )",nativeQuery = true)
    void createTeacher(String address, String avatar, String dateOfBirth, String email, String name, String phone, Integer degreeId, Integer facultyId,Boolean gender, Integer accountId);

    @Query(value = "select teacher.id as id, teacher.address as address, teacher.avatar as avatar, teacher.date_of_birth as dateOfBirth," +
            "teacher.email as email, teacher.name as name, teacher.phone as phone, teacher.degree_id as degree, teacher.faculty_id as faculty," +
            "teacher.gender as gender from teacher where teacher.id = ?1 and teacher.delete_flag = true", nativeQuery = true)
    ITeacherUpdateDTO getTeacherById(Integer id);

    @Modifying
    @Query(value = "update teacher set teacher.address = ?1, teacher.avatar = ?2, teacher.date_of_birth = ?3, teacher.email = ?4, teacher.name = ?5, teacher.phone = ?6, teacher.degree_id = ?7," +
            "teacher.faculty_id = ?8, teacher.gender = ?9 where teacher.id = ?10", nativeQuery = true)
    void updateTeacher(String address, String avatar, String dateOfBirth, String email, String name, String phone, Integer degree, Integer faculty, Boolean gender, Integer id);


}