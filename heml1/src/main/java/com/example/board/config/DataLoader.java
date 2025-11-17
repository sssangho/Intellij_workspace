package com.example.board.config;

import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {
    private final BoardRepository boardRepository;

    @PostConstruct
    public void init() {
        // 기존 데이터가 없을 때만 테스트 데이터 추가
        if (boardRepository.count() == 0) {
            boardRepository.save(Board.builder()
                    .title("환영합니다!")
                    .content("게시판에 오신 것을 환영합니다. 이 게시판은 스프링부트와 H2 데이터베이스를 사용하여 만들어졌습니다.")
                    .writer("관리자")
                    .build());

            boardRepository.save(Board.builder()
                    .title("게시판 사용 방법")
                    .content("1. 새 글 작성 버튼을 클릭하여 글을 작성할 수 있습니다.\n2. 목록에서 제목을 클릭하면 상세 내용을 볼 수 있습니다.\n3. 상세 페이지에서 수정과 삭제가 가능합니다.")
                    .writer("관리자")
                    .build());

            boardRepository.save(Board.builder()
                    .title("스프링부트 게시판")
                    .content("이 게시판은 스프링부트 3.2와 JDK 17을 사용하여 개발되었습니다. 부트스트랩을 활용한 반응형 UI를 제공합니다.")
                    .writer("개발자")
                    .build());
        }
    }
} 