package com.example.gym_attendence.repository;

//import com.kiu.gym_management_system.entity.IclockTransactionEntity;

import com.example.gym_attendence.entity.IclockTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IclockTransactionRepository extends JpaRepository<IclockTransactionEntity, Integer> {

    List<IclockTransactionEntity> findByEmpCode(@Param("emp_code") String empCode);

    List<IclockTransactionEntity> findByEmpCodeAndPunchTime(@Param("emp_code") String empCode, @Param("punch_time") Date punchTime);
}
