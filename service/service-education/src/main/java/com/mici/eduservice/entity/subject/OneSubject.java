package com.mici.eduservice.entity.subject;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class OneSubject {

    private String id;

    private String title;

    private List<TwoSubject> children;
}
