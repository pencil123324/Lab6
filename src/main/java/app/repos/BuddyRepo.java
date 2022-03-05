package app.repos;

import app.entities.BuddyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuddyRepo extends JpaRepository<BuddyInfo, String>
{

}
