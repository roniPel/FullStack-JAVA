package com.JohnBryce.Exam130324.Advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private String key;
    private String value;
}
