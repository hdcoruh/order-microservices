package com.coruh.task.order.model.dto;

import com.coruh.task.order.model.entity.InstallationSlot;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

//TO-DO not null, greater than zero validation
@Data
public class OrderRequest {
    @NotBlank(message = "Product Name is mandatory")
    private String productName;

    @NotBlank(message = "Package Name is mandatory")
    private String packageName;
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Installation Date is mandatory")
    private LocalDate installationDate;
    @NotNull(message = "Installation Slot is mandatory")
    private InstallationSlot installationSlot;

    @NotBlank(message = "Customer Name is mandatory")
    private String customerName;

    @NotBlank(message = "Customer Email is mandatory")
    @Email(message = "Wrong Email Format")
    private String customerEmail;
}
