package com.Vikkos.FoodStorage.service;

import com.Vikkos.FoodStorage.model.Food;
import com.Vikkos.FoodStorage.model.FoodLeft;
import com.Vikkos.FoodStorage.repo.FoodLeftRepository;
import com.Vikkos.FoodStorage.repo.FoodRepository;
import com.Vikkos.FoodStorage.utils.CustomExceptions.FoodNotFoundException;
import com.Vikkos.FoodStorage.utils.UpdateFieldChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoodLeftService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodLeftRepository foodLeftRepository;

    @Autowired
    private FoodService foodService;

    private final UpdateFieldChecker updateFieldChecker = new UpdateFieldChecker();


    public List<FoodLeft> findAllFoodLeft(){
        return foodLeftRepository.findAllSorted();
    }

    public List<FoodLeft> findAllByBarcode(String barcode) throws FoodNotFoundException {
        List<FoodLeft> foodLeftList = foodLeftRepository.findAllByBarcode(barcode);
        if (foodLeftList.isEmpty()){
            throw new FoodNotFoundException("No registered Food items found for the specific barcode");
        }
        return foodLeftList;
    }

    public FoodLeft createFoodleft(String barcode, FoodLeft foodleft) throws FoodNotFoundException{
        Food food = foodService.findByBarcode(barcode);

        foodleft.setId(UUID.randomUUID());
        foodleft.setFood(food);
        foodLeftRepository.save(foodleft);

        food.getFoodLeftList().add(foodleft);
        foodRepository.save(food);
        return foodleft;
    }


    public Food removeFoodLeft(String barcode, UUID id) throws FoodNotFoundException {
        Optional<Food> foundFoodOpt = foodRepository.findByBarcode(barcode);
        if (foundFoodOpt.isEmpty()){
            throw new FoodNotFoundException("There is nothing to be deleted");
        }

        Food foundFood = foundFoodOpt.get();
        removeHelper(foundFood, id);
        foodRepository.save(foundFood);
        return foundFood;
    }

    public FoodLeft updateFoodLeft(String barcode, UUID id, FoodLeft updatefoodleft) throws FoodNotFoundException {

        Optional<FoodLeft> foodLeftOpt = foodLeftRepository.findById(id);
        if (foodLeftOpt.isEmpty()){
            throw new FoodNotFoundException("FoodLeft with ID " + id + " not found");
        }
        FoodLeft foodleft = foodLeftOpt.get();

        updateFieldChecker.updateFieldIfNotNull(foodleft::setAmount, updatefoodleft.getAmount());
        updateFieldChecker.updateFieldIfNotNull(foodleft::setAmountType, updatefoodleft.getAmountType());
        foodLeftRepository.save(foodleft);


        Food food = foodService.findByBarcode(barcode);
        List<FoodLeft> foodLeftList = removeHelper(food, id);
        foodLeftList.add(foodleft);
        foodRepository.save(food);
        return foodleft;
    }

    private List<FoodLeft> removeHelper(Food food, UUID id) throws FoodNotFoundException {
        List<FoodLeft> foodLeftList = food.getFoodLeftList();

        Optional<FoodLeft> foodLeftToRemove = foodLeftList.stream()
                .filter(foodLeft -> foodLeft.getId().equals(id))
                .findFirst();

        if (foodLeftToRemove.isEmpty()){
            throw new FoodNotFoundException("FoodLeft with ID " + id + " not found");
        }
        foodLeftList.remove(foodLeftToRemove.get());
        return foodLeftList;
    }
}
