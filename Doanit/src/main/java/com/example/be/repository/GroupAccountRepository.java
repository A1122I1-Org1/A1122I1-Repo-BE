package com.example.be.repository;


import com.example.be.entity.GroupAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface GroupAccountRepository extends JpaRepository<GroupAccount, Integer> {
    //Save Group
    @Modifying
    @Query(
            value = "insert into group_account(`name`,delete_flag,status) value (?1,false,false)",
            nativeQuery = true
    )
    void saveGroup(String name);

    //Student accept join group
    @Modifying
    @Query(
            value = "update student " +
                    "set group_account_id = ?1 " +
                    "where id = ?2",
            nativeQuery = true)
    void agreeJoinGroup(Integer groupId, Integer studentId);

}
