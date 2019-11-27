package com.study.persistence.DTO;

import java.util.List;

public interface EntityToDTO<T> {
    T getDTOFromDB(int id);
    List<T> getAllDTOFromDB();
}
