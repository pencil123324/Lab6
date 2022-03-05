package app.repos;

import app.entities.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepo extends JpaRepository<AddressBook, Long>
{

}
