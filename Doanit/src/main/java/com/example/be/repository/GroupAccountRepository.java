package com.example.be.repository;


import com.example.be.entity.GroupAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface GroupAccountRepository extends JpaRepository<GroupAccount, Integer> {
    //Save Group
//    @Modifying
//    @Query(
//            value = "insert into group_account(`name`,delete_flag,status) value (?1,false,false)",
//            nativeQuery = true
//    )
//    void saveGroup(String name);

    //Student accept join group
    @Modifying
    @Query(
            value = "update student " +
                    "set group_account_id = ?1 " +
                    "where id = ?2",
            nativeQuery = true)
    void agreeJoinGroup(Integer groupId, Integer studentId);
    //find by name
    List<GroupAccount> findByName(String name);
//Set Group Leader
    @Modifying
    @Query(
            value = "update account_role \n" +
                    "set account_role.role_id = 4\n" +
                    "where account_role.account_id = ?1",
            nativeQuery = true
    )
void setGroupLeader(Integer studentId);
}
