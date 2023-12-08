package com.example.be.repository;

import com.example.be.entity.InfoTopicRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IInfoTopicRegisterRepository extends JpaRepository<InfoTopicRegister,Integer> {
    @Query(value = "SELECT * FROM info_topic_register itr WHERE itr.group_account_id= :groupId", nativeQuery = true)
    InfoTopicRegister findByGroupAccountId(Integer groupId);
}
