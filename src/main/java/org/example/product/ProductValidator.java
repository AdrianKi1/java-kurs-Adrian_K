package org.example.product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProductValidator {

    public static List<String> validate(Object obj) {

        List<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {

                Object value = field.get(obj);
                if (field.isAnnotationPresent(NotBlank.class)) {

                    }
                }

                if (field.isAnnotationPresent(Positive.class)) {

                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    MaxLength maxLength = field.getAnnotation(MaxLength.class);
                    if (value != null && value.toString().length() > maxLength.value()) {

                    }
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return errors;
    }
}