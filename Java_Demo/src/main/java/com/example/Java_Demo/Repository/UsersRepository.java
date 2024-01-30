package com.example.Java_Demo.Repository;
import com.example.Java_Demo.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByActiveKey(String activeKey);
    Optional<Users> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Users u set u.isEnabled = true where u.activeKey = :activeKey")
    void updateIsEnable(@Param("activeKey") String activeKey);


}