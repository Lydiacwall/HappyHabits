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

        public async Task CreateFoodActivity(FoodForm form)
        {
            foreach(var food in form.Foods)
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
