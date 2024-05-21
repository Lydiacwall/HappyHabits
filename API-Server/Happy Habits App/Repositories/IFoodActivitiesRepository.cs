using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IFoodActivitiesRepository
    {
        Task CreateFoodActivityAsync(Food food);
        Task<List<Food>> GetAllFoodActivitiesByUserByDate(string userId, DateOnly dateOnly);
        Task DeleteFoodActivity(string id);
    }
}
