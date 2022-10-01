package models;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
//@AllArgsConstructor
//@NoArgsConstructor

public class Contact {
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String Address;
    private String description;


}
