package com.example.yamldemo.controller;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/convert")
public class TiffController {

    @PostMapping("/tiff-to-pdf")
    public ResponseEntity<byte[]> convertTiffAndDelete(@RequestPart("file") MultipartFile file) {
        Path tempFilePath = null;
        
        try {
            // 1. Save the uploaded MultipartFile to a physical temporary file on the disk
            // This is necessary because iText works best with a file reference for multi-page TIFFs
            tempFilePath = Files.createTempFile("upload_", ".tiff");
            Files.copy(file.getInputStream(), tempFilePath, StandardCopyOption.REPLACE_EXISTING);
            File tiffFile = tempFilePath.toFile();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);

            int pageIndex = 1;
            boolean morePages = true;
            

            // 2. Perform the conversion logic
            while (morePages) {
            	writer.setCompressionLevel(9);
                try {
                    // Use the absolute path of the temp file
                    ImageData imageData = ImageDataFactory.createTiff(tiffFile.toURI().toURL(), true, pageIndex, true);
                    Image img = new Image(imageData);

                    PageSize ps = new PageSize(img.getImageWidth(), img.getImageHeight());
                    pdfDoc.addNewPage(ps);
                    
                    Document document = new Document(pdfDoc, ps);
                    img.scaleToFit(img.getImageWidth() * 72/300, 9);
                    img.setFixedPosition(pageIndex, 0, 0);
                    document.add(img);
                    
                    pageIndex++;
                } catch (Exception e) {
                    morePages = false; // No more pages in TIFF
                }
            }
            pdfDoc.close();

            // 3. Prepare the response
            byte[] pdfBytes = baos.toByteArray();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"converted.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        } finally {
            // 4. THE DELETION LOGIC
            // The 'finally' block ensures the file is deleted whether the conversion succeeded or failed.
            try {
                if (tempFilePath != null) {
                    boolean deleted = Files.deleteIfExists(tempFilePath);
                    if (deleted) {
                        System.out.println("Cleanup: Temporary TIFF file deleted successfully.");
                    }
                }
            } catch (Exception cleanupError) {
                System.err.println("Cleanup failed: Could not delete " + tempFilePath);
            }
        }
    }
}