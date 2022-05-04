package com.cos.photogramstart.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
//public class CMRespDto {
//
//
//    private int code; //1은 성공 -1 은 실패
//    private String message;
//    private Map<String, String> errorMap;
//
//
//}
public class CMRespDto<T> {


    private int code; //1은 성공 -1 은 실패
    private String message;
    private T data;


}