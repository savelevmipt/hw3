package org.example;

import net.openhft.compiler.CompilerUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Factory<T> {
    String generateCode (Class<T> clazz) {
        StringBuilder javaCode = new StringBuilder("package org.example;\n" +
                "class JsonGenerator implements Generator<" + clazz.getName() + "> {\n" +
                "   @Override \n" +
                "   public String generate(" + clazz.getName() + " o) {\n" +
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
        javaCode.delete(javaCode.length()-6, javaCode.length()).append("\" + ");
        javaCode.append("\"}\"; \n");
        javaCode.append("    }\n}");
        return javaCode.toString();
    }

    Generator<Person> getJsonGenerator (Class<T> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String java_code = generateCode(clazz);
        Class<?> jsonGeneratorСlass = CompilerUtils.CACHED_COMPILER.loadFromJava("org.example.JsonGenerator", java_code);

        return (Generator<Person>) jsonGeneratorСlass.newInstance();
    }
}
