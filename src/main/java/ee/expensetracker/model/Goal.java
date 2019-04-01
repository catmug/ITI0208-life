package ee.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goal_seq")
    @SequenceGenerator(name = "goal_seq", sequenceName = "seq_goal", allocationSize = 1)
    @Column(name = "goal_id")
    private Long goalId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "amount", precision=16, scale=2)
    private double amount;

    @Column(name = "month")
    private int month;
}
