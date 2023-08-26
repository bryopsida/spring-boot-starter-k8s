/* (C) 2023 */
package io.github.springboottemplate.entities;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EchoHistory {

    private UUID id;

    private String message;

    private Date timestamp;
}
