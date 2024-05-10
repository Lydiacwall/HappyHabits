using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IToiletActivitiesRepository
    {
        Task<List<Toilet>> GetAllToiletActivitiesAsync();
        Task CreateToiletActivityAsync(Toilet toilet);
        Task UpdateToiletActivityAsync(Toilet toilet);

        Task<Toilet> GetToiletActivityByUserAndIdAndDate(string userId, DateOnly date);
    }
}
