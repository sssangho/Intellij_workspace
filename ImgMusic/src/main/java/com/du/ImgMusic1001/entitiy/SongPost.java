package com.du.ImgMusic1001.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;      // 노래 제목
    private String artist;     // 가수

    @Column(columnDefinition = "TEXT")
    private String lyrics;     // 노래 가사

    private String audioPath;  // 노래 파일 경로 또는 링크
    private LocalDateTime uploadedAt;

    // 이미지 여러 장 가능
    @OneToMany(mappedBy = "songPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SongImage> images = new ArrayList<>();
}

