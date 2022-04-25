package com.cvg.springboot.aws.sns.dto;

import com.cvg.springboot.aws.sns.enums.SnsEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptorProtocol {
    private SnsEnum protocol;
    private String endpoint;
}
