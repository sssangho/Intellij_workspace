package com.du.Entity0930;

import com.du.Entity0930.entity.MyUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Entity0930ApplicationTests {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void testPersist() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin(); // 트랜잭션 시작

        MyUser  user = MyUser.builder()
                .name("김하나")
                .email("kim@korea.com")
                .password("123456")
                .build();

        em.persist(user);

        user.setName("테스트");

        em.flush(); // DB에 즉시 반영
        transaction.commit(); // 트랜잭션 커밋

        em.close(); // 엔티티 매니저 닫기
    }

    @Test
    void testTemplate() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();


        em.flush(); // DB에 즉시 반영
        transaction.commit(); // 트랜잭션 커밋

        em.close(); // 엔티티 매니저 닫기
    }

    @Test
    void testFind() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        MyUser user = em.find(MyUser.class, 5L);
        System.out.println(user);


        em.flush(); // DB에 즉시 반영
        transaction.commit(); // 트랜잭션 커밋

        em.close(); // 엔티티 매니저 닫기
    }


    @Test
    void testMerge() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin(); // 트랜잭션 시작

        MyUser  user = MyUser.builder()
                .id(5L)
                .name("관리자")
                .email("admin@korea.com")
                .password("99999")
                .build();

        em.merge(user);

        em.flush(); // DB에 즉시 반영
        transaction.commit(); // 트랜잭션 커밋

        em.close(); // 엔티티 매니저 닫기
    }

    @Test
    void testRemove() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        MyUser  user = em.find(MyUser.class, 4L);

        em.remove(user);

        em.flush(); // DB에 즉시 반영
        transaction.commit(); // 트랜잭션 커밋

        em.close(); // 엔티티 매니저 닫기
    }

z

}
