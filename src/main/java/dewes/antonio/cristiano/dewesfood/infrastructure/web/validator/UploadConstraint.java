package dewes.antonio.cristiano.dewesfood.infrastructure.web.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import dewes.antonio.cristiano.dewesfood.util.FileType;

@Documented
@Retention(RUNTIME)				//as informações da anotação ficam disponíveis para a JVM durante a execução
@Target({ FIELD, METHOD })		//pode ser usada em metodos ou atributos
@Constraint(validatedBy = UploadValidator.class)		//necessário para integrar ao Spring, especificando o nome da classe que vai ser utilizada com a annotation
public @interface UploadConstraint {
	
	String message() default "Arquivo inválido";
	FileType[] acceptedTypes();
	
	Class<?>[] groups() default {};
	Class< ? extends Payload> [] payload() default{};
}
