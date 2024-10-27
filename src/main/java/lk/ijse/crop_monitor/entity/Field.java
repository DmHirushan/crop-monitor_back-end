package lk.ijse.crop_monitor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "fields")
public class Field {
    @Id
    @Column(name = "field_code")
    private String fieldCode;
    @Column(name = "field_name")
    private String fieldName;
    @Column(name = "field_location")
    private Point fieldLocation;
    @Column(name = "field_size")
    private double fieldSize;
    @OneToMany(mappedBy = "field")
    @Column(name = "crops")
    private List<Crop> crops;
    @ManyToMany
    @Column(name = "staff")
    private List<Staff> staff;
    @Column(name = "image_1", columnDefinition = "LONGTEXT")
    private String image1;
    @Column(name = "image_2", columnDefinition = "LONGTEXT")
    private String image2;
}
