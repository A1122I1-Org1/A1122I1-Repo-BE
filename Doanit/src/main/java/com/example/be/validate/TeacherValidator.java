package com.example.be.validate;


import com.example.be.dto.CreateUpdateTeacherDTO;
import com.example.be.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class TeacherValidator {

    private static Map<String,String> errors = new HashMap<>();
    private static final Pattern PATTERN_NAME = Pattern.compile("^[a-zA-Z\\s]+$");
    private static final Pattern PATTERN_EMAIL = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    private static final Pattern PATTERN_PHONE = Pattern.compile("^[0-9]+$");

    public static Map<String,String> validate(CreateUpdateTeacherDTO createUpdateTeacherDTO) {
        // Validate name
        if (createUpdateTeacherDTO.getName() == null || createUpdateTeacherDTO.getName().isEmpty()) {
            errors.put("errorNameEmpty","Tên giáo viên không được trống");
        }
        if (createUpdateTeacherDTO.getName().length() > 50) {
            errors.put("errorNameLength","Tên không được quá 50 ký tự");
        }
        if (!PATTERN_NAME.matcher(createUpdateTeacherDTO.getName()).matches()) {
            errors.put("errorNameSpecialCharacter","Tên không được chứa các kí tự đặc biệt");
        }

        // Validate date of birth
        if (createUpdateTeacherDTO.getDateOfBirth() == null || createUpdateTeacherDTO.getDateOfBirth().isEmpty()) {
            errors.put("errorDateEmpty","Ngày tháng năm sinh không được để trống");
        }
        LocalDate now = LocalDate.now();
        int age = Period.between(LocalDate.parse(createUpdateTeacherDTO.getDateOfBirth()), now).getYears();
        if (age < 18) {
            errors.put("errorDateMin","Giáo viên không được nhỏ hơn 18 tuổi");
        }
        if (age > 50) {
            errors.put("errorDateMax","Giáo viên không được lớn hơn 50 tuổi");
        }

        // Validate phone
        if (createUpdateTeacherDTO.getPhone() == null || createUpdateTeacherDTO.getPhone().isEmpty()) {
            errors.put("errorPhoneEmpty","Số điện thoại không được để trống");
        }
        if (createUpdateTeacherDTO.getPhone().length() != 10) {
            errors.put("errorPhoneLength","Số điện thoại phải có 10 ký tự");
        }
        if (!PATTERN_PHONE.matcher(createUpdateTeacherDTO.getPhone()).matches()) {
            errors.put("errorPhoneNumber","Số điện thoại chỉ được chứa số");
        }

        // Validate email
        if (createUpdateTeacherDTO.getEmail() == null || createUpdateTeacherDTO.getEmail().isEmpty()) {
            errors.put("errorEmailEmpty","Email không được để trống");
        }
        if (createUpdateTeacherDTO.getEmail().length() > 50) {
            errors.put("errorEmailLength","Email không được quá 50 ký tự");
        }
        if (!PATTERN_EMAIL.matcher(createUpdateTeacherDTO.getEmail()).matches()) {
            errors.put("errorEmailFormat","Email không hợp lệ");
        }

//        // Validate phone duplicate
//        if (createUpdateTeacherDTO.getId() == null) {
//            Teacher teacher = TeacherRepository.findByEmail(createUpdateTeacherDTO.getEmail());
//            if (teacher != null) {
//                throw new IllegalArgumentException("Email đã tồn tại");
//            }
//
//            teacher = TeacherRepository.findByPhone(createUpdateTeacherDTO.getPhone());
//            if (teacher != null) {
//                throw new IllegalArgumentException("Số điện thoại đã tồn tại");
//            }
//        } else {
//            Teacher teacher = TeacherRepository.findById(createUpdateTeacherDTO.getId()).orElse(null);
//            if (teacher != null && !teacher.getEmail().equals(createUpdateTeacherDTO.getEmail())) {
//                Teacher otherTeacher = TeacherRepository.findByEmail(createUpdateTeacherDTO.getEmail());
//                if (otherTeacher != null) {
//                    throw new IllegalArgumentException("Email đã tồn tại");
//                }
//            }
//
//            if (teacher != null && !teacher.getPhone().equals(createUpdateTeacherDTO.getPhone())) {
//                Teacher otherTeacher = TeacherRepository.findByPhone(createUpdateTeacherDTO.getPhone());
//                if (otherTeacher != null) {
//                    throw new
//
//            }


        if (createUpdateTeacherDTO.getAvatar() != null && !createUpdateTeacherDTO.getAvatar().isEmpty()) {
            String[] allowedExtensions = {"jpg", "png", "jpeg"};
            String extension = createUpdateTeacherDTO.getAvatar().substring(createUpdateTeacherDTO.getAvatar().lastIndexOf(".") + 1);
            if (!Arrays.asList(allowedExtensions).contains(extension)) {
                errors.put("errorFileFormat","File ảnh không đúng định dạng");
            }

            long size = createUpdateTeacherDTO.getAvatar().length();
            if (size > 1024 * 1024 * 10) {
                errors.put("errorFileLength","File ảnh quá lớn");
            }
        }
        return errors;
    }

}
