package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.ErrorCodeInterface;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    private Integer resultCode;

    private String resultMessage;

    private String resultDescription;

    public static Result OK() {
        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("성공")
                .build()
                ;
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface) {
        return Result.builder()
                .resultCode(errorCodeInterface.getErrorCode())
                .resultMessage(errorCodeInterface.getDescription())
                .resultDescription("에러 발생")
                .build()
                ;
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface, Throwable tx) {
        return Result.builder()
                .resultCode(errorCodeInterface.getErrorCode())
                .resultMessage(errorCodeInterface.getDescription())
                .resultDescription(tx.getLocalizedMessage()) // TODO 변경해야 함 (위험)
                .build()
                ;
    }

    public static Result ERROR(ErrorCodeInterface errorCodeInterface, String resultDescription) {
        return Result.builder()
                .resultCode(errorCodeInterface.getErrorCode())
                .resultMessage(errorCodeInterface.getDescription())
                .resultDescription(resultDescription)
                .build()
                ;
    }
}
