using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public interface IMoodActivitiesService
    {
        public Task<List<Mood>> GetAllMoodActivities();
        public Task AddMoodActivity(MoodForm form);
        public Task<Dictionary<string, string>> GetMoodActivitiesOfCurrentYear(string userId);
    }
}
