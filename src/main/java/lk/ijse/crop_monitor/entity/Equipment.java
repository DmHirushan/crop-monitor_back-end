package lk.ijse.crop_monitor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @Column(name = "eqiupment_id")
    private String equipmentId;
    @Column(name = "equipment_name")
    private String name;
    @Column(name = "equipment_type")
    private EquipmentType equipmentType;
    @Column(name = "availability_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "staff_member_id")
//    @Column(name = "assigned_staff_details")
    private Staff assignedStaffDetails;

    @ManyToOne
    @JoinColumn(name = "field_code")
//    @Column(name = "assigned_field_details")
    private Field assignedFieldDetails;
}
