package com.youngsquad.travel.application.onboard;

import com.youngsquad.travel.presentation.request.OnboardRequest;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.domain.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class OnboardProfileUpdateService {
    private final UserRepository userRepository;

    public void updateProfile(long uid, String birth, String sex) {
        User user = userRepository.findById(uid);

        LocalDate parsedBirth = parseLocalDate(birth);
        String parsedSex = parseSex(sex);

        if (parsedBirth != null && parsedSex != null) {
            user.updateUserProfile(parsedBirth, parsedSex);
            userRepository.save(user);
        }
    }

    private LocalDate parseLocalDate(String date) {
        if (date != null && !date.isEmpty()) {
            try {
                return LocalDate.parse(date);
            } catch (DateTimeParseException e) {
                // 로깅 또는 예외 처리
            }
        }
        return null;
    }

    private String parseSex(String sex) {
        if (sex != null && !sex.isEmpty()) {
            return sex;
        }
        return null;
    }
}
