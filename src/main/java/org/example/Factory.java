package org.example;

import net.openhft.compiler.CompilerUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Factory<T> {
    String generateCode (Class<T> clazz) {
        StringBuilder javaCode = new StringBuilder("package org.example;\n" +
                "class JsonGenerator" + clazz.getSimpleName() + " implements Generator<" + clazz.getName() + "> {\n" +
                "   @Override \n" +
                "   public String generate(" + clazz.getName() + " o) {\n" +
                "       return \"{\" + ");
        if (clazz.getDeclaredFields().length == 0) {
            javaCode.append("\"}\";\n}}");
            return javaCode.toString();
        }
        for (Field field : clazz.getDeclaredFields()) {
            for (Method method : clazz.getMethods()) {
                if (method.getName().toLowerCase().equals("get" + field.getName().toLowerCase())) {
                    boolean isStr = field.getType().getName().contains("String");
                    javaCode.append("\"\\\"").append(field.getName()).append("\\\": ").append(isStr ? "\\\"": "").append("\" + o.").append(method.getName()).append("() + ").append("\"").append(isStr ? "\\\"": "").append(", \" + ");
                    break;
                }
            }
        }
        javaCode.delete(javaCode.length()-6, javaCode.length()).append("\" + ");
        javaCode.append("\"}\"; \n");
        javaCode.append("    }\n}");
        return javaCode.toString();
    }

    Generator<T> getJsonGenerator (Class<T> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String java_code = generateCode(clazz);
        Class<?> jsonGeneratorClass = CompilerUtils.CACHED_COMPILER.loadFromJava("org.example.JsonGenerator" + clazz.getSimpleName(), java_code);

        return (Generator<T>) jsonGeneratorClass.newInstance();
    }
}
