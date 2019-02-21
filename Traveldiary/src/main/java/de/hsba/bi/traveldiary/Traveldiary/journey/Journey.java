package de.hsba.bi.traveldiary.Traveldiary.journey;

import de.hsba.bi.traveldiary.Traveldiary.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Journey {

    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    private String name;

    @ManyToOne(optional = false)
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "journey")
    @OrderBy
    private List<JourneyStage> stages;

    public Journey() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public List<JourneyStage> getStages() {
        if (stages == null) {
            stages = new ArrayList<>();
        }
        return stages;
    }

    @PrePersist
    private void onPersist() {
        this.owner = User.getCurrentUser();
    }

    public boolean isOwnedByCurrentUser() {
        return this.owner != null && this.owner.equals(User.getCurrentUser());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Journey)) {
            return false;
        }

        Journey journey = (Journey) o;
        return id != null && id.equals(journey.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
