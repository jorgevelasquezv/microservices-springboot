package co.com.pragma.store.shopping.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    private Long id;

    private String numberId;

    private String firstName;

    private String lastName;

    private String email;

    private String photoUrl;

    private String region;

    private String state;

}
