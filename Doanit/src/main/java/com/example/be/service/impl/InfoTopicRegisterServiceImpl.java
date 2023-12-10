package com.example.be.service.impl;

import com.example.be.dto.InfoTopicRegisterDTO;
import com.example.be.entity.InfoTopicRegister;
import com.example.be.repository.IInfoTopicRegisterRepository;
import com.example.be.repository.InfoTopicRegisterRepository;
import com.example.be.service.IInfoTopicRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;

@Service
@Transactional
public class InfoTopicRegisterServiceImpl implements IInfoTopicRegisterService {
    @Autowired
    InfoTopicRegisterRepository infoTopicRegisterRepository;

    @Autowired
    private IInfoTopicRegisterRepository iInfoTopicRegisterRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void registerInfoTopic(InfoTopicRegister infoTopicRegister) {
        infoTopicRegisterRepository.save(infoTopicRegister);
    }

    @Override
    public Page<InfoTopicRegister> getListTopicNotApproval(Integer idTeacher, Pageable pageable) {
        return iInfoTopicRegisterRepository.findAllTopicNotApprovalByTeacherId(idTeacher, pageable);
    }

    @Override
    public void sendStudentApproval(InfoTopicRegisterDTO infoTopicRegisterDTO) throws MessagingException, UnsupportedEncodingException {
        String subject = "Thông báo kiểm duyệt đề tài!";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String mailContent;
        String[] emailList = new String[infoTopicRegisterDTO.getStudentList().size()];

        for (int i = 0; i<infoTopicRegisterDTO.getStudentList().size(); i++) {
            emailList[i] = infoTopicRegisterDTO.getStudentList().get(i).getEmail();
        }
        helper.setTo(emailList);
        helper.setFrom("anhtuan2003147", "Teacher - Người kiểm duyệt đề tài");
        helper.setSubject(subject);
        mailContent = "<div style='text-align: center'>\n" +
                "    <h2>Chúc mừng đề tài của bạn đã được duyệt!</h2>\n" +
                "    <p><span style='font-weight: bold'> Yêu cầu bạn hãy nhanh chóng làm việc theo các giai đoạn giáo viên đã giao! </span></p>\n" +
                "    </div>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }

    @Override
    public void setStatusApproval(Integer id) {
        iInfoTopicRegisterRepository.approval(true, id);
    }
}
