package com.mindali.songs.beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "playlists")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String playlistName;

    @ManyToMany(targetEntity = YoutubeData.class)
    private List<YoutubeData> songList;
}
