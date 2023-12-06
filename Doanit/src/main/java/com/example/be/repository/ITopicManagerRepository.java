package com.example.be.repository;

import com.example.be.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ITopicManagerRepository extends JpaRepository<Topic, Integer> {
    // Xóa đề tài
    @Modifying
    @Query(value = "UPDATE `topic` SET `delete_flag` = ?1 WHERE (`topic_id` = ?2)", nativeQuery = true)
    void deleteTopic(Boolean deleteFlag, Integer id);

    // Tạo ngày cho các giai đoạnn
    @Modifying
    @Query(value = "INSERT INTO `topic_process` (" +
            "`date_start`, `date_end`, `percent_process`, " +
            "`process_number`, `status`, `info_topic_register_id`) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6) ", nativeQuery = true)
    void createTopicProcess(String dateEnd, String dateStart, Integer percentProcess,
                            Integer processNumber, Boolean status, Integer infoTopicRegister);

    @Modifying
    @Query(value = "UPDATE `info_topic_register` SET `status` = ?1,`teacher_id` = ?2  WHERE (`info_topic_register_id` = ?3);", nativeQuery = true)
    void statusInfo(Boolean status, Integer teacherId, Integer id);
}
