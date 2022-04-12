package rifqimuhammadaziz.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Department {

    private int id;
    private String name;

    @Override
    public String toString() {
        return name.toUpperCase();
    }
}
