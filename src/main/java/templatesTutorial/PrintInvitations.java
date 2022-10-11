package templatesTutorial;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.beans.XMLDecoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PrintInvitations {

    public static List<Person> getPersonsFromXml() throws IOException {
        XMLDecoder d = new XMLDecoder(
                new BufferedInputStream(
                        Files.newInputStream(Paths.get("friends.xml"))));
        return (List<Person>) d.readObject();
    }

    public static void generateTemplate(List<Person> partyGuests) throws IOException {
        VelocityContext vcontext = new VelocityContext();
        Template template = Velocity.getTemplate("lettertemplate.vm");

        FileWriter invitations = new FileWriter("invitations.txt");
        for(Person person : partyGuests) {
            vcontext.put("person", person);
            template.merge(vcontext, invitations);
        }
        invitations.close();
    }

    public static void main(String[] args) throws IOException {
        List<Person> persons = getPersonsFromXml();
        generateTemplate(persons);
    }
}
