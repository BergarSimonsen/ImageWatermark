package com.simonsen.watermark.io;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.util.*;
import com.simonsen.watermark.controller.*;
import com.simonsen.watermark.model.*;


public class IO {

    private String RES = "_watermarked";
    private File pwd;
    private ImageController ic;
    private String RES_FOLDER = "\\result\\";
    

    public IO( File pwd, ImageController ic ) {
	this.pwd = pwd;
	this.ic = ic;
    }

    public void writeImages( CustomImage[] ci, String folderName, String extension ) throws IOException {
	if( folderName.length() > 0 ) this.RES_FOLDER = "\\" + folderName + "\\";
	if( extension.length() > 0 ) this.RES = extension;
	try {
	    File folder = new File( pwd.getPath() + RES_FOLDER );
	    if( !folder.exists() ) {
		folder.mkdir();
	    }
	} catch ( SecurityException se ) {
	    System.out.println( "not allowed to create directory!" );
	}

	for( int i = 0; i < ci.length; i++ ) {
	    File out = new File( pwd.getPath() + RES_FOLDER + ci[ i ].getName() + RES + "." + ci[ i ].getExtension() );
	    ImageIO.write( ci[ i ].getImage(), ci[ i ].getExtension(), out );
	}
    }

    public File[] readFiles(  ) {
	System.out.println( "read files" );
	ArrayList<File> retval = new ArrayList<File>();
	File[] tmp = pwd.listFiles();

	if( tmp == null || tmp.length == 0 ) {
	    System.out.println( "no files found in current folder!" );
	    return null;
	} else {
	    for( File f : tmp ) {
		String tmpFilename = f.getName();
		int index = tmpFilename.lastIndexOf( '.' );
		String ext = "";
		if( index > 0 ) ext = tmpFilename.substring( index + 1 );
		if( ic.isImage( ext ) ) {
		    retval.add( f );
		}
	    }
	}
	File[] ret = new File[ retval.size() ];
	for( int i = 0; i < retval.size(); i++ ) {
	    ret[i] = retval.get( i );
	}
	return ret;
    }
}
