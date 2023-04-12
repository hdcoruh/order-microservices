package com.coruh.task.product.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@Builder
public class PackageResponse {
    private UUID id;
    private String packageName;

}
