package rifqimuhammadaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private int id;
    private String username;
    private String password;
    private String fullName;
    private String gender;
    private String address;
    private String phoneNumber;
    private String image;
    private String status;

}
