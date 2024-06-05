package com.example.Exercise_BackEnd.Repositories;

import com.example.Exercise_BackEnd.Beans.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting,Integer> {
    List<Meeting> findAllByTeamId(int teamId);
}
