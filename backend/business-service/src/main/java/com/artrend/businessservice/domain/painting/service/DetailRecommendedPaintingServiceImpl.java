package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.repository.DetailRecommendedPaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DetailRecommendedPaintingServiceImpl implements DetailRecommendedPaintingService {
    private final DetailRecommendedPaintingRepository detailRecommendedPaintingRepository;

    @Override
    public Page<PaintingDto> findDetailRecommendedPaintings(Long id, Pageable pageable) {
        return detailRecommendedPaintingRepository.findDetailRecommendedPaintings(id, pageable);
    }
}