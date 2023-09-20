package camput.controller;

import camput.Dto.CampCommentDto;
import camput.Service.CampCommentService;
import camput.domain.Commented;
import camput.domain.CommentedImageFile;
import camput.repository.CampImageFileRepository;
import camput.repository.CommentedImageFileRepository;
import camput.repository.CommentedRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
@Slf4j
public class ReviewControllerTest {

    @Autowired
    CommentedRepository commentedRepository;
    @Autowired
    CommentedImageFileRepository commentedImageFileRepository;

    @Test
    public void createReview() {
        //given
        Commented commented = commentedRepository.save(Commented.builder().build());

        //when

        //then

    }
}