package com.du.ImgMusic1001.repository;

import com.du.ImgMusic1001.entitiy.SongImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongImageRepository extends JpaRepository<SongImage,Long> {
    // 필요 시 Post 기반으로 이미지 목록 조회 가능
    List<SongImage> findBySongPostId(Long postId);
}