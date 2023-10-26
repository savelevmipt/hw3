package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Factory {
    String generateCode (Class<?> clazz) {
        StringBuilder javaCode = new StringBuilder("class JsonGenerator {\n" +
                "   String generate(" + clazz.getName() + " o) {\n" +
                "       return \"{\" + ");
        System.out.println(clazz.getFields().length);
        for (Field field : clazz.getDeclaredFields()) {
            for (Method method : clazz.getMethods()) {
                if (method.getName().toLowerCase().equals("get" + field.getName().toLowerCase())) {
                    javaCode.append("\"").append(field.getName()).append(": \" + o.").append(method.getName()).append("() + \", \" + ");
                    break;
                }
            }
        }
        javaCode.append("\"}\"; \n");
        javaCode.append("    }\n}");


        return javaCode.toString();
    }
}
