using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public interface IWeightsActivitiesService
    {
        Task AddWeightsActivity(WeightsForm form);
        Task<WeightsStatistics> CalculateWeightsStatistics(string userId, int month, int year);
    }
}
