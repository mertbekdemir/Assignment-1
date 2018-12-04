import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.ComparisonFailure;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Properties;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class HouseTest
{
    private static Grader grader = new Grader(47);
    
    @AfterClass
    public static void grade()
    {
        System.out.println("Score: " + grader.getMarks() + " / " + grader.getMax());
    }    
        
    @Test
    public void testHouseFields() 
    {     
        testField(House.class,  "type",    String.class,  new String[] {"private", "final",}, new String[] {});
        testField(House.class,  "year",    int.class,  new String[] {"private", }, new String[] {"static", "final", });
        testField(House.class,  "size",    double.class,  new String[] {"private", }, new String[] {"static", "final", });
        testField(House.class,  "neighborhood",   String.class,  new String[] {"private", }, new String[] {"static", "final", });
        testField(House.class,  "numBedrooms",    int.class,  new String[] {"private", }, new String[] {"static", "final", });
        testField(House.class,  "numBathrooms",   double.class,  new String[] {"private", }, new String[] {"static", "final", });
        testField(House.class,  "view",           boolean.class,  new String[] {"private", }, new String[] {"static", "final", });
        testField(House.class,  "pictureGallery", String.class,  new String[] {"private", }, new String[] {"static", "final", });
        grader.addMark(8);
    }
    
    @Test
    public void testHouseConstructor()
    {
        new House(1950, 3500.0, "West Vancouver", 4, 3.0, false, "http://www.house.com/pictures");
        new House(2018, 5567.4, "Burnaby Mountain", 10, 5.5, true, "http://www.house.com/pictures/1");
        new House(1850, 2500.0, "Burnaby Mountain", 3, 2.5, true, "some string");
        grader.addMark(7);
    }

    @Test
    public void testHouseMutatorMethods() 
    {   
        testMethod(House.class, "setYear",           void.class,   new String[] {"public"},    new String[] {"static", "final"}, int.class);
        testMethod(House.class, "setSize",           void.class,   new String[] {"public"},    new String[] {"static", "final"}, double.class);
        testMethod(House.class, "setNeighborhood",   void.class,   new String[] {"public"},    new String[] {"static", "final"}, String.class);
        testMethod(House.class, "setNumBedrooms",    void.class,   new String[] {"public"},    new String[] {"static", "final"}, int.class);
        testMethod(House.class, "setNumBathrooms",   void.class,   new String[] {"public"},    new String[] {"static", "final"}, double.class);
        testMethod(House.class, "setView",           void.class,   new String[] {"public"},    new String[] {"static", "final"}, boolean.class);
        testMethod(House.class, "setPictureGallery", void.class,   new String[] {"public"},    new String[] {"static", "final"}, String.class);
        
        grader.addMark(7);
    }   
    
    @Test
    public void testHouseAccessorMethods()
    {
        testMethod(House.class, "getYear",           int.class,     new String[] {"public"},    new String[] {"static", "final"});
        testMethod(House.class, "getSize",           double.class,  new String[] {"public"},    new String[] {"static", "final"});
        testMethod(House.class, "getNeighborhood",   String.class,  new String[] {"public"},    new String[] {"static", "final"});
        testMethod(House.class, "getNumBedrooms",    int.class,     new String[] {"public"},    new String[] {"static", "final"});
        testMethod(House.class, "getNumBathrooms",   double.class,  new String[] {"public"},    new String[] {"static", "final"});
        testMethod(House.class, "hasView",           boolean.class, new String[] {"public"},    new String[] {"static", "final"});
        testMethod(House.class, "getPictureGallery", String.class,  new String[] {"public"},    new String[] {"static", "final"});
        grader.addMark(7);
    }
    
    @Test
    public void testHouseConstructorYearRules()
    {
        try
        {
            new House(1849, 2500.0, "Burnaby Mountain", 3, 2.5, true, "some string");
            fail("Too old year must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, the year 1849 is too old for a mansion.", ex.getMessage());
            grader.addMark(1);
        }
        
        try
        {
            new House(2019, 2500.0, "Burnaby Mountain", 3, 2.5, true, "some string");
            fail("Future year must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, the year 2019 is invalid.", ex.getMessage());
            grader.addMark(1);
        }    
    }
    
    @Test
    public void testHouseConstructorSizeRules()
    {
        try
        {
            new House(1950, 2499.9, "Burnaby Mountain", 3, 2.5, true, "some string");
            fail("Too small size must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, 2499.9 square feet is too small for a mansion.", ex.getMessage());
            grader.addMark(1);
        }    
    }  
    
    @Test
    public void testHouseConstructorNeighborhoodRules()
    {
        try
        {
            new House(1950, 2500.9, null, 3, 2.5, true, "some string");
            fail("null neighborhood must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("The Neighborhood is not valid.", ex.getMessage());
            grader.addMark(1);
        }
        
        try
        {
            new House(1950, 2500.9, "", 3, 2.5, true, "some string");
            fail("empty neighborhood must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, the Neighborhood must be set.", ex.getMessage());
            grader.addMark(1);
        }
    } 
    
    @Test
    public void testHouseConstructorNumBedroomsRules()
    {
        try
        {
            new House(1950, 2500.0, "Burnaby Mountain", 2, 2.5, true, "some string");
            fail("Too few bedrooms must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, a mansion should have at least 3 bedrooms.", ex.getMessage());
            grader.addMark(1);
        }    
    } 
    
    @Test
    public void testHouseConstructorNumBathroomsRules()
    {
        try
        {
            new House(1950, 2500.0, "Burnaby Mountain", 3, 2.25, true, "some string");
            fail("Too few bathrooms must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, a mansion should have at least 2.5 bathrooms.", ex.getMessage());
            grader.addMark(1);
        }     
    }
    
    @Test
    public void testHouseConstructorPictureGalleryRules()
    {
        try
        {
            new House(1950, 2500.9, "Burnaby Mountain", 3, 2.5, true, null);
            fail("null picture gallery must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("The Picture Gallery is not valid.", ex.getMessage());
            grader.addMark(1);
        }
        
        try
        {
            new House(1950, 2500.9, "Burnaby Mountain", 3, 2.5, true, "");
            fail("empty picture gallery must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, the Picture Gallery must be set.", ex.getMessage());
            grader.addMark(1);
        }    
    }
    
    @Test
    public void testHouseMutatorYearRules()
    {
        try
        {
            House testHouse = new House(1950, 2500.0, "Burnaby Mountain", 3, 2.5, true, "some string");
            testHouse.setYear(1849);
            fail("Too old year must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, the year 1849 is too old for a mansion.", ex.getMessage());
            grader.addMark(1);
        }
        
        try
        {
            House testHouse = new House(1950, 2500.0, "Burnaby Mountain", 3, 2.5, true, "some string");
            testHouse.setYear(2019);
            fail("Future year must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, the year 2019 is invalid.", ex.getMessage());
            grader.addMark(1);
        }       
    }
    
    @Test
    public void testHouseMutatorSizeRules()
    {
        try
        {
            House testHouse = new House(1950, 2500.0, "Burnaby Mountain", 3, 2.5, true, "some string");
            testHouse.setSize(2499.9);
            fail("Too small size must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, 2499.9 square feet is too small for a mansion.", ex.getMessage());
            grader.addMark(1);
        }    
    } 
    
    @Test
    public void testNeighborhoodMutatorRules()
    {
        try
        {
            House testHouse = new House(1950, 2500.9, "Burnaby Mountain", 3, 2.5, true, "some string");
            testHouse.setNeighborhood(null);
            fail("null neighborhood must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("The Neighborhood is not valid.", ex.getMessage());
            grader.addMark(1);
        }
        
        try
        {
            House testHouse = new House(1950, 2500.9, "Burnaby Mountain", 3, 2.5, true, "some string");
            testHouse.setNeighborhood("");
            fail("empty neighborhood must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, the Neighborhood must be set.", ex.getMessage());
            grader.addMark(1);
        }
    } 
    
    @Test
    public void testNumBedroomsMutatorRules()
    {
        try
        {
            House testHouse = new House(1950, 2500.0, "Burnaby Mountain", 3, 2.5, true, "some string");
            testHouse.setNumBedrooms(2);
            fail("Too few bedrooms must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, a mansion should have at least 3 bedrooms.", ex.getMessage());
            grader.addMark(1);
        }
    }
    
    @Test
    public void testNumBathroomsMutatorRules()
    {
        try
        {
            House testHouse = new House(1950, 2500.0, "Burnaby Mountain", 3, 2.5, true, "some string");
            testHouse.setNumBathrooms(2.25);
            fail("Too few bathrooms must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, a mansion should have at least 2.5 bathrooms.", ex.getMessage());
            grader.addMark(1);
        }   
    }
    
    @Test
    public void testPictureGalleryMutatorRules()
    {
        try
        {
            House testHouse = new House(1950, 2500.9, "Burnaby Mountain", 3, 2.5, true, "some value");
            testHouse.setPictureGallery(null);
            fail("null picture gallery must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("The Picture Gallery is not valid.", ex.getMessage());
            grader.addMark(1);
        }
        
        try
        {
            House testHouse = new House(1950, 2500.9, "Burnaby Mountain", 3, 2.5, true, "some value");
            testHouse.setPictureGallery("");
            fail("empty picture gallery must throw an IllegalArgumentException"); 
        }
        catch(final IllegalArgumentException ex)
        {
            assertEquals("Sorry, the Picture Gallery must be set.", ex.getMessage());
            grader.addMark(1);
        }
    }
    
    @Test
    public void testHouseDetails() 
    {
        /* With View */
        House testHouse1 = new House(2015, 4000.0, "West Vancouver", 3, 2.5, true, "https://www.house.com/specs/1");
        assertEquals(
            "This mansion was built in 2015 with 4000.0 square feet of space, 3 bedrooms and 2.5 bathrooms. It has a spectacular view. You can see pictures of this house at https://www.house.com/specs/1.",
            testHouse1.getHouseDetails());
        
        /* Without View */
        House testHouse2 = new House(1990, 4817.5, "Point Grey", 7, 5.5, false, "https://www.house.com/specs/2");
        assertEquals(
            "This mansion was built in 1990 with 4817.5 square feet of space, 7 bedrooms and 5.5 bathrooms. It is situated in a fantastic location. You can see pictures of this house at https://www.house.com/specs/2.",
            testHouse2.getHouseDetails());
    }
    
    private void testField(final Class<?> clazz, 
                           final String   fieldName,
                           final Class    expectedType,
                           final String[] expectedModifiers,
                           final String[] forbiddenModifiers)
    {
        if(clazz == null)
        {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        
        if(fieldName == null)
        {
            throw new IllegalArgumentException("fieldName cannot be null");
        }
        
        try
        {
            final Field    field;
            final int      modifiers;
            final Class<?> type;
            
            field = clazz.getDeclaredField(fieldName);
            type  = field.getType();
            
            if(!(type.equals(expectedType)))
            {
                fail(clazz.getName() + "." + fieldName + " must be declared as \"" + expectedType.getName() + "\"");
            }
            
            modifiers = field.getModifiers();
            checkRequiredModifiers(clazz, fieldName, expectedModifiers, modifiers);
            checkForbiddenModifiers(clazz, fieldName, forbiddenModifiers, modifiers);
        }
        catch(final NoSuchFieldException ex)
        {
            fail(clazz.getName() + " must have a field named: \"" + fieldName + "\"");
        }
    }

    private void testMethod(final Class<?>    clazz, 
                            final String      methodName,
                            final Class       expectedReturnType,                           
                            final String[]    expectedModifiers,
                            final String[]    forbiddenModifiers,
                            final Class<?>... expectedParameters)
   {
        if(clazz == null)
        {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        
        if(methodName == null)
        {
            throw new IllegalArgumentException("methodName cannot be null");
        }
        
        try
        {
            final Method   method;
            final int      modifiers;
            final Class<?> returnType;
            
            method     = clazz.getDeclaredMethod(methodName, expectedParameters);
            returnType = method.getReturnType();
            
            if(!(returnType.equals(expectedReturnType)))
            {
                fail(clazz.getName() + "." + methodName + " must be return \"" + expectedReturnType.getName() + "\"");
            }
            
            modifiers = method.getModifiers();
            checkRequiredModifiers(clazz, methodName, expectedModifiers, modifiers);
            checkForbiddenModifiers(clazz, methodName, forbiddenModifiers, modifiers);
        }
        catch(final NoSuchMethodException ex)
        {
            fail(clazz.getName() + " must have a field named: \"" + methodName + "\"");
        }
   }

    private void checkRequiredModifiers(final Class<?> clazz,
                                        final String   name,
                                        final String[] expectedModifiers, 
                                        final int      actualModifiers)
    {
        for(final String expected : expectedModifiers)
        {
            switch(expected)
            {
                case "public":
                {
                    if(!(Modifier.isPublic(actualModifiers)))
                    {
                        fail(clazz.getName() + "." + name + " must be declared \"public\"");
                    }
                    break;
                }        
                case "private":
                {
                    if(!(Modifier.isPrivate(actualModifiers)))
                    {
                        fail(clazz.getName() + "." + name + " must be declared \"private\"");
                    }
                    break;
                }        
                case "protected":
                {
                    if(!(Modifier.isProtected(actualModifiers)))
                    {
                        fail(clazz.getName() + "." + name + " must be declared \"protected\"");
                    }
                    break;
                }        
                case "final":
                {
                    if(!(Modifier.isFinal(actualModifiers)))
                    {
                        fail(clazz.getName() + "." + name + " must be declared \"final\"");
                    }
                    break;
                }        
                case "static":
                {
                    if(!(Modifier.isStatic(actualModifiers)))
                    {
                        fail(clazz.getName() + "." + name + " must be declared \"static\"");
                    }
                    break;
                }        
            }
        }
    }
    
    private void checkForbiddenModifiers(final Class<?> clazz,
                                         final String   name,
                                         final String[] unexpectedModifiers, 
                                         final int      actualModifiers)
    {
        for(final String unexpected : unexpectedModifiers)
        {
            switch(unexpected)
            {
                case "public":
                {
                    if(Modifier.isPublic(actualModifiers))
                    {
                        fail(clazz.getName() + "." + name + " must not be declared \"public\"");
                    }
                    break;
                }        
                case "private":
                {
                    if(Modifier.isPrivate(actualModifiers))
                    {
                        fail(clazz.getName() + "." + name + " must not be declared \"private\"");
                    }
                    break;
                }        
                case "protected":
                {
                    if(Modifier.isProtected(actualModifiers))
                    {
                        fail(clazz.getName() + "." + name + " must not be declared \"protected\"");
                    }
                    break;
                }        
                case "final":
                {
                    if(Modifier.isFinal(actualModifiers))
                    {
                        fail(clazz.getName() + "." + name + " must not be declared \"final\"");
                    }
                    break;
                }        
                case "static":
                {
                    if(Modifier.isStatic(actualModifiers))
                    {
                        fail(clazz.getName() + "." + name + " must not be declared \"static\"");
                    }
                    break;
                }        
            }
        }
    }
}

class Grader
{
    private final int max;
    private int marks;
    
    public Grader(final int m)
    {
        max = m;
    }
    
    public void addMark(final int mark)
    {
        marks += mark;
    }
    
    public int getMarks()
    {
        return marks;
    }
    
    public int getMax()
    {
        return max;
    }
}