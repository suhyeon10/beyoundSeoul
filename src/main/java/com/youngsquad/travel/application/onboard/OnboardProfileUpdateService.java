package com.youngsquad.travel.application.onboard;

import com.youngsquad.travel.presentation.request.OnboardRequest;
import com.youngsquad.user.application.UserService;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.domain.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class OnboardProfileUpdateService {
    private final UserService userService;

    public void updateProfile(User user, String birth, String sex) {

        LocalDate parsedBirth = parseLocalDate(birth);
        String parsedSex = parseSex(sex);

        if (parsedBirth != null && parsedSex != null) {
            userService.updateUser(user, parsedBirth, parsedSex);
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
