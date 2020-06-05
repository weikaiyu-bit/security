package com.weikaiyu.bootactitivi.dao;
import com.weikaiyu.bootactitivi.pojo.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user,Integer> {

    user findByName(String s);
}
