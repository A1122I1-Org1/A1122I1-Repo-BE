package com.example.be.controller;

import com.example.be.dto.AccountRoleDTO;
import com.example.be.dto.CreateUpdateStudentDTO;
import com.example.be.dto.CreateUpdateTeacherDTO;
import com.example.be.dto.IStudentEditDTO;
import com.example.be.entity.Account;
import com.example.be.entity.Student;
import com.example.be.service.IAccountService;
import com.example.be.service.IStudentService;
import com.example.be.service.impl.AccountServiceImpl;
import com.example.be.validate.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private IStudentService IStudentService;


    @Autowired
    IAccountService accountService;


    @Autowired
    StudentValidator studentValidator;

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
    public ResponseEntity<Page<Student>> getAllStudent(@RequestParam(value = "find",defaultValue = "") String find,
                                                       @RequestParam(value = "teacherId") Integer teacherId,
                                                       @RequestParam(value = "page") Integer page){
        Page<Student> listStudent = IStudentService.findAllStudent(find,teacherId, PageRequest.of(page,4));
        if (listStudent.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listStudent, HttpStatus.OK);
    }


    /**
     * KhoaHND
     * Edit Student
     */
    @RequestMapping(value = "/edit-student", method = RequestMethod.PUT)
    public ResponseEntity<?> editStudent(@RequestBody CreateUpdateStudentDTO studentDTO){
        Map<String,String> errors  = studentValidator.validate(studentDTO);
        if(errors.isEmpty()){
            IStudentService.editStudent(studentDTO);
            return new ResponseEntity<CreateUpdateStudentDTO>(studentDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    /**
     * KhoaHND
     * create Student
     */
    @RequestMapping(value = "/create-student",method = RequestMethod.POST)
    public ResponseEntity<?> createStudent(@RequestBody CreateUpdateStudentDTO studentDTO){


        if(studentDTO == null){
            return new ResponseEntity<CreateUpdateStudentDTO>(HttpStatus.BAD_REQUEST);
        }else{
            Map<String,String> errors  = studentValidator.validate(studentDTO);
            if(errors.isEmpty()){
                Account account = new Account();
                account.setUsername(studentDTO.getEmail());
                account.setPassword("123");
                account = accountService.registerAccount(account);
                studentDTO.setAccountId(account.getAccountId());
                AccountRoleDTO accountRoleDTO = new AccountRoleDTO();
                accountRoleDTO.setAccountId(account.getAccountId());
                accountRoleDTO.setRoleId(3);
                IStudentService.createNewStudent(studentDTO);

                return new ResponseEntity<CreateUpdateStudentDTO>(studentDTO, HttpStatus.OK);

            }else {
                return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
            }
        }

    }

    /**
     * KhoaHND
     * Find Student By Id
     */
    @RequestMapping(value = "/get-student-by-studentId/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<IStudentEditDTO> findStudentById(@PathVariable Integer studentId){
        IStudentEditDTO student = IStudentService.findStudentByStudentId(studentId);
        if (student == null){
            return new ResponseEntity<IStudentEditDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<IStudentEditDTO>(student, HttpStatus.OK);
    }
}
