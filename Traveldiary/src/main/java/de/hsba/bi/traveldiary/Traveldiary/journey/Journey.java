package de.hsba.bi.traveldiary.Traveldiary.journey;

import de.hsba.bi.traveldiary.Traveldiary.user.User;

import javax.persistence.*;
import java.util.*;

@Entity
public class Journey {

    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    private String name;

    @ManyToOne(optional = false)
    private User owner;

    //Delete stages if journey is deleted
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "journey")
    private List<JourneyStage> stages;

    //For the publishing feature
    @Basic(optional = false)
    private boolean forAll;

    //Optional
    private String description;

    //Default Constructor
    public Journey() {
    }

    //Getter and Setter + individual methods
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

    //calculates the kilometer sum of all stages in the journey
    public double computeKilometers() {
        double kilometers = 0;
        for (JourneyStage stage : getStages()) {
            kilometers += stage.getKilometer();
        }
        return kilometers;
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

    public boolean isForAll() {
        return forAll;
    }

    public void setForAll(boolean forAll) {
        this.forAll = forAll;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
