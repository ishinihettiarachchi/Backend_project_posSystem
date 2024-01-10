package com.practicespringboot.pointofsale.DTO.paginated;

import com.practicespringboot.pointofsale.DTO.response.ResponseOrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedOrderResponseOrderDetails {
    private List<ResponseOrderDetailsDTO> list;
    private long dataCount;
}
