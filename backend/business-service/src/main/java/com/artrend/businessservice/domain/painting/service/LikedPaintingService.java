package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.LikeDto;
import com.artrend.businessservice.domain.painting.repository.LikedPaintingRepositoryCustom;

import java.io.IOException;

public interface LikedPaintingService extends LikedPaintingRepositoryCustom {
    void like(LikeDto likeDto, String token) throws IOException;
    void cancelLike(LikeDto likeDto, String token) throws IOException;
}