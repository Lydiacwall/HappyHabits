using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public interface IFoodActivitiesService
    {
        public Task<List<FoodDto>> GetAllFoods(string userId, string date);
        public Task CreateFoodActivity(FoodForm form);
        public Task DeleteFoodActivity(string id);
    }
}
