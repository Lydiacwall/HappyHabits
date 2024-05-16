using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IWeightsActivitiesRepository
    {
        Task CreateWeightsActivity(Weights weights);
    }
}
