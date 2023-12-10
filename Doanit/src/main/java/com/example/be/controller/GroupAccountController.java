package com.example.be.controller;

import com.example.be.dto.GroupAccountDTO;
import com.example.be.entity.Account;
import com.example.be.entity.GroupAccount;
import com.example.be.security.UserPrinciple;
import com.example.be.service.IAccountService;
import com.example.be.service.IGroupAccountService;
import com.example.be.validate.GroupAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/Group")
public class GroupAccountController {
    @Autowired
    IGroupAccountService IGroupAccountService;

    @Autowired
    GroupAccountValidator groupAccountValidator;
    @Autowired
    IAccountService iAccountService;
    @PostMapping("/createGroup")
    public ResponseEntity<?> doCreateGroup(@RequestBody GroupAccountDTO groupAccountDto) {
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account= iAccountService.findByUsername(userPrinciple.getUsername());
        Integer accountId= account.getAccountId();
        Integer studentID= account.getStudent().getStudentId();

        Map<String,String> errors= groupAccountValidator.validate(groupAccountDto);
        if(errors.isEmpty()) {
            IGroupAccountService.saveGroup(groupAccountDto.getName(), studentID, accountId,groupAccountDto.getStudents());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/joinGroup")
    public ResponseEntity<?> joinGroup(@RequestParam("studentID") Integer studentId, @RequestParam("groupId") Integer groupId) {
        IGroupAccountService.acceptJoinGroup(groupId, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('TEACHER')" )
    @RequestMapping(value = "list-group", method= RequestMethod.GET)
    public ResponseEntity<Page<GroupAccount>> listGroup(@RequestParam(value = "find",defaultValue = "") String find,
                                                        @RequestParam(value = "page") Integer page){
        Page<GroupAccount> listGroup=IGroupAccountService.listGroup(find, PageRequest.of(page,2));
        if (listGroup.isEmpty()){
            return new ResponseEntity<Page<GroupAccount>>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<Page<GroupAccount>>(listGroup,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('TEACHER')" )
    @RequestMapping(value = "delete-group/{groupId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> deleteGroup(@PathVariable("groupId") Integer groupId,
                                         @RequestBody List<Integer> listIdStudent) throws MessagingException, UnsupportedEncodingException {
        this.IGroupAccountService.sendStudentDelete(IGroupAccountService.findById(groupId));
        this.IGroupAccountService.deleteGroup(groupId, listIdStudent);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('TEACHER')" )
    @RequestMapping(value = "accept-group/{groupId}",method =RequestMethod.PATCH )
    public ResponseEntity<?> acceptGroup(@PathVariable("groupId") Integer groupId) throws MessagingException, UnsupportedEncodingException {
        this.IGroupAccountService.acceptGroup(groupId);
        this.IGroupAccountService.sendStudentAccept(IGroupAccountService.findById(groupId));
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('TEACHER')" )
    @RequestMapping(value = "create-deadline/{groupId}/{date}", method = RequestMethod.PATCH)
    public ResponseEntity<?> createDeadline(@PathVariable("date") String date,@PathVariable("groupId")Integer groupId){
        this.IGroupAccountService.updateDeadLine(date,groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
