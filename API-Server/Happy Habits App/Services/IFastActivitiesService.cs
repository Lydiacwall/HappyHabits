using Happy_Habits_App.Forms;

namespace Happy_Habits_App.Services
{
    public interface IFastActivitiesService
    {
        public Task AddFastActivity(FastActivityForm form);
    }
}
