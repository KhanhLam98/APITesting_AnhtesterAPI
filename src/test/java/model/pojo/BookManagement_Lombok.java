package model.pojo;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookManagement_Lombok {
    private String name;
    private int category_id;
    private int price;
    private String release_date;
    private ArrayList<Integer> image_ids;
    private boolean status;
}
