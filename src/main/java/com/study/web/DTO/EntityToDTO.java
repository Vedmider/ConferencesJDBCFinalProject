package com.study.web.DTO;

import java.util.List;

public interface EntityToDTO<T> {
    T getDTOFromDB(int id);
    List<T> getAllDTOFromDB();
}
