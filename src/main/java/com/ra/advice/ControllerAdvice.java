package com.ra.advice;

import com.ra.model.error.DataError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    // Sử dụng khi register account thiếu field
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // Xử lý exception theo key: value sử dụng Map: (Map<String, String>)
    public DataError<Map<String, String>> handlerErrorNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> map = new HashMap<>();
        exception.getFieldErrors().forEach(fieldError ->
                map.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return new DataError<>(map, 400);
    }

    // Sử dụng khi người dùng gửi sai URL ví dụ: cho thêm abcd sau URL
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError<String> handlerErrorNotFoundElement(Exception exception) {
        return new DataError<>(exception.getMessage(), 404);
    }
}
