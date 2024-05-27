using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public interface ISleepActivitiesService
    {
        public Task<List<Sleep>> GetAllSleepActivities();
        public Task AddSleepActivity(SleepForm form);
        public Task<SleepStatistics> GetStatistics(string userId, string monday, string sunday);
    }
}
