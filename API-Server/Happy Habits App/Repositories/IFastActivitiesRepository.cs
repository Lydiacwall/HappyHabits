using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IFastActivitiesRepository
    {
        Task<List<FastActivity>> GetAllFastActivitiesAsync();
        Task CreateFastActivityAsync(FastActivity fastActivity);
        Task DeleteFastActivityAsync(String userId, DateOnly date);
    }
}
