package app.controllers;

import app.entities.BuddyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import app.repos.BuddyRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BuddyInfoController
{
    @Autowired
    BuddyRepo buddyRepo;

    @GetMapping("/buddies")
    public List<BuddyInfo> buddies()
    {
        List<BuddyInfo> buddies = new ArrayList<>();
        for(BuddyInfo b : buddyRepo.findAll())
        {
                buddies.add(b);
        }

        // Add sample buddy so that some JSON data is seen in localhost:8080/buddies
        // This is because currently the repository for buddies is empty
        buddies.add(new BuddyInfo("test", "12", "a"));

        return buddies;
    }
}
