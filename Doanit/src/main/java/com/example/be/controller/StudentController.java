package com.example.be.controller;

import com.example.be.dto.ITeacherUpdateDTO;
import com.example.be.entity.Account;
import com.example.be.entity.Student;
import com.example.be.entity.Teacher;
import com.example.be.security.UserPrinciple;
import com.example.be.service.IAccountService;
import com.example.be.service.IStudentService;
import com.example.be.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private IStudentService IStudentService;
    @Autowired
    ITeacherService iTeacherService;
    @Autowired
    IAccountService iAccountService;

    @RequestMapping(value = "/student-list",method = RequestMethod.GET)
    public ResponseEntity<Page<Student>> getAllStudent(@RequestParam(value = "find",defaultValue = "") String find,
                                                       @RequestParam(value = "page", defaultValue = "0") Integer page){
        Page<Student> listStudent = IStudentService.findAllStudent(find, PageRequest.of(page,4));
        if (listStudent.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listStudent, HttpStatus.OK);
    }
    @RequestMapping(value = "/student-list-teacher",method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllStudentWithTeacher(@RequestParam(value = "find",defaultValue = "") String find,
                                                       @RequestParam(value = "page", defaultValue = "0") Integer page){
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account= iAccountService.findByUsername(userPrinciple.getUsername());
        Integer teacherId= account.getTeacher().getTeacherId();
        Page<Student> listStudent = IStudentService.findAllStudent(find,teacherId, PageRequest.of(page,4));
        ITeacherUpdateDTO teacher =  iTeacherService.getTeacherById(teacherId);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("students", listStudent);
        responseMap.put("teacher", teacher);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
