package com.youngsquad.record.application;

import com.youngsquad.common.exception.BusinessException;
import com.youngsquad.common.exception.ErrorCode;
import com.youngsquad.common.s3.S3Service;
import com.youngsquad.record.domain.Record;
import com.youngsquad.record.domain.service.RecordRepository;
import com.youngsquad.record.presentation.response.CursorResult;
import com.youngsquad.record.presentation.response.RecordResponse;
import com.youngsquad.record.presentation.response.TravelNameResponse;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.TravelParticipate;
import com.youngsquad.travel.domain.service.TravelParticipateRepository;
import com.youngsquad.travel.domain.service.TravelRepository;
import com.youngsquad.user.domain.model.User;
import com.youngsquad.user.domain.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecordReadService {
    private final RecordRepository recordRepository;
    private final TravelParticipateRepository travelParticipateRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private static final int DEFAULT_SIZE = 10;

    public CursorResult<RecordResponse> getRecordsByLatestTravel(Long cursorId, Integer size, Long uid) {
        if (size == null) size = DEFAULT_SIZE;

        User user = userRepository.findById(uid)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        TravelParticipate latestTravelParticipate = travelParticipateRepository.findTopByTeamMemberOrderByCreateDateDesc(user)
                .orElseThrow(() -> new BusinessException(ErrorCode.TRAVEL_NOT_FOUND));

        Travel latestTravel = latestTravelParticipate.getTravel();

        return getRecordsByTravel(latestTravel.getId(), cursorId, uid, size);
    }

    public CursorResult<RecordResponse> getRecordsByTravel(Long travelId, Long cursorId, Long uid, Integer size) {
        Pageable pageRequest = PageRequest.of(0, size); // Page 번호가 0부터 시작하도록 수정
        Slice<Record> records = recordRepository.findRecordsByTravelId(travelId, cursorId, pageRequest);
        Long lastIndex = records.getContent().isEmpty() ? -1L :
                records.getContent().get(records.getContent().size() - 1).getId();

        List<RecordResponse> filteredRecords = records.getContent().stream()
                .filter(record -> record.getRecorder().getId().equals(uid)) // recorderId가 uid와 일치하는 것만 필터링
                .map(record -> RecordResponse.fromRecordList(record, this))
                .collect(Collectors.toList());

        return new CursorResult<>(
                filteredRecords.size(),
                lastIndex,
                records.isLast(),
                filteredRecords);
    }

    public List<TravelNameResponse> getTravelNameFromRecord(Long uid){
        return TravelNameResponse.fromTravelNames(getTravelList(uid));
    }

    private List<Travel> getTravelList(Long uid){
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        List<TravelParticipate> travelParticipates = travelParticipateRepository.findByTeamMemberOrderByCreateDateDesc(user);
        return travelParticipates.stream()
                .map(tp -> tp.getTravel())
                .collect(Collectors.toList());
    }

    public String getRecordImageURL(String imagePath){
        return s3Service.getDownloadPresignedURL(imagePath);
    }

}
