package org.example;

import net.openhft.compiler.CompilerUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Factory<Person> factory = new Factory<>();
        String myjavaCode = factory.generateCode(Person.class);
        System.out.println(myjavaCode);

        Generator<Person> runner = factory.getJsonGenerator(Person.class);
        String json = runner.generate(new Person(1L, "alex", "savelev"));
        System.out.println(json);
    }
}