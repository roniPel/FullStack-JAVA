package com.mindali.songs.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "songs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YoutubeData {
    @Id
    private String id;
    private String name;
    private String description;
    private String imageURL;

}

