package com.example.account.dto;

import com.example.account.domain.Account;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

public class CreateAccount {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        @Min(1)
        private Long userId;

        @NotNull
        @Min(100)
        private Long initialBalance;
    }

    @Getter // getter 메서드 자동 생성
    @Setter // setter 메서드 자동 생성
    @NoArgsConstructor // 매개변수가 없는 기본 생성자 생성
    @AllArgsConstructor // 모든 필드를 파라미터(인수)로 받는 생성자 자동 생성
    @Builder // 빌더 패턴으로 객체를 유연하게 생성할 수 있도록 설정
    public static class Response {
        private Long userId;
        private String accountNumber;
        private LocalDateTime registeredAt;

        public static Response from(AccountDto accountDto) {
            return Response.builder()
                    .userId(accountDto.getUserId())
                    .accountNumber(accountDto.getAccountNumber())
                    .registeredAt(accountDto.getRegisteredAt())
                    .build();
        }
    }
}
