package com.springboot.AWSImage.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PresignedUrlDownloadRequest;

import javassist.bytecode.ByteArray;

@Service
public class UploadService {

	@Autowired
	private AmazonS3 client;
	
	private String bucketname = "liveasyuploadimage";

	public Result uploadImage(MultipartFile[] file) {
		Result rs = new Result();
		List<Output> oslist = new ArrayList<Output>();
		Output ot = new Output();
		for(MultipartFile mf: file) {
			try {
				System.out.println(mf.getOriginalFilename());
				ot.setImage(mf.getBytes());
				oslist.add(ot);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * File fileobj = new File(file.getOriginalFilename()); try (FileOutputStream
		 * fos = new FileOutputStream(fileobj)){ fos.write(file.getBytes()); }
		 * catch(IOException e) { }
		 * 
		 * client.putObject(bucketname, file.getOriginalFilename(), fileobj);
		 * client.setObjectAcl(bucketname, file.getOriginalFilename(),
		 * CannedAccessControlList.PublicRead); //Calendar myCalendar = new
		 * GregorianCalendar(2021, Calendar.MAY, 18); //Date myDate =
		 * myCalendar.getTime(); //URL url = client.generatePresignedUrl(bucketname,
		 * file.getOriginalFilename(), myDate); URL url = client.getUrl(bucketname,
		 * file.getOriginalFilename()); System.out.println(url); fileobj.delete();
		 */
		rs.setList(oslist);
		return rs;
	}
	
	public File convertToFile(MultipartFile file) {
		File output = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(output)){
			fos.write(file.getBytes());
		}
		catch(IOException e) {
		}
		return output;
	}

	public Object getImage(Output output) {
		// TODO Auto-generated method stub
		ByteArrayInputStream bis = new ByteArrayInputStream(output.getImage());
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(bis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ImageIO.write(bi, "jpg", new File("demo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
