package com.example.be.repository;

import com.example.be.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Integer> {
    Account findByUsername(String userName);
    boolean existsByUsername(String userName);
    Account findByStudent_Id(Integer student_id);

    Account findByTeacher_Id(Integer teacher_id);

}
