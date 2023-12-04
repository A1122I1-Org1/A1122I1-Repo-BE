package com.example.be.controller;

import com.example.be.dto.IAccountGroupDTO;
import com.example.be.entity.GroupAccount;
import com.example.be.service.IGroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class GroupController {
    @Autowired
    private IGroupAccountService iGroupAccountService;
   @RequestMapping(value = "list-group", method= RequestMethod.GET)
    public ResponseEntity<Page<GroupAccount>> listGroup(@RequestParam(value = "page") Integer page){
      Page<GroupAccount> listGroup=iGroupAccountService.listGroup(PageRequest.of(page,4));
      if (listGroup.isEmpty()){
          return new ResponseEntity<Page<GroupAccount>>(HttpStatus.BAD_REQUEST);
      }
       return  new ResponseEntity<Page<GroupAccount>>(listGroup,HttpStatus.OK);
   }
    @RequestMapping(value = "delete-group/{groupId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> deleteGroup(@PathVariable("groupId") Integer groupId,
                                         @RequestBody List<Integer> listIdStudent) {
        this.iGroupAccountService.deleteGroup(groupId, listIdStudent);
        return new ResponseEntity<>(HttpStatus.OK);

    }

   @RequestMapping(value = "accept-group/{groupId}",method =RequestMethod.PATCH )
    public ResponseEntity<?> acceptGroup(@PathVariable("groupId") Integer groupId){
      this.iGroupAccountService.acceptGroup(groupId);
      return  new ResponseEntity<>(HttpStatus.OK);
   }
   @RequestMapping(value = "create-deadline/{groupId}/{date}", method = RequestMethod.PATCH)
    public ResponseEntity<?> createDeadline(@PathVariable("date") String date,@PathVariable("groupId")Integer groupId){
       this.iGroupAccountService.updateDeadLine(date,groupId);
       return new ResponseEntity<>(HttpStatus.OK);
   }
}
