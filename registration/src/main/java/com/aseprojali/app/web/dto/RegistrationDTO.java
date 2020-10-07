package com.aseprojali.app.web.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

    @NotNull
    @NotEmpty
    @Pattern(
            regexp = "^(^\\+62\\s?|^0)(\\d{3,4}-?){2}\\d{3,4}$",
            message = "{invalid.phone}")
    private String phone;

    @NotNull
    @NotEmpty
    @Column(name = "first_name")
    private String firstname;

    @NotNull
    @NotEmpty
    @Column(name = "last_name")
    private String lastname;

    private int month;
    private int date;
    private int year;

    @Column(name = "gender")
    private String gender;

    @NotNull(message = "{email.required}")
    @Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]"
            + "(?:[A-Za-z0-9-]*[A-Za-z0-9])?",
            message = "{invalid.email}")
    @Column(name = "email")
    private String email;
}
