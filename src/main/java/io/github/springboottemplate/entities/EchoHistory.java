/* (C) 2023 */
package io.github.springboottemplate.entities;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
public class EchoHistory {

    @Id
    @GeneratedValue
    private UUID id;

    private String message;

    private Date timestamp;
}
