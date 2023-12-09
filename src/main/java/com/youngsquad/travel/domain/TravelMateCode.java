package com.youngsquad.travel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRAVEL_MATE_CODE")
public class TravelMateCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TRAVEL_MATE_CODE_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="TRAVEL_ID")
    private Travel travel;
    @Column(name="MATE_CODE")
    private String mateCode;
    @Column(name="EXPIRE_DATE_TIME")
    private LocalDateTime expireTime;

    public static TravelMateCode from(Travel travel){
        return TravelMateCode.builder()
                .expireTime(ofExpireDateTime())
                .mateCode(generateRandomCode(6))
                .travel(travel)
                .build();
    }
    private static LocalDateTime ofExpireDateTime(){
        return LocalDateTime.now().plusMinutes(30);
    }

    private static String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomCode = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomCode.append(characters.charAt(index));
        }

        return randomCode.toString();
    }
}
