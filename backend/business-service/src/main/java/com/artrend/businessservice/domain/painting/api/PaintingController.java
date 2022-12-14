package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.dto.PaintingCondition;
import com.artrend.businessservice.domain.painting.dto.RecommendedDataDto;
import com.artrend.businessservice.domain.painting.dto.SearchCondition;
import com.artrend.businessservice.domain.painting.service.PaintingService;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.vo.SearchResponse;
import com.artrend.businessservice.global.common.CountDataResponse;
import com.artrend.businessservice.global.common.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/paintings")
@Slf4j
public class PaintingController {
    private final PaintingService paintingService;
    private final Environment env;

    @GetMapping("/health_check")
    public String check() {
        log.info("Server port={}", env.getProperty("local.server.port"));

        return String.format("This Service port is %s", env.getProperty("local.server.port"));
    }

    @Operation(summary = "그림 전체 조회", description = "등록된 모든 그림의 데이터를 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping
    public ResponseEntity<? extends DataResponse> findAllPaintings() {
        List<PaintingDto> paintingList = paintingService.findAllPaintings();

        return ResponseEntity.status(HttpStatus.OK).body(new CountDataResponse(paintingList, paintingList.size()));
    }

    @Operation(summary = "그림 개별 조회", description = "선택한 그림 ID의 모든 그림 정보를 가져옵니다." +
            " 추천된 그림이라면 CF 기반 추천 그림 리스트가, 추천되지 않은 그림이라면 CBF 기반 추천 그림 리스트가" +
            " 반환됩니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/{painting_id}/{member_id}")
    public ResponseEntity<? extends DataResponse> findPainting(@PathVariable("painting_id") Long paintingId,
                                                               @PathVariable("member_id") Long memberId,
                                                               Pageable pageable) {
        RecommendedDataDto result = paintingService.findPainting(paintingId, memberId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(result));
    }

    @Operation(summary = "그림 장르별 조회", description = "genre, artTrend, artist 별로 그림을 조회할 수 있습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/search")
    public ResponseEntity<? extends DataResponse> searchPaintings(SearchCondition condition, Pageable pageable) {
        SearchResponse response = paintingService.searchPaintings(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }

    @Operation(summary = "그림 통계별 조회", description = "조회수, 좋아요수, 변환수 별로 그림을 조회할 수 있습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/sort")
    public ResponseEntity<? extends DataResponse> sortPaintings(PaintingCondition condition, Pageable pageable) {
        List<PaintingDto> paintings = paintingService.sortPaintings(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(paintings));
    }
}
