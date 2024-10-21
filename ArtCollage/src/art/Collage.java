package art;

import java.awt.Color;

/*
 * This class contains methods to create and perform operations on a collage of images.
 * 
 * @author Ana Paula Centeno
 */ 

public class Collage {

    // The orginal picture
    private Picture originalPicture;

    // The collage picture is made up of tiles.
    // Each tile consists of tileDimension X tileDimension pixels
    // The collage picture has collageDimension X collageDimension tiles
    private Picture collagePicture;

    // The collagePicture is made up of collageDimension X collageDimension tiles
    // Imagine a collagePicture as a 2D array of tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    // Imagine a tile as a 2D array of pixels
    // A pixel has three components (red, green, and blue) that define the color 
    // of the pixel on the screen.
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 150
     * 2. initializes originalPicture with the filename image
     * 3. initializes collagePicture as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see constructors for the Picture class).
     * 4. update collagePicture to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public Collage (String filename) {

        // WRITE YOUR CODE HERE
        this.collageDimension = 4;
        this.tileDimension = 150;

        originalPicture = new Picture(filename);

        int coltile = collageDimension * tileDimension;
        collagePicture = new Picture(coltile, coltile);

        scale(originalPicture, collagePicture);
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes originalPicture with the filename image
     * 3. initializes collagePicture as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collagePicture to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */    
    public Collage (String filename, int td, int cd) {

        // WRITE YOUR CODE HERE
        this.collageDimension = cd;
        this.tileDimension = td;

        originalPicture = new Picture(filename);

        int coltile = collageDimension * tileDimension;
        collagePicture = new Picture(coltile, coltile);

        scale(originalPicture, collagePicture);
    }


    /*
     * Scales the Picture @source into Picture @target size.
     * In another words it changes the size of @source to make it fit into
     * @target. Do not update @source. 
     *  
     * @param source is the image to be scaled.
     * @param target is the 
     */
    public static void scale (Picture source, Picture target) {

        // WRITE YOUR CODE HERE
        // tc = target column; tr = target row; twid = target width; thei = target height; sc = source column; sr = source row; clr = color
        int twid = target.width();
        int thei = target.height();

        for(int tc = 0; tc < twid; tc++){
            for(int tr = 0; tr < thei; tr++){
                int sc = tc * source.width() / twid;
                int sr = tr * source.height() / thei;
                Color clr = source.get(sc, sr);
                target.set(tc, tr, clr);
            }
        }
    }

     /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */   
    public int getCollageDimension() {
        return collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */    
    public int getTileDimension() {
        return tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    
    public Picture getOriginalPicture() {
        return originalPicture;
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    
    public Picture getCollagePicture() {
        return collagePicture;
    }

    /*
     * Display the original image
     * Assumes that original has been initialized
     */    
    public void showOriginalPicture() {
        originalPicture.show();
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */    
    public void showCollagePicture() {
	    collagePicture.show();
    }

    /*
     * Updates collagePicture to be a collage of tiles from original Picture.
     * collagePicture will have collageDimension x collageDimension tiles, 
     * where each tile has tileDimension X tileDimension pixels.
     */    

    // Helper Method Copy
    public static void copy(int row, int col, Picture source, Picture target) {
        for (int i = 0; i < source.height(); i++) {
            for (int j = 0; j < source.height(); j++) {
                Color clr = source.get(i, j);
                target.set(i+col, j+row, clr);
            }
        }   
    }

    public void makeCollage () {

        // WRITE YOUR CODE HERE
        Picture tile = new Picture(tileDimension, tileDimension);
        scale(originalPicture, tile);

        for (int i = 0; i < collagePicture.height()/tileDimension; i++) {
            for (int j = 0; j < collagePicture.height()/tileDimension; j++) {
                int tilerow = i * tileDimension;
                int tilecol = j * tileDimension;
                copy(tilerow, tilecol, tile, collagePicture);
            }
        }
    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
        for(int i = 0; i < collagePicture.height()/tileDimension; i++){
            for(int j = 0; j < collagePicture.height()/tileDimension; j++){
                if (i == collageRow && j == collageCol){
                    for(int pixelR = 0; pixelR < tileDimension; pixelR++){
                        for(int pixelC = 0; pixelC < tileDimension; pixelC++){
                            int tileCol = j * tileDimension;
                            int tileRow = i * tileDimension;
                            Color clr = collagePicture.get(pixelC + tileCol, pixelR + tileRow);
                            int r = clr.getRed();
                            int g = clr.getGreen();
                            int b = clr.getBlue();

                            if(component.equals("red")){
                                collagePicture.set(pixelC + tileCol, pixelR + tileRow, new Color(r, 0, 0));
                            }
                            else if(component.equals("green")){
                                collagePicture.set(pixelC + tileCol, pixelR + tileRow, new Color(0, g, 0));
                            }
                            else if(component.equals("blue")){
                                collagePicture.set(pixelC + tileCol, pixelR + tileRow, new Color(0, 0, b));
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
        Picture replace = new Picture(filename);
        Picture scaledReplace = new Picture(tileDimension, tileDimension);
        scale(replace, scaledReplace);

        for(int i = 0; i < collagePicture.height()/tileDimension; i++){
            for(int j = 0; j < collagePicture.height()/tileDimension; j++){
                if(i == collageRow && j == collageCol){
                    int tileCol = j * tileDimension;
                    int tileRow = i * tileDimension;
                    copy (tileRow, tileCol, scaledReplace, collagePicture);
                }
            }
        }
    }

    /*
     * Grayscale tile at (collageCol, collageRow)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void grayscaleTile (int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
        for(int i = 0; i < collagePicture.height()/tileDimension; i++){
            for(int j = 0; j < collagePicture.height()/tileDimension; j++){
                if(i == collageRow && j == collageCol){
                    for(int pixelR = 0; pixelR < tileDimension; pixelR++){
                        for(int pixelC = 0; pixelC < tileDimension; pixelC++){
                            int tileCol = j * tileDimension;
                            int tileRow = i * tileDimension;
                            Color clr = collagePicture.get(pixelC + tileCol, pixelR + tileRow);
                            Color gray = toGray(clr);
                            collagePicture.set(pixelC + tileCol, pixelR + tileRow, gray);
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the monochrome luminance of the given color as an intensity
     * between 0.0 and 255.0 using the NTSC formula
     * Y = 0.299*r + 0.587*g + 0.114*b. If the given color is a shade of gray
     * (r = g = b), this method is guaranteed to return the exact grayscale
     * value (an integer with no floating-point roundoff error).
     *
     * @param color the color to convert
     * @return the monochrome luminance (between 0.0 and 255.0)
     */
    private static double intensity(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        if (r == g && r == b) return r;   // to avoid floating-point issues
        return 0.299*r + 0.587*g + 0.114*b;
    }

    /**
     * Returns a grayscale version of the given color as a {@code Color} object.
     *
     * @param color the {@code Color} object to convert to grayscale
     * @return a grayscale version of {@code color}
     */
    private static Color toGray(Color color) {
        int y = (int) (Math.round(intensity(color)));   // round to nearest int
        Color gray = new Color(y, y, y);
        return gray;
    }

    /*
     * Closes the image windows
     */
    public void closeWindow () {
        if ( originalPicture != null ) {
            originalPicture.closeWindow();
        }
        if ( collagePicture != null ) {
            collagePicture.closeWindow();
        }
    }
}
