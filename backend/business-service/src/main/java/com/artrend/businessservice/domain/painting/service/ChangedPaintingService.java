package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.ChangedPaintingDto;
import com.artrend.businessservice.domain.painting.dto.MemberDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.List;

public interface ChangedPaintingService {
    List<ChangedPaintingDto> findChangedPaintingsAll(Long memberId, Pageable pageable);

    void deleteChangedPainting(MemberDto memberDto);

    Blob changePainting(Blob url, Long paintingId, String authorization);
}
