package dewes.antonio.cristiano.dewesfood.util;

import lombok.Getter;

public enum FileType {
	
	PNG("image/png", "png"),
	JPG("image/jpeg", "jpg");
	
	@Getter
	String mimeType;
	@Getter
	String extension;
	
	FileType(String mimeType, String extension) {
		this.mimeType = mimeType;
		this.extension = extension;
	}
	
	public boolean sameOf(String mimeType) {
		return this.mimeType.equalsIgnoreCase(mimeType);
	}
	
	public static FileType of(String mimeType) {
		for (FileType fileType : values()) {
			if(fileType.sameOf(mimeType)) {
				return fileType;
			}
		}
		
		return null;
	}
}
