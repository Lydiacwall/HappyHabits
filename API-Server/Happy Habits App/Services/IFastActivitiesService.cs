using Happy_Habits_App.Forms;

namespace Happy_Habits_App.Services
{
    public interface IFastActivitiesService
    {
        Task AddFastActivity(FastActivityForm form);
        Task<FastActivitiesStatistics> GetFastActivitiesStatisticsAsync(string userId, int month, int year, string type);
    }
}
