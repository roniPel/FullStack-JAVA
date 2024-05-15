package com.mindali.songs.repositories;

import com.mindali.songs.beans.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<UserDetails,Integer> {
}
