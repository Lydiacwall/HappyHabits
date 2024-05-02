using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public interface IFastActivityService
    {
        public Task<FastActivity> PostFastActivity();
        public Task<List<FastActivity>> GetAllFastActivities();
        
    }
}
