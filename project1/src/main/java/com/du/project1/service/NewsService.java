package com.du.project1.service;

import com.du.project1.entity.News;
import com.du.project1.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> findAllNews() {
        return newsRepository.findAll();
    }
}

