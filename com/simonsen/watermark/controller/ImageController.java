package com.simonsen.watermark.controller;

import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import com.simonsen.watermark.model.*;


public class ImageController {

    public CustomImage[] watermarkImages( CustomImage[] b, Color color ) throws IOException {
	CustomImage[] retval = new CustomImage[ b.length ];
	for( int i = 0; i < b.length; i++ ) {
	    retval[ i ] = watermarkImage( b[ i ], color );
	}
	return retval;
    }

    private CustomImage watermarkImage( CustomImage ci, Color color ) throws IOException {
	Graphics2D g = ci.getImage().createGraphics();
	    
	int imgHeight = ci.getHeight();

	int curHeight = imgHeight - 50;
	int curWidth = 50;

	String copUnicode = "\\U00A9";
	char copChar = ( char ) Integer.parseInt( copUnicode.substring(2), 16 );

	g.setColor( color );
	
	// if(black) {
	//     System.out.println( "setting color to black!!!" );
	//     g.setColor( Color.BLACK );
	//     System.out.println( g.getColor().toString() );	    
	// }
	// else {
	//     System.out.println("setting color WHITE!!!" );
	//     g.setColor( Color.WHITE );
	//     System.out.println( g.getColor().toString() );
	// }

	g.setFont( new Font( "Arial Black", Font.BOLD, 40 ) );
	g.drawString( copChar + "Ann Kristina S. Simonsen", curWidth, curHeight );

	g.dispose();
	
	return ci;
    }

    public CustomImage[] readImages( File[] f ) throws IOException {
	CustomImage[] retval = new CustomImage[ f.length ];
	for( int i = 0; i < f.length; i++ ) {
	    retval[ i ] = createCustomImage( f[ i ] );
	}
	return retval;
    }

    public boolean isImage(String ex ) {
	if( ex.equalsIgnoreCase( "jpg" ) ||
	    ex.equalsIgnoreCase( "png" ) ||
	    ex.equalsIgnoreCase( "jpeg" ) ||
	    ex.equalsIgnoreCase( "bmp" ) ) {
	    return true;
	} else {
	    return false;
	}
    }
    public CustomImage createCustomImage( File f ) throws IOException {
	BufferedImage img = ImageIO.read( f );
	String fullName = f.getName();
	String filename = getFilename( fullName );
	String extension = getFileExtension( fullName );
	return new CustomImage( img, fullName, filename, extension );
    }

    public String getFileExtension( String filename ) {
	String retval = "";
	int i = filename.lastIndexOf( '.' );
	if( i > 0 ) retval = filename.substring( i + 1 );
	return retval;
    }

    public String getFilename( String fullName ) {
	String retval = "";
	int i = fullName.lastIndexOf( '.' );
	if( i > 0 ) retval = fullName.substring( 0, i - 1 );
	return retval;
    }
}
