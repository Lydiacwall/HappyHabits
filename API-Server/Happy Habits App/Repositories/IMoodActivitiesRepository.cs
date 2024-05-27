using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IMoodActivitiesRepository
    {
        Task<List<Mood>> GetAllMoodActivitiesAsync();
        Task CreateMoodActivityAsync(Mood mood);

        Task DeleteMoodHabitAsync(string userId, DateOnly date);
        Task<List<Mood>> GetMoodActivitiesByYearAsync(string userId, int year);
    }
}
