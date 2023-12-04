package com.example.be.repository;

import com.example.be.dto.IAccountGroupDTO;
import com.example.be.entity.GroupAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IGroupAccountRepository extends JpaRepository<GroupAccount,Integer> {
@Query(value = "select group_account.group_account_id ,group_account.delete_flag,group_account.status,group_account.name,date,count(group_account.name) as so_luong from group_account left join student on group_account.group_account_id=student.group_account_id group by(group_account.group_account_id)" , nativeQuery = true)
    Page<GroupAccount> findAllGroup(Pageable pageable);

@Modifying
    @Query(
            value = "UPDATE `doanit`.`group_account` SET `delete_flag` = 1 WHERE (`group_account_id` = ?1);",nativeQuery = true
    ) void deleteGroup(Integer groupId);
@Modifying
    @Query(
            value = "UPDATE `doanit`.`group_account` SET `date` = ?1 WHERE (`group_account_id` = ?2);",nativeQuery = true)
            void updateDeadLine(String date,Integer id);
@Modifying
    @Query(value = "UPDATE `doanit`.`group_account` SET `status` = 1 WHERE (`group_account_id` = ?1);",nativeQuery = true)
        void acceptGroup(Integer groupId);
    @Modifying
    @Query(
            value = "update student  " +
                    "set student.group_account_id = null " +
                    "where student.id = ?1",
            nativeQuery = true)
    void deleteGroupOfStudentById(Integer id);
}

