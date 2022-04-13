package rifqimuhammadaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private Department department;

}
