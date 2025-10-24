package com.HUSRTbdBiomedica.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.HUSRTbdBiomedica.app.entity.TipoDocumento;

@Service
public class UploadFileService {
	
	private String upload_folder = "./src/main/resources/files/";
	private String upload_sysfolder = "./src/main/resources/sysfiles/";
	private String images_folder = "./src/main/resources/static/images/";
	private String guides_folder = "./src/main/resources/guias/";
	private String calibraciones_folder = "./src/main/resources/calibraciones/";
	private String calificaciones_folder = "./src/main/resources/calificaciones/";
	private String validaciones_folder = "./src/main/resources/validaciones/";
	private String imagescall_folder = "./src/main/resources/static/images/llamados/";
	private String licencias_folder = "./src/main/resources/licencias/";
	//sys
	private String syshv_folder = "./src/main/resources/static/images/sys/";
	
	//documentosBiomedica
	private String registro_sanitario_folder = "./src/main/resources/documentos/registroSanitario/";
	private String factura_folder = "./src/main/resources/documentos/factura/";
	private String garantia_folder = "./src/main/resources/documentos/garantia/";
	private String recepcion_tecnica_folder = "./src/main/resources/documentos/recepcionTecnica/";
	private String acta_entrega_folder = "./src/main/resources/documentos/actaEntrega/";
	private String capacitacion_folder = "./src/main/resources/documentos/capacitacion/";
	private String manual_operacion_folder = "./src/main/resources/documentos/manualOperacion/";
	private String ficha_tecnica_folder = "./src/main/resources/documentos/fichaTecnica/";
	private String protocolo_limpieza_folder = "./src/main/resources/documentos/protocoloLimpieza/";
	private String guia_rapida_folder = "./src/main/resources/documentos/guiaRapida/";
	private String otro_folder = "./src/main/resources/documentos/otro/";
	
	public void saveFile(MultipartFile file,Long id) throws IOException{
		
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(upload_folder +id+ file.getOriginalFilename());
			Files.write(path, bytes);
		}
	}
	
	public String saveDocumentBiomedica(MultipartFile file, TipoDocumento tipoDocumento,long idEquipo) throws IOException{
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			String pathFile  = "";
			if(tipoDocumento.getIdTipoDocumento() == 1) {
				 pathFile = registro_sanitario_folder + idEquipo + "--" + file.getOriginalFilename();
			}else if (tipoDocumento.getIdTipoDocumento() == 2) {
				 pathFile = factura_folder + idEquipo + "--" + file.getOriginalFilename();
			}else if (tipoDocumento.getIdTipoDocumento() == 3) {
				 pathFile = garantia_folder + idEquipo + "--" + file.getOriginalFilename();
			}else if (tipoDocumento.getIdTipoDocumento() == 4) {
				 pathFile = recepcion_tecnica_folder + idEquipo + "--" + file.getOriginalFilename();
			}else if (tipoDocumento.getIdTipoDocumento() == 5) {
				 pathFile = acta_entrega_folder + idEquipo + "--" + file.getOriginalFilename();
			}else if (tipoDocumento.getIdTipoDocumento() == 6) {
				 pathFile = capacitacion_folder + idEquipo + "--" + file.getOriginalFilename();
			}else if (tipoDocumento.getIdTipoDocumento() == 7) {
				 pathFile = manual_operacion_folder + idEquipo + "--" + file.getOriginalFilename();
			}else if (tipoDocumento.getIdTipoDocumento() == 8) {
				 pathFile = ficha_tecnica_folder + idEquipo + "--" + file.getOriginalFilename();
			}else if (tipoDocumento.getIdTipoDocumento() == 9) {
				 pathFile = protocolo_limpieza_folder + idEquipo + "--" + file.getOriginalFilename();
			}else if (tipoDocumento.getIdTipoDocumento() == 10) {
				 pathFile = guia_rapida_folder + idEquipo + "--" + file.getOriginalFilename();
			}else if (tipoDocumento.getIdTipoDocumento() == 11) {
				 pathFile = otro_folder + idEquipo + "--" + file.getOriginalFilename();
			}
			Path path = Paths.get(pathFile);
			Files.write(path, bytes);
			return pathFile;
		}else {
			return "";
		}
	}
	
	public void savesysFile(MultipartFile file,Long id) throws IOException{
		
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(upload_sysfolder +id+ file.getOriginalFilename());
			Files.write(path, bytes);
		}
	}
	public void saveImage(MultipartFile file,Long id) throws IOException{
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(images_folder +id+ file.getOriginalFilename());
			Files.write(path, bytes);
		}
	}
	public void saveImageLlamado(MultipartFile file,Long id) throws IOException{
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(imagescall_folder +id+ file.getOriginalFilename());
			Files.write(path, bytes);
		}
	}
	
	public void saveGuide(MultipartFile file,Long id) throws IOException{
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(guides_folder +id+ file.getOriginalFilename());
			Files.write(path, bytes);
		}
	}
	public String saveCalibracion(MultipartFile file, Long id) throws IOException{
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(calibraciones_folder +id+ file.getOriginalFilename());
			Files.write(path, bytes);
			return calibraciones_folder +id+ file.getOriginalFilename();
		}else {
			return "";
		}
	}
	
	public void saveValidacion(MultipartFile file, Long id) throws IOException{
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(validaciones_folder +id+ file.getOriginalFilename());
			Files.write(path, bytes);
		}
	}
	public void saveCalificacion(MultipartFile file, Long id) throws IOException{
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(calificaciones_folder +id+ file.getOriginalFilename());
			Files.write(path, bytes);
		}
	}
	public void saveSysHV(MultipartFile file, Long id) throws IOException{
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(syshv_folder+id+file.getOriginalFilename());
			Files.write(path, bytes);
		}
	
	}
	public void saveLicencias(MultipartFile file, Long id) throws IOException{
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(licencias_folder+id+file.getOriginalFilename());
			Files.write(path, bytes);
		}
	
	}
}
