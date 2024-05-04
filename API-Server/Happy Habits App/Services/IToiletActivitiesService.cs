using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public interface IToiletActivitiesService
    {
        public Task<List<Toilet>> GetAllToiletActivities();
        public Task<Toilet> AddToiletActivity(ToiletForm form);
    }
}
