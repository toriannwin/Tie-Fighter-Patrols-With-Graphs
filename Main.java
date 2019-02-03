/**
 * Tori Windrich
 * 4/17/2018
 * Project 5: Tie Fighter Patrols With Graphs
 */
package TieFighter;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main 
{
    public static void main(String[] args) 
    {
        //first input file
        File input = new File("galaxy.txt");
        
        //try catch in case the file doesn't exist
        try
        {
            int numLines = countLines(input); //count lines in the galaxy.txt file
            Graph g = new Graph(numLines); //create a new graph with the appropriate size
            File pilots = new File("pilot_routes.txt"); //open the second input file
            Scanner inputScanner = new Scanner(pilots); //create a scanner for pilot_routes.txt
            Pilot [] arr = new Pilot[countLines(pilots)]; //create an array of pilots by counting the lines of pilot_routes.txt
            
            //fill the pilot array with empty pilots
            for(int i = 0; i < arr.length; i++)
            {
                arr[i] = new Pilot();
            }
            
            Scanner galaxyScanner = new Scanner(input); //create a scanenr for galaxy.txt
            fillGraph(g,galaxyScanner); //fill the graph
            String line, name;
            int index = 0;
            
            //while there are still pilots to go through
            while(inputScanner.hasNext())
            {
                line = inputScanner.nextLine(); //get the next line of the file
                name = getName(line); //get the name from the line
                line = line.substring(name.length()); //cut off the name from the line
                arr[index].setName(name); //set the name in the current position of the array
                arr[index].setPathLength(validateRoute(g,line)); //set the path length to the value returned by the validateRoute method
                index++; //increment index
            }
            
            //sorts the pilots in the array by the path length (ascending order)
            sortPilots(arr);
            File out = new File("patrols.txt"); //output file
            PrintWriter output = new PrintWriter(out); //create a PrintWriter for the output file
            
            //print out the header for the table, then call the print function
            output.printf("%-26s%-26s%-26s\r\n", "Pilot Name", "Path Length", "Valid Path?");
            printPilots(arr,output);
            
            //close the files
            output.close();
            inputScanner.close();
            galaxyScanner.close();
            
        }
        //catches exception if the file can't be opened
        catch (FileNotFoundException e)
        {
            System.out.println("A file could not be opened: " + e.getLocalizedMessage());
        }
    }
    
    /**
     * Parses the passed in string to return the name from the string.
     * 
     * @param str the line from the file
     * @return the name
     */
    public static String getName(String str)
    {
        //split the string by spaces
        String [] splitString = str.split(" ");
        int num = 0;
        
        //going through the whole array of string
        for(int i = 0; i < splitString.length; i++)
        {
            //if the string is not a number, catches exception and continues through loop
            try
            {
                num = Integer.parseInt(splitString[i]);
                num = i;
                //if the string can be cast to a number, break out of the loop
                break;
            }
            catch(NumberFormatException e) {}
        }
        String name = "";
        
        //builds the name string with all of the strings that did not cast to an int
        for(int j = 0; j < num; j++)
        {
            name += splitString[j] + " ";
        }
        return name;
    }
    
    /**
     * Sorts the pilots in ascending order (by the path length) using a
     * selection sort.
     * 
     * @param arr the array of pilots
     */
    public static void sortPilots(Pilot [] arr)
    {
        Pilot temp;
        int minimumIndex;
        
        //going through the array of pilots
        for (int i = 0; i < arr.length - 1; i++) 
        {
            //set the current minimum
            minimumIndex = i;
            //going through the rest of the array of pilots
            for (int j = i + 1; j < arr.length; j++) 
            {
                //if the current position is less than the min index, overwrite min index
                if (arr[j].compareTo(arr[minimumIndex]) < 0) 
                {
                    minimumIndex = j;
                }
            }
            //swap the minimum value with the current value
            temp = arr[minimumIndex];
            arr[minimumIndex] = arr[i];
            arr[i] = temp;
        }
    }
    
    /**
     * Prints the pilots in the correct format to match the table output.
     * 
     * @param arr the pilot array
     * @param output the output print writer
     */
    public static void printPilots(Pilot [] arr, PrintWriter output)
    {
        //going through the array
        for (Pilot arr1 : arr) 
        {
            //print the name, then the path length and valid or no path length and invalid
            output.printf("%-26s", arr1.getName());
            if (arr1.getPathLength() == -1) 
            {
                output.printf("%-26s", "");
                output.printf("%-26s", "invalid");
            } 
            else 
            {
                output.printf("%-26d", arr1.getPathLength());
                output.printf("%-26s", "valid");
            }
            output.println();
        }
    }
    
    /**
     * Counts the lines in the passed in file.
     * 
     * @param f file to be check
     * @return number of lines in the file that aren't empty
     * @throws FileNotFoundException 
     */
    public static int countLines(File f) throws FileNotFoundException
    {
        Scanner input = new Scanner(f);
        int lines = 0;
        
        //while the file has more lines, if the line isn't empty, increment number of lines
        while(input.hasNext())
        {
            if(!input.nextLine().equals(""))
                lines++;  
        }
        return lines;
    }
    
    /**
     * Fills the graph with the information from the passed in Scanner.
     * 
     * @param g the empty graph
     * @param input the input scanner
     */
    public static void fillGraph(Graph g, Scanner input)
    {
        String line;
        String [] edges;
        int vertex, edge, weight;
        
        //while there are still lines in the input scanner
        while(input.hasNext())
        {
            line = input.nextLine(); //gets the next line in the file
            vertex = Integer.parseInt(line.substring(0, line.indexOf(" "))); //parses out the start vertex number
            line = line.substring(line.indexOf(" ")); //cuts off the start vertex number
            //inserts the vertex number in the graph
            g.insertVertex(vertex);
            //splits the rest of the string into edges
            edges = line.split(" ");
            
            //for each edge
            for (String cur : edges)
            {
                //if the edge isn't an empty string
                if(!cur.equals(""))
                {
                    //parse out the edge and the weight and insert the edge into the graph
                    edge = Integer.parseInt(cur.substring(0, cur.indexOf(",")));
                    weight = Integer.parseInt(cur.substring(cur.indexOf(",") + 1));
                    g.insertEdge(vertex, edge, weight);
                }
            }
        }
    }
    
    /**
     * Validates the pilot's route and returns the path length or -1 if it's an invalid path.
     * 
     * @param g the graph being traversed
     * @param path the string with the path
     * @return the pathLength or -1
     */
    public static int validateRoute(Graph g, String path)
    {
        //split the path into individual vertices
        String [] pathVertices = path.split(" ");
        int pathLength = 0;
        
        //for each vertex
        for(int i = 0; i < pathVertices.length - 1; i++)
        {
            //if the doesn't have a connection from start to end, return -1, otherwise, call getWeight and add to path length
            if(!g.hasConnection(Integer.parseInt(pathVertices[i]), Integer.parseInt(pathVertices[i+1])))
                return -1;
            else
                pathLength += g.getWeight(Integer.parseInt(pathVertices[i]), Integer.parseInt(pathVertices[i+1]));
        }
        return pathLength;
    }
}
