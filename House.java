
/**
 * class House includes information for a house that is on sale
 *
 * @author Mert
 * @version 1409
 */
public class House
{
    //Constants

    private static final int MAX_YEAR = 2018;
    private static final int MIN_YEAR = 1850;
    private static final double MIN_SIZE = 2500.0;
    private static final int MIN_BEDROOM = 3;
    private static final double MIN_BATHROOM = 2.5;

    // Class  (static) variables
    public static String TYPE = "Mansion";
    private static String pictureGallery = "www.mansion.com/gallery ";

    //instance variables 
    private int year;
    private double sizeInSquareFeet;
    private String neighborhood;
    private int numberOfBedrooms;
    private double numberOfBathrooms;
    private boolean view;

    /**
     * Constructor for objects of class House
     * @param year this parameter indicates the year house was built
     * @param sizeInSquareFeet this parameter indicates the size of house in square feet
     * @param neighborhood this parameter indicates the neighborhood the house is located
     * @param numberOfBedrooms this parameters represents the number of bedrooms in the house
     * @param numBathrooms this parameter shows number of bathrooms in the house
     * @param view this parameter checks if the house has a view or not
     * @param pictureGallery this parameter contains the url of the house.
     */
    public House(int year,
    double sizeInSquareFeet,
    String neighborhood, 
    int numberOfBedrooms, 
    double numberOfBathrooms,
    boolean view,
    String pictureGallery)
    {
        setYear(year);
        setSize(sizeInSquareFeet);
        setNeighborhood(neighborhood);
        setNumBedrooms(numberOfBedrooms);
        setNumBathrooms(numberOfBathrooms);
        setView(view);
        setPictureGallery(pictureGallery);

    }
    /**
     * Mutator setYear sets the year built for the house
     * 
     * @param theYear is the year that house built in
     */
    public final void setYear(int theYear){
        if(theYear >= MIN_YEAR && theYear <= MAX_YEAR){
            year = theYear;
        }else{
            throw new IllegalArgumentException(" Sorry, the year " + theYear + " is invalid");
        }
    }

    /**
     * mutator setSize sets the size of the house in square feet
     * @param theSize is the size for the house in square feet
     */
    public final void setSize(double theSize){
        if(theSize >= MIN_SIZE){
            sizeInSquareFeet = theSize;
        }else{
            throw new IllegalArgumentException("Sorry, " + theSize + " square feet is too small for a mansion");
        }
    }

    /**
     * mutator setNeighborhood sets the neighborhood of the house
     * @param theNeighborhood is the neighborhood of the house
     */
    public final void setNeighborhood(String theNeighborhood){
        if(theNeighborhood != null ){
            neighborhood = theNeighborhood;
        }else{   
            throw new IllegalArgumentException("The neighborhood is not valid");
        }

        if(theNeighborhood.length() > 0){
            neighborhood = theNeighborhood;
        }else{
            throw new IllegalArgumentException("Sorry, the neighborhood must be set");
        }
    }

    /**
     * mutator setNumBedrooms sets the number of bedrooms in the house
     * @param bedroomNumbers is the number of bedrooms in the house
     */
    public final void setNumBedrooms(int bedroomNumbers){
        if(bedroomNumbers >= MIN_BEDROOM){
            numberOfBedrooms = bedroomNumbers;
        }else{
            throw new IllegalArgumentException("Sorry, a mansion should have at least 3 bedrooms");
        }
    }

    /**
     * mutator setNumBathrooms sets the value for the bathroom numbers in the house
     * @param bathroomNumbers is the value for bathroom numbers in the house
     */
    public final void setNumBathrooms(double bathroomNumbers){
        if(bathroomNumbers >= MIN_BATHROOM){
            numberOfBathrooms = bathroomNumbers;
        }else{
            throw new IllegalArgumentException("Sorry, a mansion should have at least 2.5 bathrooms.");
        }
    }

    /**
     * mutator setView sets if the house has a view or not
     * @param theView checks if the house has a view or not
     */
    public final void setView(boolean theView){
        view = theView;
    }

    /**
     * mutator setPictureGallery sets the picture gallery rules
     * @param thePictureGallery are the picture gallery rules
     */
    public final void setPictureGallery(String thePictureGallery){
        
        if(thePictureGallery == null){
            
           throw new IllegalArgumentException("Sorry, the picture gallery is not valid.");
        }
       else if(thePictureGallery.length() <= 0){
            
            throw new IllegalArgumentException("Sorry, the picture gallery must be set.");
        }
        
        else{
        
        pictureGallery = thePictureGallery;
    }
    }
    //Accessor methos
    /**
     * @ return getYear returns the year house built in
     */
    public int getYear(){
        return year;
    }

    /**
     * @return getSize returns the size of house in square feet
     */
    public double getSize(){
        return sizeInSquareFeet;
    }

    /**
     * @return getNeighborhood returns the neighborhood of the house
     */
    public String getNeighborhood(){
        return neighborhood;
    }

    /**
     * @return getNumBedrooms returns number of bedrooms in the house
     */
    public int getNumBedrooms(){
        return numberOfBedrooms;
    }

    /**
     * @return getNumBathrooms returns the number of bathrooms in the house
     */
    public double getNumBathrooms(){
        return numberOfBedrooms;
    }

    /**
     * @return hasView returns if house has a view or not
     */
    public boolean hasView(){
        return view;
    }

    /**
     * @return getPictureGallery returns picture gallery
     */
    public String getPictureGallery(){
        return pictureGallery;
    }

    public String getHouseDetails(){
        String display = "";
        display = "This " + TYPE + " was built in "+ getYear() + " with " + getSize() + " square feet of space, " + getNumBedrooms() + " and " + getNumBathrooms() + 
            " bathrooms. It has a spectacular View. You can see pictures of this house at " + getPictureGallery();

        return display;
    }
}

