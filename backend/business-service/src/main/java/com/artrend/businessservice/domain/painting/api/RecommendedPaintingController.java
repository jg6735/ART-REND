package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.dto.RecommendPaintingDto;
import com.artrend.businessservice.domain.painting.service.RecommendedPaintingService;
import com.artrend.businessservice.global.common.CountDataResponse;
import com.artrend.businessservice.global.common.DataResponse;
import com.artrend.businessservice.global.common.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
@Slf4j
public class RecommendedPaintingController {
    private final RecommendedPaintingService recommendedPaintingService;

    @Operation(summary = "메인 페이지 추천 그림 목록 조회", description = "해당 회원에게 메인 페이지에 보여질 " +
            "추천된 그림 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/{memberId}")
    public ResponseEntity<? extends DataResponse> findRecommendedPaintings(@PathVariable("memberId")
                                                                               Long memberId,
                                                                           @RequestHeader(value = "Authorization") String authorization,
                                                                           Pageable pageable) {
        List<RecommendPaintingDto> recommendedPaintings =
                recommendedPaintingService.findRecommendedPaintings(memberId, authorization, pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CountDataResponse(recommendedPaintings, recommendedPaintings.size()));
    }

    @Operation(summary = "메인 페이지를 갈 때마다 추천 그림 리스트를 응답 받습니다.", description = "유저 로그 기반" +
            " 회원에게 맞는 그림 리스트들을 메인 페이지에 띄우는 용도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping
    public ResponseEntity<? extends DataResponse> mainPageRecommend(@RequestHeader(value = "Authorization") String authorization) {
        Object list = recommendedPaintingService.mainPageRecommend(authorization).getBody();
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(list));
    }
}
