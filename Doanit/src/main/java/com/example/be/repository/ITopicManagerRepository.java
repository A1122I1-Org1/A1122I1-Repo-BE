package com.example.be.repository;

import com.example.be.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ITopicManagerRepository extends JpaRepository<Topic, Integer> {

    @Query(value = "SELECT topic_id, content, delete_flag, image, introduce, name, faculty_id FROM doanit.topic ", nativeQuery = true)
    Page<Topic> findAllTopic(Pageable pageable);

    @Query(value = "SELECT topic_id, content, delete_flag, image, introduce, name, faculty_id FROM doanit.topic where delete_flag = ?1 and topic.name like %?2%", nativeQuery = true)
    Page<Topic> findAllTopicBy(Boolean delete , String name, Pageable pageable);

    @Query(value = "SELECT topic_id, content, delete_flag, image, introduce, name, faculty_id FROM doanit.topic where topic.topic_id = ?1", nativeQuery = true)
    Topic findByIdTopic(Integer id);

    @Modifying
    @Query(value = "UPDATE `doanit`.`topic` SET `delete_flag` = ?1 WHERE (`topic_id` = ?2)", nativeQuery = true)
    void deleteTopic(Boolean deleteFlag, Integer id);

    @Modifying
    @Query(value = "INSERT INTO `doanit`.`topic_process` (" +
            "`date_end`, `date_start`, `percent_process`, " +
            "`process_number`, `status`, `info_topic_register`) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6) " ,nativeQuery = true)
    void createTopicProcess(String dateEnd, String dateStart, Integer percentProcess,
                            Integer processNumber, Boolean status, Integer infoTopicRegister);

    @Modifying
    @Query(value = "UPDATE `doanit`.`info_topic_register` SET `status` = ?1,`teacher_id` = ?2  WHERE (`topic_id` = ?3);",nativeQuery = true)
    void statusInfo(Boolean status, Integer teacherId, Integer id);
}