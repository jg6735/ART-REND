package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.dto.ChangedPaintingDto;
import com.artrend.businessservice.domain.painting.dto.MemberDto;
import com.artrend.businessservice.domain.painting.service.ChangedPaintingService;
import com.artrend.businessservice.global.common.CountDataResponse;
import com.artrend.businessservice.global.common.DataResponse;
import com.artrend.businessservice.global.common.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/changed")
@Slf4j
public class ChangedPaintingController {
    private final ChangedPaintingService changedPaintingService;

    @Operation(summary = "회원별 변환한 그림 조회하기", description = "해당 회원의 변환한 그림 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("{member_id}")
    public ResponseEntity<? extends DataResponse> findChangedPaintingsAll(@PathVariable("member_id")
                                                                              Long memberId, Pageable pageable) {
        List<ChangedPaintingDto> paintings = changedPaintingService.findChangedPaintingsAll(memberId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new CountDataResponse(paintings, paintings.size()));
    }

    @Operation(summary = "해당 변환된 그림 삭제하기", description = "해당 ID 값의 변환된 그림을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @DeleteMapping()
    public ResponseEntity<? extends MessageResponse> findChangedPainting(@RequestBody MemberDto memberDto) {
        changedPaintingService.deleteChangedPainting(memberDto);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }
}