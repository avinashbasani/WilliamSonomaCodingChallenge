package com.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Avinash Reddy
 * This class takes arguments of Zip Code ranges and
 * simplifies the given ranges, removing any duplicate occurances
 * either directly with the given range or indirectly from with in the range
 */
public class ZipCodeValidator {
    private static final String[] sampleArgs1 = {"[94133,94133]","[94200,94299]","[94226,94399]"};
    private static final String[] sampleArgs2 = {"[49679,52015]","[49800,50000]","[51500,53479]", "[45012,46937]","[54012,59607]"
            ,"[45500,45590]","[45999,47900]", "[44000,45000]","[43012,45950]"};
    public static void main(String[] args) {

        ZipCodeValidator zipCodeValidator = new ZipCodeValidator();
        if(args.length == 0) {
            /**
             * Sample Execution to show the validity of the code
             */
            String output1 = zipCodeValidator.zipCodeManager(sampleArgs1);
            System.out.println("Output from Sample 1: " + output1);
            String output2 = zipCodeValidator.zipCodeManager(sampleArgs2);
            System.out.println("Output from Sample 2: " + output2);
            System.out.println("\n\n" + " Run the program by building the jar and executing the jar with arguments "
                    +"\nEg: " + "'java -jar ZipCodeManager-1.0-SNAPSHOT.jar \"[94133,94133]\" \"[94200,94299]\" \"[94226,94399]\"'");
        }else{
            /**
             * Actual Execution when passed through Args
             */
            String output = zipCodeValidator.zipCodeManager(args);
            System.out.println("Output with Args provided : " + output);
        }
    }

    /**
     *
     * @param args
     * @return String of simplified range
     */
    public String zipCodeManager(String[] args){
        List<ZipRange> providedZipCodes = generateZipRangeList(args);

        //Sorting using Java 8 LambdaFunction with Comparator
        providedZipCodes.sort((ZipRange zip1, ZipRange zip2)-> zip1.getLow()-zip2.getLow());

        //Remove duplicate occurances
        simplifyRange(providedZipCodes);

        //Generate output String
        return generateOutputList(providedZipCodes);
    }

    /**
     *
     * @param array
     * @return List - ArrayList of {@link ZipRange}
     * return a list of Zip Range from given array. this method also checks
     * lower and higher values within the given pair
     */
    public List generateZipRangeList(String[] array) {
        List<ZipRange> providedZipCodes = new ArrayList<>();
        Arrays.stream(array).forEach(s->{
            String[] temp = s.replace("[", "").replace("]", "").split(",");
            ZipRange zipRange = null;
            if(Integer.parseInt(temp[0]) >= Integer.parseInt(temp[1])){
                zipRange = new ZipRange(Integer.parseInt(temp[1]), Integer.parseInt(temp[0]));
            }else{
                zipRange = new ZipRange(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            }
            providedZipCodes.add(zipRange);
        });
        return providedZipCodes;
    }

    /**
     *
     * @param providedZipCodes - List<ZipRange>
     * Checks all conditions of overlapping and duplication in the given list
     * removes all such occurances
     */
    public void simplifyRange(List providedZipCodes){
        int size = providedZipCodes.size();
        for(int i=0;i<size-1;i++){
            for(int j=i+1; j<size;j++) {
                ZipRange zipRange1 = (ZipRange) providedZipCodes.get(i);
                ZipRange zipRange2 = (ZipRange) providedZipCodes.get(j);

                if ((zipRange2.getLow() - zipRange1.getHigh()) <= 1) {
                    zipRange1.setHigh(zipRange2.getHigh()>zipRange1.getHigh() ? zipRange2.getHigh() : zipRange1.getHigh());
                    providedZipCodes.remove(j);
                    j--;
                    size = providedZipCodes.size();
                }
            }
        }
    }

    /**
     *
     * @param zipCodeList
     * @return String
     * Converts the given list to a string
     */
    public String generateOutputList(List zipCodeList){
        Iterator it = zipCodeList.iterator();
        StringBuilder sb = new StringBuilder();
        while(it.hasNext()){
            sb.append((ZipRange)it.next());
        }
        return sb.toString();
    }


}
