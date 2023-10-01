package com.example.schoolapp.utility;

import lombok.Data;

@Data
public class PaginationData<T> {
    private T content;
    private int pages;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
}
