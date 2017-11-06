package com.visma.lecture.Validators;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.exception.InvalidCriteriaException;
import com.visma.lecture.common.exception.NoItemFoundForCriteriaException;

import java.util.List;
import java.util.Map;

public class Validator extends Throwable{


    public static void validateInputString(String string){
        if (string == null || string.equals("") || string.isEmpty()){
            throw new InvalidCriteriaException("Input was null, empty or lower than 0.");
        }
    }

    public static void validateInputInteger(int number){
        if (number <= 0){
            throw new InvalidCriteriaException("Input was null, empty or lower than 0.");
        }
    }

    public static void validateInputLocation(ItemLocation location){
        if (location == null){
            throw new InvalidCriteriaException("Input was null, empty or lower than 0.");
        }
    }


    public static void validateOutputMap(Map<?, List<Item>> listMap){
        if (listMap.isEmpty()
                || listMap == null
                || listMap.entrySet().isEmpty()
                || listMap.keySet().isEmpty()){
            throw new NoItemFoundForCriteriaException("No items were found for the given search criteria.");
        }
    }

    public static void validateOutputItem(Item item){
        if (item == null){
            throw new NoItemFoundForCriteriaException("No items were found for the given search criteria.");
        }
    }

    public static void validateOutputString(String outputString){
        if (outputString.length() == 0 || outputString.equals("") || outputString == null){
            throw new NoItemFoundForCriteriaException("No items were found for the given search criteria.");
        }
    }

    public static void validateOutputList(List<?> list){
        if (list == null || list.size() == 0){
            throw new NoItemFoundForCriteriaException("No items were found for the given search criteria.");
        }
    }

    public static void validateOutputDouble(double number){
        if (number < 0){
            throw new NoItemFoundForCriteriaException("No items were found for the given search criteria.");
        }
    }

}
