package app.controllers;


import app.entities.AddressBook;
import app.entities.BuddyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import app.repos.AddressBookRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AddressBookController
{
    @Autowired
    AddressBookRepo addressBookRepo;

    // Probably should have HTML in stand-alone files...

    // ***** SPA mappings ****

    @GetMapping("addressBookOverview")
    public String loadSPA()
    {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <title>SPA Address Book App</title>\n" +
                "    <script type=\"text/javascript\" src=\"application.js\"></script>\n" +
                "</head>\n" +
                "\n" +
                "<body onload=\"loadAddressBooks();\">\n" +
                "\n" +
                "<h1>SPA Addressbook</h1>\n" +
                "\n" +
                "<div>\n" +
                "    <button onclick=\"addAddressBook();\">Create Address Book</button>\n" +
                "</div>\n" +
                "<div id = addressBooks></div>\n" +
                " <div>\n" +
                "<label for=\"name\">Name:</label>\n" +
                "<input type=\"text\" id=\"name\">\n" +
                "<label for=\"address\">Address:</label>\n" +
                "<input type=\"text\" id=\"address\">\n" +
                "<label for=\"phone\">Phone:</label>\n" +
                "<input type=\"text\" id=\"number\">\n" +
                "</div>" +
                "</body>\n" +
                "</html>\n";
    }

    @PostMapping("/addBookSPA")
    public void addBookSPA(@RequestBody String body)
    {
        this.addressBookRepo.save(new AddressBook());
    }

    @GetMapping("/addressBooks")
    List<AddressBook> getAddressBooks()
    {
        List<AddressBook> books = new ArrayList<>();

        for(AddressBook b : addressBookRepo.findAll())
        {
            books.add(b);
        }

        return books;
    }

    @PostMapping("/addBuddy")
    public void addBuddy(@RequestBody String body)
    {
        String[] information = body.split("&");
        Optional<AddressBook> book = this.addressBookRepo.findById(Long.parseLong(information[0]));

        // Ideally check that the request addressbook actually exists in backend, asusming that it does
        book.orElse(null).addBuddy(new BuddyInfo
                (
                    information[1], // Name
                    information[2], // Address
                    information[3] // Number
                ));
    }

    //******** Static HTML ***********

    @GetMapping("/showAddressBooks")
    public String showAddressBook()
    {
        return getAddressBookResponse();
    }

    @GetMapping("/addBookForm")
    public String addBook()
    {
        return " <form action = \"http://localhost:8080/showAddressBooks/\" method = \"post\">\n" +
                "  <label>Added book</label><br>\n" +
                "  <input type=\"submit\" value=\"Continue\" name=\"something2\">\n" +
                "</form> ";
    }

    @PostMapping("/showAddressBooks")
    public String showAddressBooks(@RequestBody String body)
    {
        // Called by addBook()

        System.out.println("Body is: " + body);
        if(!body.isEmpty())
        {
            AddressBook book = new AddressBook();
            book.addBuddy(new BuddyInfo("a", "45 nan", "1234"));
            book.addBuddy(new BuddyInfo("s", "345 sdf", "34534"));
            book.addBuddy(new BuddyInfo("d", "ert fdgyh", "6546"));
            this.addressBookRepo.save(book);
        }

        return getAddressBookResponse();
    }

    private String getAddressBookResponse()
    {
        return " <!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>AddressBooks</h1>\n" +
                addressBooks() +
                "<button type=\"button\" onclick=\"location.href='http://localhost:8080/addBookForm/'\">Add book!</button>  \n" +
                "\n" +
                "</body>\n" +
                "</html> ";
    }

    private String addressBooks()
    {
        StringBuilder response = new StringBuilder();
        response.append("<ol>");
        for(AddressBook a : addressBookRepo.findAll())
        {
            response.append("<li>AddressBook" + a.getId() + "</li>");
            response.append("<ul>");
            for(BuddyInfo b : a.getBuddies())
            {
                response.append("<li>" + b.getName() + ", " + b.getAddress() + ", " + b.getPhoneNumber() + "</li>");
            }
            // As of right now buddy button does not do anything; same idea as address book where clicking it brings
            // user to a form where they add buddy information to create a new buddy
            response.append("<li><button>Add Buddy</button></li>");
            response.append("</ul>");
        }
        response.append("</ol>");
        return response.toString();
    }
}
