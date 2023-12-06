package com.example.be.service.impl;

import com.example.be.dto.InfoTopicRegisterDTO;
import com.example.be.dto.TopicProcessDTO;
import com.example.be.repository.IInfoTopicRegisterRepository;
import com.example.be.repository.ITopicManagerRepository;
import com.example.be.service.ITopicManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class TopicManagerServiceImpl implements ITopicManagerService {
    @Autowired
    private ITopicManagerRepository iTopicManagerRepository;

    @Autowired
    private IInfoTopicRegisterRepository iInfoTopicRegisterRepository;

//    @Autowired
//    JavaMailSender javaMailSender;

    @Override
    public void topicCancel(Integer id) {
        iInfoTopicRegisterRepository.approvalCancel(true, id);
        iInfoTopicRegisterRepository.topicCancel(true, id);
    }

    @Override
    public void deleteTopic(Integer id) {
        iTopicManagerRepository.deleteTopic(true, id);
    }

    @Override
    public void createTopicProcess(TopicProcessDTO topicProcessDTO) {
        iTopicManagerRepository.createTopicProcess(topicProcessDTO.getDateEnd(), topicProcessDTO.getDateStart(), 0, topicProcessDTO.getProcessNumber(), false, topicProcessDTO.getInfoTopicRegister());
    }

    @Override
    public void statusInfo(Integer teacherId, Integer id) {
        iTopicManagerRepository.statusInfo(true, teacherId, id);
    }

    @Override
    public void sendStudent(InfoTopicRegisterDTO infoTopicRegisterDTO) throws MessagingException, UnsupportedEncodingException {
//        String subject = "Thông báo hủy đề tài!";
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
//        String mailContent;
//        String[] emailList = new String[infoTopicRegisterDTO.getStudentList().size()];
//
//        for (int i = 0; i<infoTopicRegisterDTO.getStudentList().size(); i++) {
//            emailList[i] = infoTopicRegisterDTO.getStudentList().get(i).getEmail();
//        }
//        helper.setTo(emailList);
//        helper.setFrom("anhtuan2003147", "Teacher - Người kiểm duyệt đề tài");
//        helper.setSubject(subject);
//        mailContent = "<div style='text-align: center'>\n" +
//                "    <h2>Nội dung nguyên nhân hủy đề tài</h2>\n" +
//                "    <p><span style='font-weight: bold'>" + infoTopicRegisterDTO.getMessageCancel() + "</span></p>\n" +
//                "    <p><span style='font-weight: bold'> Yêu cầu bạn hãy nhanh chóng chọn đề tài mới </span></p>\n" +
//                "    </div>";
//        helper.setText(mailContent, true);
//        javaMailSender.send(message);
    }
}
