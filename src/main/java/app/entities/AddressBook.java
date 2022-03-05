package app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AddressBook implements Serializable
{
    List<BuddyInfo> buddies;
    private Long id;

    public AddressBook()
    {
        buddies = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.PERSIST)
    public List<BuddyInfo> getBuddies()
    {
        return buddies;
    }

    public void setBuddies(List<BuddyInfo> buddies)
    {
        this.buddies = buddies;
    }

    public void addBuddy(BuddyInfo buddy)
    {
        if(buddy != null)
        {
            buddies.add(buddy);
        }
    }

    public BuddyInfo getBuddy(int index)
    {
        return ((ArrayList<BuddyInfo>)this.buddies).get(index);
    }

    public void removeBuddy(int index)
    {
        if(index < 0 || index >= buddies.size())
        {
            return;
        }

        ((ArrayList<BuddyInfo>)this.buddies).remove(index);
    }

    public void clear()
    {
        buddies.clear();
    }

    public int numberBuddies()
    {
        return buddies.size();
    }
}