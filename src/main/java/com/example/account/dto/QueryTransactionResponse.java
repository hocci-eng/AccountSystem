package com.example.account.dto;

import com.example.account.type.TransactionResultType;
import com.example.account.type.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter // getter 메서드 자동 생성
@Setter // setter 메서드 자동 생성
@NoArgsConstructor // 매개변수가 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 파라미터(인수)로 받는 생성자 자동 생성
@Builder // 빌더 패턴으로 객체를 유연하게 생성할 수 있도록 설정
public class QueryTransactionResponse {

    private String accountNumber;
    private TransactionType transactionType;
    private TransactionResultType transactionResult;
    private String transactionId;
    private Long amount;
    private LocalDateTime transactedAt;

    public static QueryTransactionResponse from(TransactionDto transactionDto) {
        return QueryTransactionResponse.builder()
                .accountNumber(transactionDto.getAccountNumber())
                .transactionType(transactionDto.getTransactionType())
                .transactionResult(transactionDto.getTransactionResultType())
                .transactionId(transactionDto.getTransactionId())
                .amount(transactionDto.getAmount())
                .transactedAt(transactionDto.getTransactedAt())
                .build();
    }

}
