package com.example.be.validate;

import com.example.be.dto.GroupAccountDTO;
import com.example.be.repository.GroupAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GroupAccountValidator {
@Autowired
    GroupAccountRepository groupAccountRepository;
public Map<String,String> validate(GroupAccountDTO groupAccountDTO){
    Map<String,String> errors = new HashMap<>();
if(groupAccountDTO.getName()==null){
    errors.put("errorNameGroup","Tên không được để trống");
    return errors;
}
if (groupAccountDTO.getName().equals("")){
    errors.put("errorNameGroup","Tên không được là ký tự trắng");
}
    if(groupAccountDTO.getName().length()>255){
        errors.put("errorNameGroup","Tên không được quá 255 ký tự");
    }
//    if (groupAccountRepository.findByName(groupAccountDTO.getName())!=null){
//        errors.put("errorNameGroup","Tên đã được sử dụng");
//    }
    return errors;
}

}
