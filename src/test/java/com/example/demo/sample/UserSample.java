package com.example.demo.sample;

import com.example.demo.AdminApplicationTests;
import com.example.demo.model.entity.Member;
import com.example.demo.model.enumclass.MemberStatus;
import com.example.demo.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
public class UserSample extends AdminApplicationTests {

    private Random random;

    @Autowired
    private MemberRepository userRepository;

    @Test
    public void sampleCreate(){

        random = new Random();

        for(int i = 1 ; i < 1001; i++){
            // 가입 상태 랜덤
            int div = (random.nextInt(10)+1) % 2;
            MemberStatus status = (div == 0 ? MemberStatus.REGISTERED : MemberStatus.UNREGISTERED);

            Member user = Member.builder()
                    .account("TestUser"+i)
                    .password("password"+i)
                    .status(status)
                    .email("TestUser"+i+"@gmail.com")
                    .phoneNumber("010-1111-"+String.format("%04d", i))
                    .registeredAt(getRandomDate())
                    .unregisteredAt(status.equals(MemberStatus.UNREGISTERED) ? getRandomDate() : null )
                    .build();

            log.info("{}",user);
            userRepository.save(user);
        }


    }


    private LocalDateTime getRandomDate(){
        return LocalDateTime.of(2019,getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber());
    }

    private int getRandomNumber(){
        return random.nextInt(11)+1;
    }
}
