package com.xinghua24.bookmark.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // ignore null fields
public class ResponseBean {
    private Integer status;
    private String msg;
    private List<String> details;

    public static ResponseBean error(String msg) {
        return new ResponseBeanBuilder().status(500).msg(msg).build();
    }

    public static ResponseBean error(String msg, List<String> details) {
        return new ResponseBeanBuilder().status(500).msg(msg).details(details).build();
    }
}
