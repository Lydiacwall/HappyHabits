using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;

namespace Happy_Habits_App.Services
{
    public class FoodActivitiesService : IFoodActivitiesService
    {
        private readonly IFoodActivitiesRepository _foodActivitiesRepository;

        public FoodActivitiesService(IFoodActivitiesRepository foodActivitiesRepository)
        {
            _foodActivitiesRepository = foodActivitiesRepository;
        }

        public async Task<FoodStatistics> CalculateFoodStatistics(string userId, string date)
        {
            List<Food> foodList = await _foodActivitiesRepository.GetAllFoodActivitiesByUserByDate(userId, DateOnly.Parse(date));

            float totalProtein = 0;
            float totalCarbs = 0;
            float totalFiber = 0;
            float totalFats = 0;

            // Aggregate the nutritional values
            foreach (var food in foodList)
            {
                totalProtein += food.Protein;
                totalCarbs += food.Carbs;
                totalFiber += food.Fiber;
                totalFats += food.Fats;
            }

            // Calculate total grams of macronutrients
            float totalGrams = totalProtein + totalCarbs + totalFiber + totalFats;

            // Avoid division by zero if no food is present
            if (totalGrams == 0)
            {
                return new FoodStatistics
                {
                    ProteinPercentage = 0,
                    CarbsPercentage = 0,
                    FiberPercentage = 0,
                    FatsPercentage = 0
                };
            }

            // Calculate percentages
            float proteinPercentage = (totalProtein / totalGrams) * 100;
            float carbsPercentage = (totalCarbs / totalGrams) * 100;
            float fiberPercentage = (totalFiber / totalGrams) * 100;
            float fatsPercentage = (totalFats / totalGrams) * 100;

            // Return the statistics
            return new FoodStatistics
            {
                ProteinPercentage = proteinPercentage,
                CarbsPercentage = carbsPercentage,
                FiberPercentage = fiberPercentage,
                FatsPercentage = fatsPercentage
            };
        }


        public async Task CreateFoodActivity(FoodForm form)
        {
            foreach (var food in form.Foods)
            {
                await _foodActivitiesRepository.CreateFoodActivityAsync(
                   new Food(
                       DateOnly.Parse(form.Date),
                       form.UserId,
                       food.Name,
                       food.Meal,
                       food.Calories,
                       food.Protein,
                       food.Fats,
                       food.Carbs,
                       food.Fiber,
                       food.Quantity,
                       food.Measurement));
            }

        }

        public async Task DeleteFoodActivity(string id)
        {
            await _foodActivitiesRepository.DeleteFoodActivity(id);
        }

        public async Task<List<FoodDto>> GetAllFoods(string userId, string date)
        {
            List<Food> foodList = await _foodActivitiesRepository.GetAllFoodActivitiesByUserByDate(userId, DateOnly.Parse(date));
            var foodDtoList = foodList.Select(food => new FoodDto(
                food.Id,
                food.Name,
                food.Meal,
                food.Calories,
                food.Protein,
                food.Fats,
                food.Carbs,
                food.Fiber,
                food.Quantity,
                food.Measurement
            )).ToList();

            return foodDtoList;
        }

    }
}
