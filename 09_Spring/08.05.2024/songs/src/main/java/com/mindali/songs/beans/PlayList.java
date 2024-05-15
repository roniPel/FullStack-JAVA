package com.mindali.songs.beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String playListName;
    private String name;
    @OneToMany
    private List<YoutubeData> myList;
}
