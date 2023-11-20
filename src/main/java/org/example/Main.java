package org.example;

import net.openhft.compiler.CompilerUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public Main() {
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Factory<Person> factory = new Factory<>();
        String myJavaCode = factory.generateCode(Person.class);
        System.out.println(myJavaCode);

        Generator<Person> runner = factory.getJsonGenerator(Person.class);
        String json = runner.generate(new Person(1L, "alex", "savelev"));
        System.out.println(json);

        Factory<Empty> f = new Factory<>();
        String javaCode = f.generateCode(Empty.class);
        System.out.println(javaCode);

        Generator<Empty> r = f.getJsonGenerator(Empty.class);
        String jsonEmpty = r.generate(new Empty());
        System.out.println(jsonEmpty);


    }
}