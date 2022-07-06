package org.onefamily.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Employee {
    int id;
    @JsonProperty("employee_name")
    String name;
    @JsonProperty("employee_salary")
    String salary;
    @JsonProperty("employee_age")
    int age;
    @JsonProperty("profile_image")
    String profileImage;
}
