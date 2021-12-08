package dewes.antonio.cristiano.dewesfood.infrastructure.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import dewes.antonio.cristiano.dewesfood.application.service.ImageService;

@Controller
public class ImageController {
	
	@Autowired
	private ImageService imgService;
	
	@ResponseBody	//avisa ao navegador que ele deve agrupar o retorno ao html
	@GetMapping(path = "/images/{type}/{imgName}", produces = MediaType.IMAGE_PNG_VALUE)//permite que o metodo seja usada para diversos caminhos, se adequando a necessidade
	public byte[] getBytes(
			@PathVariable("type") String type,
			@PathVariable("imgName") String imgName) {
		
		return imgService.getBytes(type, imgName);
	}
}
