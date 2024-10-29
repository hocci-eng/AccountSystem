package com.example.account.service;

import com.example.account.exception.AccountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import static com.example.account.type.ErrorCode.ACCOUNT_TRANSACTION_LOCK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LockServiceTest {

    @Mock
    private RedissonClient redissonClient;

    @Mock
    private RLock rlock;

    @InjectMocks
    private LockService lockService;

    @Test
    void successGetLock() throws InterruptedException {
        // given
        given(redissonClient.getLock(anyString()))
                .willReturn(rlock);
        given(rlock.tryLock(anyLong(), anyLong(), any() ))
                .willReturn(true);

        // when
        // then
        assertDoesNotThrow(() -> lockService.lock("123"));
    }

    @Test
    void failGetLock() throws InterruptedException {
        // given
        given(redissonClient.getLock(anyString()))
                .willReturn(rlock);
        given(rlock.tryLock(anyLong(), anyLong(), any() ))
                .willReturn(false);

        // when
        AccountException exception = assertThrows(AccountException.class,
                () -> lockService.lock("123"));

        // then
        assertEquals(ACCOUNT_TRANSACTION_LOCK, exception.getErrorCode());
    }
}