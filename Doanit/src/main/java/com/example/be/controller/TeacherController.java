package com.example.be.controller;


import com.example.be.dto.AccountRoleDTO;
import com.example.be.dto.CreateUpdateTeacherDTO;
import com.example.be.dto.ITeacherUpdateDTO;
import com.example.be.entity.Account;
import com.example.be.entity.Degree;
import com.example.be.entity.Faculty;
import com.example.be.service.IAccountService;
import com.example.be.service.IDegreeService;
import com.example.be.service.IFacultyService;
import com.example.be.service.ITeacherService;
import com.example.be.validate.TeacherValidator;
import com.example.be.validate.TeacherValidatorUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private TeacherValidator teacherValidator;

    @Autowired
    private TeacherValidatorUpdate teacherValidatorUpdate;

    @Autowired
    private IFacultyService facultyService;
    @Autowired
    private IDegreeService degreeService;
    @PostMapping("/createTeacher")
    public ResponseEntity<?> createTeacher(@RequestBody CreateUpdateTeacherDTO teacherDTO) {
        if (teacherDTO == null) {
            return new ResponseEntity<CreateUpdateTeacherDTO>(HttpStatus.BAD_REQUEST);
        } else {
            Map<String,String> errors  = teacherValidator.validate(teacherDTO);
            if (errors.isEmpty()) {
                Account account = new Account();
                account.setUsername(teacherDTO.getEmail());
                account.setPassword("123");
                account = accountService.registerAccount(account);
                teacherDTO.setAccountId(account.getAccountId());

                AccountRoleDTO accountRoleDTO = new AccountRoleDTO();
                accountRoleDTO.setAccountId(account.getAccountId());
                accountRoleDTO.setRoleId(2);
                teacherService.createTeacher(teacherDTO);
                return new ResponseEntity<CreateUpdateTeacherDTO>(teacherDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<Map<String,String>>( errors, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping(value = "/getTeacherById/{id}")
    public ResponseEntity<ITeacherUpdateDTO> findStudentById(@PathVariable Integer id){
        ITeacherUpdateDTO teacher = teacherService.getTeacherById(id);
        if (teacher == null){
            return new ResponseEntity<ITeacherUpdateDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ITeacherUpdateDTO>(teacher, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllFaculty")
    public ResponseEntity<List<Faculty>> getAllFaculty(){
        List<Faculty> faculties = facultyService.getAllFaculty();
        if (faculties.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Faculty>>(faculties, HttpStatus.OK);
    }


    @GetMapping(value = "/getAllDegree")
    public ResponseEntity<List<Degree>> getAllDegree(){
        List<Degree> degrees = degreeService.getAllDegree();
        if (degrees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Degree>>(degrees, HttpStatus.OK);
    }


    @PostMapping("/updateTeacher")
    public ResponseEntity<?> updateTeacher(@RequestBody CreateUpdateTeacherDTO teacherDTO) {
        if (teacherService.getTeacherById(teacherDTO.getTeacherId()) == null) {
            return new ResponseEntity<>("không tìm thấy teacher",HttpStatus.BAD_REQUEST);
        } else {
            Map<String,String> errors  = teacherValidatorUpdate.validate(teacherDTO);
            if (errors.isEmpty()) {
                teacherService.updateTeacher(teacherDTO);
                return new ResponseEntity<CreateUpdateTeacherDTO>(teacherDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<Map<String,String>>( errors, HttpStatus.BAD_REQUEST);
            }
        }
    }

}