package org.example;

import net.openhft.compiler.CompilerUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
//        String className = "mypackage.MyClass";
//        String javaCode = "package mypackage;\n" +
//                "public class MyClass implements Runnable {\n" +
//                "    public void run() {\n" +
//                "        System.out.println(\"Hello World\");\n" +
//                "    }\n" +
//                "}\n";
//        Class aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(className, javaCode);
//        System.out.println(aClass.getName());
//
//        Runnable runner = (Runnable) aClass.newInstance();
//        runner.run();

        Factory factory = new Factory();
        String myjavaCode = factory.generateCode(Person.class);
        System.out.println(myjavaCode);
        Class jsonGenerator = CompilerUtils.CACHED_COMPILER.loadFromJava("mypackage.MyClass", myjavaCode);
        System.out.println(jsonGenerator.getName());

        Constructor<?> cons = jsonGenerator.getConstructor();
        Object object = cons.newInstance();




    }
}