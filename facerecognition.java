package com.ocr.ocrexample;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static java.lang.System.loadLibrary;
import static org.opencv.imgproc.Imgproc.rectangle;



class  DetectFaceDemo {

    public static void run(MultipartFile file) throws IOException {

        loadLibrary( Core.NATIVE_LIBRARY_NAME );
        CascadeClassifier faceDetector = new CascadeClassifier();
        faceDetector.load( "C:\\Users\\Desktop\\ocv\\.idea\\haarcascade_frontalface_alt.xml" );

        // Input image

        com.ocr.ocrexample.FileUploadController.convert(file);
                Mat image = Imgcodecs.imread(String.valueOf(file));
// Detecting faces
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale( image, faceDetections );

        // Creating a rectangular box showing faces detected
        for (Rect rect : faceDetections.toArray()) {
            rectangle( image, new Point( rect.x, rect.y ), new Point( rect.width + rect.x,
                    rect.height + rect.y ), new Scalar( 0, 255, 0 ) );
        }

        // Saving the output image
        String filename = "Ouput.jpg";
        System.out.println("Face Detected Successfully ");
        Imgcodecs.imwrite( "D:\\" + filename, image );


    }
    }
