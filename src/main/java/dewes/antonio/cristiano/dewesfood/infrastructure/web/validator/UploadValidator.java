package dewes.antonio.cristiano.dewesfood.infrastructure.web.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import dewes.antonio.cristiano.dewesfood.util.FileType;

public class UploadValidator implements ConstraintValidator<UploadConstraint, MultipartFile> {

	private List<FileType> acceptedFileTypes;
		
	@Override
	public void initialize(UploadConstraint constraintAnnotation) {
		acceptedFileTypes = Arrays.asList(constraintAnnotation.acceptedTypes());
	}


	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		
		if(multipartFile == null) {
			return true;
		}
		
		for( FileType fileType : acceptedFileTypes) {
			if(fileType.sameOf(multipartFile.getContentType())) {
				return true;
			}
		}
		
		return false;
	}

}