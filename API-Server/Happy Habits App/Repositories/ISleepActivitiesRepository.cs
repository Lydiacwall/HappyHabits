using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface ISleepActivitiesRepository
    {
        Task<List<Sleep>> GetAllSleepActivitiesAsync();
        Task CreateSleepActivityAsybc(Sleep sleep);
        public Task DeleteSleepActivityAsync(String userId, DateOnly date);
        Task<List<Sleep>> GetSleepActivitiesByMonthAndUserAsync(int year, int month, string userId);
    }
}