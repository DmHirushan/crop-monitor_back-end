package lk.ijse.crop_monitor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @Column(name = "staff_member_id")
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "designation")
    private String designation;
    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "joined_date")
    private Date joinedDate;
    @Column(name = "date_of_birth")
    private Date DOB;
    @Column(name = "address_line_1")
    private String addressLine1;
    @Column(name = "address_line_2")
    private String addressLine2;
    @Column(name = "address_line_3")
    private String addressLine3;
    @Column(name = "address_line_4")
    private String addressLine4;
    @Column(name = "address_line_5")
    private String addressLine5;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "staff")
    private Equipment equipment;

    @ManyToMany(mappedBy = "staff")
//    @Column(name = "fields")
    private List<Field> field;

    @OneToMany(mappedBy = "staff")
//    @Column(name = "vehicle")
    private List<Vehicle> vehicles;

    @ManyToMany(mappedBy = "staff")
    private List<CropDetails> cropDetails;
}
