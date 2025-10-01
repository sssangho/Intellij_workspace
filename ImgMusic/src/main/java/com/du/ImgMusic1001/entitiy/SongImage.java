package com.du.ImgMusic1001.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;  // 원본 파일명
    private String filePath;  // 서버에 저장된 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_post_id")
    private SongPost songPost;
}
