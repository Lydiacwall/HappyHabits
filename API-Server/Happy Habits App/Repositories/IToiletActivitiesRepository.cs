using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IToiletActivitiesRepository
    {
        Task<List<Toilet>> GetAllToiletActivitiesAsync();
        Task<Toilet> CreateToiletActivityAsync(Toilet toilet);
        Task<Toilet> UpdateToiletActivityAsync();

        Task<Toilet> GetToiletActivityByUserAndIdAndDate(string userId, string habitId, DateOnly date);
    }
}
