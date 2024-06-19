package com.example.SpringBatch.SpringBatch_Gamma.Processors;
import com.example.SpringBatch.SpringBatch_Gamma.Model.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstname().toUpperCase();
        final String lastName = person.getLastname().toUpperCase();
        final Person transformedPerson = new Person();
        transformedPerson.setFirstname(firstName);
        transformedPerson.setLastname(lastName);
        return transformedPerson;
    }
}