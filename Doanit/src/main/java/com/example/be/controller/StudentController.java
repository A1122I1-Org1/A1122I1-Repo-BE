package com.example.be.controller;

import com.example.be.entity.Student;
import com.example.be.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private IStudentService IStudentService;

    @RequestMapping(value = "/student-list",method = RequestMethod.GET)
    public ResponseEntity<Page<Student>> getAllStudent(@RequestParam(defaultValue = "") String find,
                                                       @RequestParam(value = "page") Integer page){
        Page<Student> listStudent = IStudentService.findAllStudent(find, PageRequest.of(page,4));
        if (listStudent.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listStudent, HttpStatus.OK);
    }
    @RequestMapping(value = "/student-list-teacher",method = RequestMethod.GET)
    public ResponseEntity<Page<Student>> getAllStudent(@RequestParam(defaultValue = "") String find,
                                                       @RequestParam(value = "teacherId") Integer teacherId,
                                                       @RequestParam(value = "page") Integer page){
        Page<Student> listStudent = IStudentService.findAllStudent(find,teacherId, PageRequest.of(page,4));
        if (listStudent.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listStudent, HttpStatus.OK);
    }

}
