class Grader
{
    private final String testName;
    private final int max;
    private int marks;

    public Grader(final String name, 
                  final int m)
    {
        testName = name;
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
    
    public String getTestName()
    {
        return (testName);
    }
}
