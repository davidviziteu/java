package com.example.demo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
interface PersRepo extends CrudRepository<Person, Integer> {

    Person findPersonById(int id);
    @Modifying
    @Transactional
    @Query(value = "insert into users(name) VALUES (?1)", nativeQuery = true)
    int add(String name);


    @Query(value="select distinct f.friend_with_id, (select (count(user_id))" +
            " from friends where friend_with_id=f.friend_with_id) as fr from friends f order by fr desc limit ?1",
            nativeQuery=true)
    List<Map<Integer, Integer>> mostConnected(int top);


    @Query(value="select distinct f.friend_with_id, (select (count(user_id))" +
            " from friends where friend_with_id=f.friend_with_id) as fr from friends f order by fr asc limit ?1",
            nativeQuery=true)
    List<Map<Integer, Integer>> leastConnected(int top);
}