import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.net.URL;

public class TIFFToPDFConverter {

    public void convert(String sourceTiffPath, String destinationPdfPath) {
        try {
            PdfWriter writer = new PdfWriter(destinationPdfPath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            
            // Note: We don't initialize the Layout Document yet 
            // because we need to set page sizes dynamically.
            
            URL tiffUrl = new URL("file:" + sourceTiffPath);

            // In iText 8, we can use the ImageDataFactory to get specific pages
            // We loop until an exception occurs or we can check the count if known
            int pageCount = 1;
            boolean process = true;

            while (process) {
                try {
                    // This creates image data for a specific page (1-indexed)
                    // Signature: createTiff(URL url, boolean recover, int page, boolean direct)
                    ImageData imageData = ImageDataFactory.createTiff(tiffUrl, true, pageCount, true);
                    Image img = new Image(imageData);

                    // Create a page matching the image dimensions
                    PageSize ps = new PageSize(img.getImageWidth(), img.getImageHeight());
                    pdfDoc.addNewPage(ps);
                    
                    // Add image to the current page (coordinates 0,0)
                    // The 'pageCount' here refers to the PDF page we are adding to
                    img.setFixedPosition(pageCount, 0, 0);
                    
                    // Use a temporary layout document to add the image element
                    Document layoutDoc = new Document(pdfDoc, ps);
                    layoutDoc.add(img);
                    
                    pageCount++;
                } catch (Exception e) {
                    // iText throws an error when you try to access a page that doesn't exist
                    process = false; 
                }
            }

            pdfDoc.close();
            System.out.println("Converted " + (pageCount - 1) + " pages.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}