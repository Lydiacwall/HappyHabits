using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IWeightsActivitiesRepository
    {
        Task CreateWeightsActivity(Weights weights);
        Task<List<Weights>> GetWeightsActivitiesByUserAndDate(string userId, int month, int year);
    }
}
