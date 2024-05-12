using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IMoodActivitiesRepository
    {
        Task<List<Mood>> GetAllMoodActivitiesAsync();
        Task CreateMoodActivityAsync(Mood mood);

        Task DeleteMoodHabit(string userId, DateOnly date);
    }
}
