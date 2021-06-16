package br.com.adirvic.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id",updatable = false,nullable = false)
    private UUID id;

    private UUID userId;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    private String contentTask;

    private String descriptionTask;

    private StatusTask status;

    @Column(insertable = false)
    private LocalDateTime updatedDate;

    @PrePersist
    public void onCreate(){
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        updatedDate = LocalDateTime.now();
    }
}