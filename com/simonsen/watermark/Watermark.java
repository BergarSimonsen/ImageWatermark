package com.simonsen.watermark;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.Color;
import com.simonsen.watermark.io.*;
import com.simonsen.watermark.controller.*;
import com.simonsen.watermark.model.*;
import com.simonsen.watermark.log.*;

public class Watermark {
    private static IO io;
    private static ImageController ic;
    private static File pwd;
    private static int scale;

    private static File[] files;
    private static CustomImage[] images;
    private static CustomImage[] watermarkedImages_white;

    public static void main(String args[]) {
	try {

	    Logger.logInfo("Starting main");

	    pwd = new File( "" ).getCanonicalFile();
	    ic = new ImageController(  );
	    io = new IO( pwd, ic );

	    files = io.readFiles();
	    if( files != null ) {
		images = ic.readImages( files );
		if( images != null ) {
		    watermarkedImages_white = ic.watermarkImages( images, Color.BLACK );
		    if( watermarkedImages_white != null ) {
			io.writeImages( watermarkedImages_white, "result", "_watermarked" );
		    }
		}
	    }
	} catch ( IOException e ) {
	    e.printStackTrace();
	    System.err.println(e);
	}
    }

    public static void writeImage( BufferedImage img ) throws IOException {
	File out = new File( "image_watermark.png" );
	ImageIO.write( img, "png", out );
    }
    
}
